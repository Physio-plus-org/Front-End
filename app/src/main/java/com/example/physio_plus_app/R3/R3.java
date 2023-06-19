package com.example.physio_plus_app.R3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.physio_plus_app.Patientprofile.patientProfile;
import com.example.physio_plus_app.R;
import com.example.physio_plus_app.R6.R6;

public class R3 extends AppCompatActivity {



    EditText name_Patient,surname_Patient,addressPatient,amkaPatient;
    ImageButton createPatient, returnButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.r3_activity);

        name_Patient = findViewById(R.id.username);
        surname_Patient = findViewById(R.id.surname);
        addressPatient = findViewById(R.id.addess);
        amkaPatient = findViewById(R.id.amka);
        createPatient = findViewById(R.id.createbtn);
        returnButton = findViewById(R.id.backbtn);

        /* Topbar */
        ImageView physiologoTopbarButton = findViewById((R.id.PhysiologoTopbar));
        Button profileTopbarButton = findViewById((R.id.ProfilePatientTopbar));
        Button notifTopbarButton = findViewById(R.id.calendarTopBar);
        ImageView goBackButton = findViewById(R.id.goback);



        physiologoTopbarButton.setOnClickListener(v->{
            Intent i = new Intent(R3.this, R6.class );
            startActivity(i);
        });

        profileTopbarButton.setOnClickListener(v->{
            Intent i = new Intent(R3.this, patientProfile.class );
            startActivity(i);
        });

        goBackButton.setOnClickListener(v -> finish());

        returnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }
    public void onClick(View v) {
        String namePatient = name_Patient.getText().toString();
        String surnamePatient = surname_Patient.getText().toString();
        String addressOfPatient = addressPatient.getText().toString();
        String amPatient = amkaPatient.getText().toString();

        try {
            String response = OkHttpHandler3.loghistory(new ParamsR3(namePatient,surnamePatient, amPatient, addressOfPatient));
            Toast.makeText(R3.this, response, Toast.LENGTH_LONG).show();
            
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}