package com.example.physio_plus_app;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.physio_plus_app.R1.R1;
import com.example.physio_plus_app.R3.R3;
import com.example.physio_plus_app.R4.R4;
import com.example.physio_plus_app.R5.R5;
import com.example.physio_plus_app.R6.R6;
import com.example.physio_plus_app.R7.R7;
import com.example.physio_plus_app.R9.R9;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);
    }

    public void physioClubActivity(View view) {
        Intent intent = new Intent(getApplicationContext(), R1.class);
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