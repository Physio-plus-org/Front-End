package com.example.physio_plus_app.R3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.physio_plus_app.R;
import com.example.physio_plus_app.R6.R6;

public class R3 extends AppCompatActivity {

    /* Topbar */
    private ImageView PhysiologoTopbarButton;
    private ImageView profileTopbarButton;
    private ImageView notifTopbarButton;
    private ImageView goBackButton;

    EditText name_Patient,surname_Patient,addressPatient,amkaPatient;
    ImageButton createPatient;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.r3_activity);

        name_Patient = findViewById(R.id.username);
        surname_Patient = findViewById(R.id.surname);
        addressPatient = findViewById(R.id.addess);
        amkaPatient = findViewById(R.id.amka);
        createPatient = findViewById(R.id.createbtn);

        PhysiologoTopbarButton = findViewById((R.id.PhysioLogoTopbar));
        profileTopbarButton = findViewById((R.id.profileTopbar));
        goBackButton = findViewById(R.id.goback);



        PhysiologoTopbarButton.setOnClickListener(v->{
            Intent i = new Intent(R3.this, R6.class );
            startActivity(i);
        });

        profileTopbarButton.setOnClickListener(v->{
            Intent i = new Intent(R3.this, patientProfile.class );
            startActivity(i);
        });

        goBackButton.setOnClickListener(v -> finish());



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