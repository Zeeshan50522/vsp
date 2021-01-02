package com.example.vitalsigns;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class create_new_account extends AppCompatActivity {

    Calendar dateOfBirth;
    Button account_created,dateBirth;
    TextView birthday;
    EditText userName,emailAddress,phoneNumber,password,confirmPassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_new_account);
        account_created = findViewById(R.id.account_created);
        dateOfBirth = Calendar.getInstance();
        dateBirth = findViewById(R.id.dateBirth);
        birthday = findViewById(R.id.birthday);
        userName = findViewById(R.id.username);
        emailAddress = findViewById(R.id.emailAddress);
        phoneNumber = findViewById(R.id.phoneNumber);
        password = findViewById(R.id.password);
        confirmPassword = findViewById(R.id.confirmPassword);

        DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                dateOfBirth.set(Calendar.YEAR, year);
                dateOfBirth.set(Calendar.MONTH, monthOfYear);
                dateOfBirth.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel();
            }

        };
        dateBirth.setOnClickListener(view -> new DatePickerDialog(create_new_account.this, date, dateOfBirth
                .get(Calendar.YEAR), dateOfBirth.get(Calendar.MONTH),
                dateOfBirth.get(Calendar.DAY_OF_MONTH)).show());


        account_created.setOnClickListener(view -> {
            String  username = userName.getText().toString();
            String emailaddress = emailAddress.getText().toString();
            CommonFunction.username = username;
            String mobileNo =phoneNumber.getText().toString();
            String password_1 = password.getText().toString();
            String password_2 = confirmPassword.getText().toString();

            if (username.isEmpty() || emailaddress.isEmpty() || mobileNo.isEmpty() || password_1.isEmpty()|| password_2.isEmpty()|| birthday.getText().toString().isEmpty()) {
               Toast.makeText(create_new_account.this,"Please fill all field",Toast.LENGTH_SHORT).show();
            }else{
                if (!password_1.equals(password_2)) {
                    Toast.makeText(create_new_account.this, "Password not match", Toast.LENGTH_SHORT).show();
                }else{
                    Intent intent = new Intent(create_new_account.this,basic_information.class);
                    intent.putExtra("username",username);
                    intent.putExtra("emailaddress",emailaddress);
                    intent.putExtra("mobileNo",mobileNo);
                    intent.putExtra("password_1",password_1);
                    intent.putExtra("dateOfBirth",birthday.getText().toString());
                    startActivity(intent);
                }
            }

        });

    }
    private void updateLabel() {
        String myFormat = "MM/dd/yy"; //In which you need put here
        SimpleDateFormat df = new SimpleDateFormat(myFormat, Locale.US);
        birthday.setText(df.format(dateOfBirth.getTime()));
    }
}
