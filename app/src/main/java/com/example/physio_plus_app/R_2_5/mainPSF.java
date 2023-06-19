package com.example.physio_plus_app.R_2_5;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;
import com.example.physio_plus_app.R;
import com.example.physio_plus_app.R1.R1;
import com.example.physio_plus_app.R2.R2;
import com.example.physio_plus_app.R_2_5.mainPSF;

public class mainPSF extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_psf);

        //Topbar
        ImageView physiologoTopbarButton = findViewById((R.id.PhysiologoTopbar));
        ImageView goBackButton = findViewById(R.id.back_button);

        //Footbar
        ImageView createPsfFootbarButton = findViewById(R.id.physiocenter_btn);
        ImageButton addButton = findViewById(R.id.add_Button);
        goBackButton.setOnClickListener(v -> finish());
        createPsfFootbarButton.setOnClickListener(v->{
            Intent i = new Intent(mainPSF.this, R1.class );
            startActivity(i);
        });
        addButton.setOnClickListener(v->{
            Intent i = new Intent(mainPSF.this, R2.class );
            startActivity(i);
        });
    }

}
