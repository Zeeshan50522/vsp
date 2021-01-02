package com.example.vitalsigns;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {


    EditText usernameText,passwordLogin;
    Button signInButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        usernameText = findViewById(R.id.usernameText);
        passwordLogin = findViewById(R.id.password_login);
        signInButton = findViewById(R.id.signInButton);
        TextView create_new_account_txt = findViewById(R.id.create_new_accountText);



        signInButton.setOnClickListener(view -> {
            String username = usernameText.getText().toString();
            String password = passwordLogin.getText().toString();

            FirebaseDatabase database = FirebaseDatabase.getInstance();
            DatabaseReference myRef = database.getReference("user");
            myRef.child(username).child(password).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    CommonFunction.emailDatabaseReference = usernameText.getText().toString();
                    Intent intent = new Intent(MainActivity.this,home.class);
                    startActivity(intent);
                }
                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
        });

        create_new_account_txt.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this,create_new_account.class);
            startActivity(intent);
        });
    }
}