package com.example.vitalsigns;
import  com.example.vitalsigns.meals_screen.*;
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
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.media.RingtoneManager;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.app.NotificationCompat;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

public class home extends AppCompatActivity {

    CardView add_today_meal;
    CardView walking_record;
    CardView heart_rate_layout;
    TextView current_cal;
    FitnessOptions fitnessOptions;
    ProgressBar mProgress;
    TextView  txtProgress;
    FitnessOptions fitnessOptionsHeart;
    TextView hr_value;
    ImageView notify;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        add_today_meal = findViewById(R.id.add_meal);
        walking_record = findViewById(R.id.walking_record);
        heart_rate_layout = findViewById(R.id.hr_measure_button);
        txtProgress = findViewById(R.id.txtProgress);
        current_cal = findViewById(R.id.current_cal);
        hr_value = findViewById(R.id.hr_value);
        notify = findViewById(R.id.notify);
        Resources res = getResources();
        Drawable drawable = res.getDrawable(R.drawable.circle);
        mProgress = (ProgressBar) findViewById(R.id.circularProgressbar);
        mProgress.setProgress(0);   // Main Progress
        mProgress.setMax(6000); // Maximum Progress
        mProgress.setProgressDrawable(drawable);
        FirebaseDatabase database = FirebaseDatabase.getInstance("https://vitalsignsproject-5f6d8-default-rtdb.firebaseio.com/");
        DatabaseReference Calories = database.getReference("TodayCalories");


        notify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(home.this, NotificationScreen.class);
                startActivity(intent);
            }
        });
        notification();
        Calories.child("zeeshan50522").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot!=null) {
                    if (dataSnapshot.getValue(Integer.class)!=null) {
                        int cal = dataSnapshot.getValue(Integer.class);
                        CommonFunction.cal = cal;
                        current_cal.setText(String.valueOf(cal));
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        add_today_meal.setOnClickListener(view -> {
            Intent intent = new Intent(home.this,Meal.class);
            startActivity(intent);
        });
        walking_record.setOnClickListener(view -> {
            Intent intent = new Intent(home.this,walking_home.class);
            startActivity(intent);
        });
        heart_rate_layout.setOnClickListener(view -> {
            Intent intent = new Intent(home.this,heart_rate.class);
            startActivity(intent);
        });

        fitnessOptions = FitnessOptions.builder()
                .addDataType(DataType.TYPE_STEP_COUNT_DELTA, FitnessOptions.ACCESS_READ)
                .addDataType(DataType.AGGREGATE_STEP_COUNT_DELTA, FitnessOptions.ACCESS_READ)
                .build();

        fitnessOptionsHeart = FitnessOptions.builder()
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
            accessGoogleFitStepCount();
        }


        GoogleSignInAccount accountHeart = GoogleSignIn.getAccountForExtension(this, fitnessOptionsHeart);
        if (!GoogleSignIn.hasPermissions(account, fitnessOptionsHeart)) {
            GoogleSignIn.requestPermissions(
                    this, // your activity
                    1, // e.g. 1
                    account,
                    fitnessOptionsHeart);

        } else {
            accessGoogleFit();
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            accessGoogleFitStepCount();
            accessGoogleFit();
        }
    }


    private void accessGoogleFitStepCount() {

        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        long endTime = cal.getTimeInMillis();
        cal.add(Calendar.DAY_OF_WEEK, -1);
        long startTime = cal.getTimeInMillis();

        DataReadRequest readRequest = new DataReadRequest.Builder()
                .read(DataType.AGGREGATE_STEP_COUNT_DELTA)
                .setTimeRange(startTime, endTime, TimeUnit.MILLISECONDS)
                .bucketByTime(1, TimeUnit.DAYS)
                .build();

        Fitness.getHistoryClient(this, Objects.requireNonNull(GoogleSignIn.getLastSignedInAccount(this)))
                .readData(readRequest)
                .addOnSuccessListener(dataReadResponse -> {

                    for (Bucket bucket : dataReadResponse.getBuckets()) {
                        List<DataSet> dataSets = bucket.getDataSets();
                        for (DataSet dataSet : dataSets) {
                            int sum = 0;
                            for (int i = 0; i < dataSet.getDataPoints().size(); i++) {
                                sum = sum + Integer.parseInt(dataSet.getDataPoints().get(i).getValue(Field.FIELD_STEPS).toString());
                            }

                            mProgress.setProgress(sum);
                            txtProgress.setText(String.valueOf(sum));

                        }
                    }
                })
                .addOnFailureListener(e -> {
                })
                .addOnCompleteListener(task -> {
                });
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
                        float avg = 0;
                        List<DataSet> dataSets = bucket.getDataSets();
                        for (DataSet dataSet : dataSets) {
                            for (int i = 0; i < dataSet.getDataPoints().size(); i++) {
                                Bpm_model bpm_model = new Bpm_model(Double.parseDouble(String.valueOf(dataSet.getDataPoints().get(i).getValue(Field.FIELD_BPM))), dataSet.getDataPoints().get(i).getStartTime(TimeUnit.MILLISECONDS), dataSet.getDataPoints().get(i).getEndTime(TimeUnit.MILLISECONDS));
                                avg = avg + (float) bpm_model.bp_value;
                            }
                            int total_hr = (int) (avg/ dataSet.getDataPoints().size());
                            hr_value.setText(String.valueOf(total_hr));
                        }
                    }
                })
                .addOnFailureListener(e -> {
                })
                .addOnCompleteListener(task -> {
                });
    }

    public void notification(){
        final Intent emptyIntent = new Intent();
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, emptyIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(this)
                        .setSmallIcon(R.drawable.food)
                        .setContentTitle("Heart rate")
                        .setContentText("your heart rate abnormal")
                        .setContentIntent(pendingIntent);
        mBuilder.setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION));

        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(0, mBuilder.build());
    }

}