package com.example.vollup_app_android;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.icu.util.Currency;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.DecimalFormat;
import java.text.NumberFormat;

public class Login extends AppCompatActivity {

    EditText email, password;
    ProgressBar progressBar;
    TextView register;
    Button login;
    private long mLastClickTime = 0;
    String mail, pass, id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //defining element variables:
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        login = findViewById(R.id.login);
        register = findViewById(R.id.register);
        progressBar = findViewById(R.id.progressBar);

        //Hide the progressBar
        progressBar.setVisibility(View.INVISIBLE);

        register.setOnClickListener(view -> {
            //setting double click blocking code
            if (SystemClock.elapsedRealtime() - mLastClickTime < 1000){
                return;
            }
            mLastClickTime = SystemClock.elapsedRealtime();
            //go to register activity
            startActivity(new Intent(Login.this, Register.class));
        });

        login.setOnClickListener(view -> {
            //setting double click blocking code
            if (SystemClock.elapsedRealtime() - mLastClickTime < 1000){
                return;
            }
            mLastClickTime = SystemClock.elapsedRealtime();
            //go to register Main activity
            SessionManagement sessionManagement = new SessionManagement(Login.this);
            sessionManagement.saveSession(id);
        });
    }

    @Override
    protected void onStart() {
        super.onStart();

        //check if user is logged in
        //if user is logged in --> move to main activity

        SessionManagement sessionManagement = new SessionManagement(Login.this);
        String userUid = sessionManagement.getSession();
        if(!userUid.equals("-1")){
            //user uid logged in and move to main activity
            moveToMainActivity();
        }
    }

    private void moveToMainActivity() {
        Intent intent = new Intent(Login.this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}