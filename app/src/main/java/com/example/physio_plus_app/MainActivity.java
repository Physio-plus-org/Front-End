package com.example.physio_plus_app;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private final String myIP = "192.168.1.89";
    private CreatePatient cbl;
    private RadioGroup rg;
    private View fullusername;
    private View Useramka;
    private View Useraddress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.fullusername = findViewById(R.id.username);
        this.Useramka = findViewById(R.id.amka);
        this.Useraddress = findViewById(R.id.address);
        cbl = new CreatePatient(myIP);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void Patient(View v) {
        makeRequest();
    }
    protected void makeRequest() {
        String url = "http://" + myIP + "/carsDBServices/logHistory.php?fullname=" + fullusername.toString() + "&amka=" + Useramka.toString() + "&address=" + Useraddress.toString();
        StringRequest stringRequest = new StringRequest(
                Request.Method.GET,
                url,
                null,
                null
        );
        RequestQueue requestQueue = Volley.newRequestQueue(MainActivity.this);
        requestQueue.add(stringRequest);
        /*try {
            OkHttpHandler okHttpHandler = new OkHttpHandler();
            okHttpHandler.logHistory(url);
            Toast.makeText(getApplicationContext(), "Selection Logged", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            e.printStackTrace();
        }*/
    }
}