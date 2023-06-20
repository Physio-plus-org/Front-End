package com.example.physio_plus_app.R4;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.physio_plus_app.R;
import com.example.physio_plus_app.R5.R5;

import java.util.Objects;

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
    String url1, url2;

    OkHttpClient client,client2;

    private static final String DISPLAY_INFO = "https://physioplus.000webhostapp.com/R4/displaypatients.php";
    private static final String DISPLAY_SESSIONS= "https://physioplus.000webhostapp.com/R4/displaysessions.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.r4_activity);
        Objects.requireNonNull(getSupportActionBar()).hide(); // Hide the action bar

        Log.d("MainActivity", "onCreate method called");

        name_tv = findViewById(R.id.name_tv);
        age_tv = findViewById(R.id.age_tv);
        address_tv = findViewById(R.id.address_tv);
        date_tv = findViewById(R.id.date_tv);
        verticalLayout = findViewById(R.id.verticalLayout);



        url1 = DISPLAY_INFO;
        url2 = DISPLAY_SESSIONS;



        Log.d("Main Activity", "Sessions is running without problems");








        ImageView goBackView = findViewById(R.id.goback);
        goBackView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View goBackView) {
                Intent intent = new Intent(R4.this, R5.class);
                startActivity(intent);
            }
        });



        // Get the intent that started this activity
        Intent intent = getIntent();

        // Retrieve the string extra from the intent
        String message = intent.getStringExtra("PATIENT_AMKA");

        // Use the received string variable as needed
        if (message != null) {

            String patient_amka = message;

            client = new OkHttpClient();
            client2 = new OkHttpClient();
            handler.sendPatientNameToServer(patient_amka);
            handler = new DisplayInfo(url1, "12345678", client ,name_tv, age_tv, address_tv, date_tv);
            //vazw dummy metablhth sth thesh tou amka gia na kanw thn klhsh ths vashs
            Log.d("Main Activity", "DisplayInfo is running without problems");

            sessions.sendPatientNameToServer(patient_amka);
            sessions = new Sessions(url2, "12345678", client2, verticalLayout);
            //vazw dummy metablhth sth thesh tou amka gia na kanw thn klhsh ths vashs
            sessions.displaySessions();


        }else{
            Log.d("Main R4","Data parsed from R4 to R5 is null!");
        }


    }






}