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

    EditText name_Patient,surname_Patient,addressPatient,amkaPatient;
    ImageButton createPatient;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        name_Patient = (EditText) findViewById(R.id.username);
        surname_Patient = (EditText) findViewById(R.id.surname);
        addressPatient = (EditText) findViewById(R.id.addess);
        amkaPatient = (EditText) findViewById(R.id.amka);
        createPatient = (ImageButton) findViewById(R.id.createbtn);

        createPatient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String namePatient = name_Patient.getText().toString();
                String surnamePatient = surname_Patient.getText().toString();
                String addressofPatient = addressPatient.getText().toString();
                String amPatient = amkaPatient.getText().toString();

                // Create an instance of OKHttpHandler
                OkHttpHandler okHttpHandler = new OkHttpHandler();

                // Call the CreatePatient method with the obtained data
                try {
                    String response = okHttpHandler.loghistory(namePatient,surnamePatient, amPatient, addressofPatient);
                    Toast.makeText(MainActivity.this, response, Toast.LENGTH_LONG).show();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }

        });
    }
}