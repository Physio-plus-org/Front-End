package com.example.physio_plus_app.Main_PSF;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;
import com.example.physio_plus_app.R;
import com.example.physio_plus_app.R1.R1;
import com.example.physio_plus_app.R2.R2;

public class mainPSF extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_psf);

        //Topbar
        ImageView goBackButton = findViewById(R.id.back_button);

        //Footbar
        ImageButton addButton = findViewById(R.id.add_Button);
        ImageView physioCenter = findViewById(R.id.physiocenter_btn);

        goBackButton.setOnClickListener(v -> finish());

        addButton.setOnClickListener(v->{
            Intent i = new Intent(mainPSF.this, R2.class );
            startActivity(i);
        });


        physioCenter.setOnClickListener(v->{
            Intent i = new Intent(mainPSF.this, show_psf.class );
            startActivity(i);

//            // Change background color
//            physioCenter.setBackgroundColor(Color.WHITE);
//
//            // Change drawable color
//            Drawable drawable = physioCenter.getBackground().getCurrent();
//            drawable.setColorFilter(Color.BLACK, PorterDuff.Mode.SRC_IN);
        });
    }

}
