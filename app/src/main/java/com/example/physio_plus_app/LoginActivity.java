package com.example.physio_plus_app;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.physio_plus_app.R6.R6;
import com.example.physio_plus_app.R9.R9;
import com.example.physio_plus_app.Main_PSF.MainPSF;
import com.example.physio_plus_app.Utils.HttpHandler.HttpHandler;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);
        //initialize thread policy for http handler
        HttpHandler.setPolicy();
    }

    public void physioClubActivity(View view) {
        Intent intent = new Intent(getApplicationContext(), MainPSF.class);
        startActivity(intent);
    }
    public void physioCenterActivity(View view) {
        Intent intent = new Intent(getApplicationContext(), R6.class);
        startActivity(intent);
    }
    //TODO: R9 TO SEE
    public void physioPatientActivity(View view) {
        Intent intent = new Intent(getApplicationContext(), R9.class);
        startActivity(intent);
    }
}