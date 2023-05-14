package com.example.physio_plus_app;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private final String myIP = "192.168.1.248";

    Button createPatient;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        createPatient = findViewById(R.id.createbtn);
        createPatient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openNewActivity();
            }
            private void openNewActivity() {
                Intent intent = new Intent(getApplicationContext(), CreatePatient.class);
                startActivity(intent);
            }
        });
    }
}