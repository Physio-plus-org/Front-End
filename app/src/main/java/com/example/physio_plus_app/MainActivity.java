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

    TextView date1, date2, date3, date4, date5, date6, date7;

    public DisplayInfo handler;
    public Sessions sessions;
    String url;


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

        date1 = findViewById(R.id.date_text1);
        date2 = findViewById(R.id.date_text2);
        date3 = findViewById(R.id.date_text3);
        date4 = findViewById(R.id.date_text4);
        date5 = findViewById(R.id.date_text5);
        date6 = findViewById(R.id.date_text6);
        date7 = findViewById(R.id.date_text7);

        url = "http://192.168.56.1/displaypatients.php";

        OkHttpClient client = new OkHttpClient();
        handler = new DisplayInfo(url, client ,name_tv, age_tv, address_tv, date_tv);
        sessions = new Sessions(date1, date2, date3, date4, date5, date6, date7);

    }






}