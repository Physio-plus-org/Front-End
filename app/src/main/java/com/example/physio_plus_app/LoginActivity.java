package com.example.physio_plus_app;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.physio_plus_app.R1.R1;

public class LoginActivity extends AppCompatActivity {

    Button psfBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        psfBtn = findViewById(R.id.r1EnterButtonPSF);

        psfBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openNewActivity();
            }

            private void openNewActivity() {
                Intent intent = new Intent(getApplicationContext(), R1.class);
                Log.e(TAG,"im here");
                startActivity(intent);
            }
        });
    }
}