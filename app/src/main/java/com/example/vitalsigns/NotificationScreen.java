package com.example.vitalsigns;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

public class NotificationScreen extends AppCompatActivity {




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification_screen);

        RecyclerView rv=  findViewById(R.id.notify_recyclerView);

        ArrayList <NotificationRowModal> notify_data = new ArrayList<NotificationRowModal>();

        notify_data.add(new NotificationRowModal("Your current heart rate is 120 in sitting mode which is not good. Try to" +
                " avoid heavy work for now and consult your doctor if this prolongs." , 1));

        NotificationRowAdapter notificationRowAdapter = new NotificationRowAdapter(this,notify_data);

        rv.setAdapter(notificationRowAdapter);

        rv.setLayoutManager(new LinearLayoutManager(this));

    }
}