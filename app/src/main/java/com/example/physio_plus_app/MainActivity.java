package com.example.physio_plus_app;

import androidx.appcompat.app.AppCompatActivity;


import android.os.Bundle;

import android.util.Log;
import android.widget.LinearLayout;
import android.widget.TextView;

import okhttp3.OkHttpClient;


public class MainActivity extends AppCompatActivity {

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
        setContentView(R.layout.activity_main);
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


    }






}