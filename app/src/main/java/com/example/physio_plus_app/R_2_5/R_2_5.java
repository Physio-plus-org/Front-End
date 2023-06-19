package com.example.physio_plus_app.R_2_5;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;

import com.example.physio_plus_app.Patientprofile.patientProfile;
import com.example.physio_plus_app.R;
import com.example.physio_plus_app.R3.R3;
import com.example.physio_plus_app.R6.R6;
import com.example.physio_plus_app.R7.R7;

public class R_2_5 {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.r7_activity);


        ImageView physiologoTopbarButton = findViewById((R.id.PhysiologoTopbar));
        Button profileTopbarButton = findViewById((R.id.ProfilePatientTopbar));
        ImageView goBackButton = findViewById(R.id.goback);

        /* Footbar */
        ImageView calendarFootbarButton = findViewById(R.id.calendarFootbar);
        ImageView addPatientFootbarButton = findViewById(R.id.addPatientFootbar);




        physiologoTopbarButton.setOnClickListener(v->{
            Intent i = new Intent(R7.this, R6.class );
            startActivity(i);
        });

        profileTopbarButton.setOnClickListener(v->{
            Intent i = new Intent(R7.this, patientProfile.class );
            startActivity(i);
        });

        goBackButton.setOnClickListener(v -> finish());


        addPatientFootbarButton.setOnClickListener((v->{
            Intent i = new Intent(R7.this, R3.class );
            startActivity(i);

        }));
        calendarFootbarButton.setOnClickListener(v->{
            Intent i = new Intent(R7.this, R6.class);
            startActivity(i);
        });


    }



}
