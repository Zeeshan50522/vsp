package com.example.vitalsigns;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.NumberPicker;

import androidx.appcompat.app.AppCompatActivity;

import com.example.vitalsigns.model.userInfo;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class basic_information extends AppCompatActivity {

    NumberPicker numberPicker;
    NumberPicker numberPicker_weight;
    Button submit_height_and_weight;
    String height;
    String weightValue;
    private DatabaseReference myRef;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basic_information);

        FirebaseDatabase database = FirebaseDatabase.getInstance("https://vitalsignsproject-5f6d8-default-rtdb.firebaseio.com/");
        myRef = database.getReference();
        String username  = getIntent().getStringExtra("username");
        String emailAddress = getIntent().getStringExtra("emailaddress");
        String mobileNo = getIntent().getStringExtra("mobileNo");
        String password_1 = getIntent().getStringExtra("password_1");
        String dateOfBirth = getIntent().getStringExtra("dateOfBirth");


        CommonFunction.emailDatabaseReference = emailAddress;
        numberPicker =  findViewById(R.id.numberPicker);
        submit_height_and_weight = findViewById(R.id.submit_height_and_weight);
        numberPicker_weight = findViewById(R.id.numberPicker_weight);
        String[] num = {
                "4","4.1","4.2","4.3","4.4","4.5","4.6","4.7","4.8","4.9","4.10","4.11",
                "5","5.1","5.2","5.3","5.4","5.5","5.6","5.7","5.8","5.9","5.10","5.11",
                "6","6.1","6.2","6.3","6.4","6.5","6.6","6.7","6.8","6.9","6.10","6.11",
                "7","7.1","7.2","7.3","7.4","7.5","7.6","7.7","7.8","7.9","7.10","7.11"
        };
        String[] weight = {
                "30-40","40-50","50-60","60-70","70-80","80-90","90-100","100-110","110-120","120-130",
                "130-140","140-150","150-160","160-170","170-180","180-190","190-200","200-210","210-220",
                "220-230","230-240","240-250","250-260","260-270","270-280","280-290","290-300"
        };
        numberPicker.setMinValue(0);
        numberPicker.setMaxValue(num.length-1);
        numberPicker.setWrapSelectorWheel(false);
        numberPicker.setDisplayedValues(num);
        numberPicker.setValue(18);
        numberPicker.setDescendantFocusability(NumberPicker.FOCUS_BLOCK_DESCENDANTS);
        numberPicker.setOnValueChangedListener((picker, oldVal, newVal) -> height = num[newVal]);

        numberPicker_weight.setMinValue(0);
        numberPicker_weight.setMaxValue(weight.length-1);
        numberPicker_weight.setWrapSelectorWheel(false);
        numberPicker_weight.setDisplayedValues(weight);
        numberPicker_weight.setValue(4);
        numberPicker_weight.setDescendantFocusability(NumberPicker.FOCUS_BLOCK_DESCENDANTS);
        numberPicker_weight.setOnValueChangedListener((picker, oldVal, newVal) -> weightValue = weight[newVal]);
        submit_height_and_weight.setOnClickListener(view -> {
            myRef.child("user").child(username).setValue(new userInfo(username,emailAddress,mobileNo,password_1,dateOfBirth,height,weightValue));
            Intent intent = new Intent(basic_information.this,home.class);
            startActivity(intent);
        });
    }
}