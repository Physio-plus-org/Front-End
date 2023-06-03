package com.example.physio_plus_app;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

public class R3 extends AppCompatActivity {

    EditText name_Patient,surname_Patient,addressPatient,amkaPatient;
    ImageButton createPatient;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        name_Patient = findViewById(R.id.username);
        surname_Patient = findViewById(R.id.surname);
        addressPatient = findViewById(R.id.addess);
        amkaPatient = findViewById(R.id.amka);
        createPatient = findViewById(R.id.createbtn);

    }
    public void onClick(View v) {
        String namePatient = name_Patient.getText().toString();
        String surnamePatient = surname_Patient.getText().toString();
        String addressofPatient = addressPatient.getText().toString();
        String amPatient = amkaPatient.getText().toString();

        try {
            String response = OkHttpHandler3.loghistory(new ParamsR3(namePatient,surnamePatient, amPatient, addressofPatient));
            Toast.makeText(R3.this, response, Toast.LENGTH_LONG).show();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}