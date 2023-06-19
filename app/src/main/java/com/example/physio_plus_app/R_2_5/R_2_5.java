package com.example.physio_plus_app.R_2_5;

import static androidx.core.content.ContextCompat.startActivity;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.physio_plus_app.Patientprofile.patientProfile;
import com.example.physio_plus_app.R;
import com.example.physio_plus_app.R1.R1;
import com.example.physio_plus_app.R2.R2;
import com.example.physio_plus_app.R3.R3;
import com.example.physio_plus_app.R6.R6;
import com.example.physio_plus_app.R7.R7;
import com.google.gson.Gson;

import okhttp3.OkHttpClient;

public class R_2_5 extends AppCompatActivity {



    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.r_2_5_activity);



        //Topbar

        ImageView physiologoTopbarButton = findViewById((R.id.PhysiologoTopbar));
        ImageView goBackButton = findViewById(R.id.goback);


        //Footbar
        ImageView createPsfFootbarButton = findViewById(R.id.physiocenter_btn);
        ImageButton addButton = findViewById(R.id.add_Button);









        goBackButton.setOnClickListener(v -> finish());



        createPsfFootbarButton.setOnClickListener(v->{
            Intent i = new Intent(R_2_5.this, R1.class );
            startActivity(i);
        });


        addButton.setOnClickListener(v->{
            Intent i = new Intent(R_2_5.this, R2.class );
            startActivity(i);
        });



    }

}
