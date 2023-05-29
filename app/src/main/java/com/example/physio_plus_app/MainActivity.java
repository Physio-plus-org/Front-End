package com.example.physio_plus_app;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private final String myIP = "192.168.1.248";

    EditText fullnamePatient,addressPatient,amkaPatient;
    ImageButton createPatient;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fullnamePatient = (EditText) findViewById(R.id.username);
        addressPatient = (EditText) findViewById(R.id.addess);
        amkaPatient = (EditText) findViewById(R.id.amka);
        createPatient = (ImageButton) findViewById(R.id.createbtn);

        createPatient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String namePatient = fullnamePatient.getText().toString();
                String addressofPatient = addressPatient.getText().toString();
                String amPatient = amkaPatient.getText().toString();

                // Create an instance of OKHttpHandler
                OkHttpHandler okHttpHandler = new OkHttpHandler();

                // Call the CreatePatient method with the obtained data
                try {
                    okHttpHandler.loghistory(namePatient, amPatient, addressofPatient);

                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }

        });
    }
}