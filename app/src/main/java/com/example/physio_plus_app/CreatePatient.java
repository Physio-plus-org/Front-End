package com.example.physio_plus_app;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class CreatePatient extends AppCompatActivity {
    private Button CreatePatientbtn;
    private EditText fullnamePatient;
    private EditText addressPatient;
    private EditText amkaPatient;


    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        CreatePatientbtn = findViewById(R.id.createbtn);
        fullnamePatient = findViewById(R.id.username);
        addressPatient = findViewById(R.id.addess);
        amkaPatient = findViewById(R.id.amka);

        CreatePatientbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String namePatient = fullnamePatient.getText().toString();
                String addressofPatient = addressPatient.getText().toString();
                String amkaPatient = CreatePatientbtn.getText().toString();

                // Create an instance of OKHttpHandler
                OkHttpHandler okHttpHandler = new OkHttpHandler();

                // Call the CreatePatient method with the obtained data
                try {
                    okHttpHandler.loghistory(namePatient, amkaPatient, addressofPatient);


                    Toast.makeText(CreatePatient.this, "Data written to database", Toast.LENGTH_SHORT).show();
                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(CreatePatient.this, "Error writing data to database", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
