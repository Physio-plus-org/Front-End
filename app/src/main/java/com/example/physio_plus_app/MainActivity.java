package com.example.physio_plus_app;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.physio_plus_app.R7.R7Main;

public class MainActivity extends AppCompatActivity {


    private static final String TAG = "R6Main";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        try {
            Intent i = new Intent(MainActivity.this, com.example.physio_plus_app.R6.R6Main.class);
            startActivity(i);
        }catch (Exception e){
            Log.e(TAG, "Failed to test connection. Response code: " + e.getMessage());
            runOnUiThread(() -> Toast.makeText(MainActivity.this, "Failed to test connection", Toast.LENGTH_SHORT).show());
        }
    }


}