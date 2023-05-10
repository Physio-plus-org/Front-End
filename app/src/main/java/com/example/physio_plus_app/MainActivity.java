package com.example.physio_plus_app;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    TextView name_tv;
    TextView age_tv;
    TextView birthday_tv;
    TextView address_tv;
    TextView date_tv;

    String currentDate = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(new Date());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide(); // Hide the action bar

        name_tv = findViewById(R.id.name_tv);
        age_tv = findViewById(R.id.age_tv);
        birthday_tv = findViewById(R.id.birthday_tv);
        address_tv = findViewById(R.id.address_tv);
        date_tv = findViewById(R.id.date_tv);

        date_tv.setText(currentDate);

    }
}