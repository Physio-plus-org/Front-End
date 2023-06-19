package com.example.physio_plus_app.Main_PSF;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.physio_plus_app.R;
import com.example.physio_plus_app.R1.R1;
import com.example.physio_plus_app.R2.R2;

public class show_psf extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.psf_show_psfs);


        //Topbar
        ImageView goBackButton = findViewById(R.id.back_button);

        //Footbar
        ImageView kneadingButton = findViewById(R.id.physio_btn);
        ImageButton addPSFButton = findViewById(R.id.addPSF_Button);

        goBackButton.setOnClickListener(v -> finish());

        kneadingButton.setOnClickListener(v -> {
            Intent i = new Intent(show_psf.this, R1.class);
            startActivity(i);
        });
        addPSFButton.setOnClickListener(v -> {
            Intent i = new Intent(show_psf.this, R2.class);
            startActivity(i);
        });

    }
}

