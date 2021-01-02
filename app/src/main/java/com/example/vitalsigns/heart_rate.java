package com.example.vitalsigns;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.vitalsigns.meals_screen.Meal;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.fitness.Fitness;
import com.google.android.gms.fitness.FitnessOptions;
import com.google.android.gms.fitness.data.Bucket;
import com.google.android.gms.fitness.data.DataSet;
import com.google.android.gms.fitness.data.DataType;
import com.google.android.gms.fitness.data.Field;
import com.google.android.gms.fitness.request.DataReadRequest;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

public class heart_rate extends AppCompatActivity {

    FitnessOptions fitnessOptions;
    List<Bpm_model> bpmValue;
    RecyclerView RC_view;
    private BpmAdapter BpmAdapter;
    LineGraphSeries<DataPoint> series;
    private LineChart lineChart;
    private DatabaseReference myRef;
    ArrayList<Entry> current;
    ArrayList<Entry> predicted;
    Socket s;
    int DataSetSize=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_heart_rate);

        RC_view = findViewById(R.id.rv);
        current = new ArrayList<>();
        predicted = new ArrayList<>();
//
        FirebaseDatabase database = FirebaseDatabase.getInstance("https://vitalsignsproject-5f6d8-default-rtdb.firebaseio.com/");
        myRef = database.getReference();
        bpmValue = new ArrayList<>();
        BpmAdapter = new BpmAdapter(bpmValue);
        lineChart  = findViewById(R.id.lineChart);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setReverseLayout(true);
        linearLayoutManager.setStackFromEnd(true);
        RC_view.setLayoutManager(linearLayoutManager);
        RC_view.setAdapter(BpmAdapter);

        series = new LineGraphSeries<>();



        lineChart.animateX(1000);
        fitnessOptions = FitnessOptions.builder()
                .addDataType(DataType.TYPE_HEART_RATE_BPM, FitnessOptions.ACCESS_READ)
                .addDataType(DataType.AGGREGATE_HEART_RATE_SUMMARY, FitnessOptions.ACCESS_READ)
                .build();

        GoogleSignInAccount account = GoogleSignIn.getAccountForExtension(this, fitnessOptions);
        if (!GoogleSignIn.hasPermissions(account, fitnessOptions)) {
            GoogleSignIn.requestPermissions(
                    this, // your activity
                    1, // e.g. 1
                    account,
                    fitnessOptions);

        } else {
            accessGoogleFit();
        }


    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            accessGoogleFit();
        }
    }

    private void accessGoogleFit() {

        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        long endTime = cal.getTimeInMillis();
        cal.add(Calendar.HOUR, -1);
        long startTime = cal.getTimeInMillis();

        DataReadRequest readRequest = new DataReadRequest.Builder()
                .read(DataType.TYPE_HEART_RATE_BPM)
                .setTimeRange(startTime, endTime, TimeUnit.MILLISECONDS)
                .bucketByTime(365, TimeUnit.HOURS)
                .build();


        Fitness.getHistoryClient(this, Objects.requireNonNull(GoogleSignIn.getLastSignedInAccount(this)))
                .readData(readRequest)
                .addOnSuccessListener(dataReadResponse -> {

                    for (Bucket bucket : dataReadResponse.getBuckets()) {
                        List<DataSet> dataSets = bucket.getDataSets();
                        for (DataSet dataSet : dataSets) {
                            DataSetSize =  dataSet.getDataPoints().size();
                            for (int i = 0; i < dataSet.getDataPoints().size(); i++) {
                                Bpm_model bpm_model = new Bpm_model(Double.parseDouble(String.valueOf(dataSet.getDataPoints().get(i).getValue(Field.FIELD_BPM))), dataSet.getDataPoints().get(i).getStartTime(TimeUnit.MILLISECONDS), dataSet.getDataPoints().get(i).getEndTime(TimeUnit.MILLISECONDS));
                                bpmValue.add(bpm_model);
                                current.add(new Entry(i, (float) bpm_model.bp_value));
                                JSONObject obj = new JSONObject();
                                try {
                                    obj.put("Sentence", dataSet.getDataPoints().get(i).getValue(Field.FIELD_BPM) + "");
                                    String bpm = String.valueOf(dataSet.getDataPoints().get(i).getValue(Field.FIELD_BPM));
                                    sendMessage(bpm);

                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                                 myRef.child(CommonFunction.userLogin).push().setValue(bpm_model);
                                BpmAdapter.notifyDataSetChanged();
                            }
                        }
                    }
                })
                .addOnFailureListener(e -> {
                })
                .addOnCompleteListener(task -> {
                });
    }

    private void sendMessage(final String msg) {

        final Handler handler = new Handler();
        Thread thread = new Thread(() -> {
            try {
                s = new Socket("192.168.10.17", 5000);
                OutputStream out = s.getOutputStream();
                PrintWriter output = new PrintWriter(out);
                output.println(msg);
                output.flush();
                BufferedReader input = new BufferedReader(new InputStreamReader(s.getInputStream()));
                final String st = input.readLine();
                handler.post(() -> {

                    if (st != null && st.trim().length() != 0) {
                        try {
                            JSONObject obj = new JSONObject(st);
                            String label = obj.getString("label");
                            predicted.add(new Entry(DataSetSize, (float) Double.parseDouble(label)));
                            DataSetSize = DataSetSize + 1;
                            setData();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
//                    output.close();
//                    out.close();
                // s.close();

            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        thread.start();
    }
    private void setData(){

        LineDataSet set1,set2;
        set1 = new LineDataSet(current,"watch data");
        set1.setColor(Color.RED);
        set1.setDrawCircles(false);
        set1.setLineWidth(2f);

        set2 = new LineDataSet(predicted,"predicated");
        set2.setColor(Color.BLUE);
        set2.setDrawCircles(false);
        set2.setLineWidth(2f);

        LineData data = new LineData(set1,set2);
        lineChart.setData(data);
    }


}