package com.example.physio_plus_app;

import androidx.appcompat.app.AppCompatActivity;


import android.os.Bundle;

import android.util.Log;
import android.widget.TextView;

import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Response;




public class MainActivity extends AppCompatActivity {

    TextView name_tv;
    TextView age_tv;

    TextView address_tv;
    TextView date_tv;


    public DisplayInfo handler;
    public Sessions sessions;
    String url1, url2;


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



        url1 = "http://192.168.56.1/displaypatients.php";
        url2 = "http://192.268.56.1/displaysessions.php";

        OkHttpClient client = new OkHttpClient();
        handler = new DisplayInfo(url1, client ,name_tv, age_tv, address_tv, date_tv);
        sessions = new Sessions(url2, client);

    }






}