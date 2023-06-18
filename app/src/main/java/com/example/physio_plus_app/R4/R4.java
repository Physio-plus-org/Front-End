package com.example.physio_plus_app.R4;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.physio_plus_app.R;
import com.example.physio_plus_app.R6.R6;

import okhttp3.OkHttpClient;


public class R4 extends AppCompatActivity {


    /* Topbar */


    TextView name_tv;
    TextView age_tv;

    TextView address_tv;
    TextView date_tv;

    LinearLayout verticalLayout;


    public DisplayInfo handler;
    public Sessions sessions;
    String url1;

    OkHttpClient client,client2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.r4_activity);
        getSupportActionBar().hide(); // Hide the action bar

        Log.d("MainActivity", "onCreate method called");

        name_tv = findViewById(R.id.name_tv);
        age_tv = findViewById(R.id.age_tv);
        address_tv = findViewById(R.id.address_tv);
        date_tv = findViewById(R.id.date_tv);
        verticalLayout = findViewById(R.id.verticalLayout);



        url1 = "http://192.168.56.1/displaypatients.php";

        client = new OkHttpClient();
        client2 = new OkHttpClient();
        handler = new DisplayInfo(url1, client ,name_tv, age_tv, address_tv, date_tv);
        Log.d("Main Activity", "DisplayInfo is running without problems");


        sessions = new Sessions(url1, client2, verticalLayout);
        sessions.displaySessions();


        Log.d("Main Activity", "Sessions is running without problems");


//        /* Topbar */
//        ImageView physiologoTopbarButton = findViewById((R.id.PhysiologoTopbar));
//        Button profileTopbarButton = findViewById((R.id.ProfilePatientTopbar));
//        Button notifTopbarButton = findViewById(R.id.calendarTopBar);
//        ImageView goBackButton = findViewById(R.id.goback);
//
//
//        physiologoTopbarButton.setOnClickListener(v->{
//            Intent i = new Intent(R4.this, R6.class );
//            startActivity(i);
//        });
//
//        profileTopbarButton.setOnClickListener(v->{
////            Intent i = new Intent(R4.this, Profile.class );
////            startActivity(i);
//        });
//
//        goBackButton.setOnClickListener(v -> finish());

    }


    public void OnClick(View view){
        
    }



}