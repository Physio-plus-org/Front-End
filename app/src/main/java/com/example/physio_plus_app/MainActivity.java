package com.example.physio_plus_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button psfBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        psfBtn = findViewById(R.id.r1EnterButtonPSF);

        psfBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openNewActivity();
            }

            private void openNewActivity() {
                Intent intent = new Intent(getApplicationContext(), R1.class);
                startActivity(intent);
            }
        });
    }
}