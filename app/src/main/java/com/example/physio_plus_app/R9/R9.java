package com.example.physio_plus_app.R9;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.example.physio_plus_app.R;
import com.example.physio_plus_app.R10.R10;

import java.util.Locale;


//import com.cheesecake.mytoxictraits.Week.ThisLocalizedWeek;

public class R9 extends AppCompatActivity{

    //    private Button button;
    private  final String myIP = "192.168.2.6";

//    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.r9_activity);

        ImageView financialMovesButton = findViewById(R.id.addPatientFootbar);
        financialMovesButton.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), R10.class);
            intent.putExtra("patient_id", "12345678");
            startActivity(intent);
        });
//        button= findViewById(R.id.buttonNext);
//        Bundle args = new Bundle();
//        //start of actionBar
//        ActionBar actionBar;
//        actionBar = getSupportActionBar();
//
//        assert actionBar != null;
//        actionBar.setDisplayShowCustomEnabled(true);
//        LayoutInflater inflater=(LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//        View view = inflater.inflate(R.layout.r9_custom_image,null);
//        actionBar.setCustomView(view);
//
//        // Define ColorDrawable object and parse color
//        // using parseColor method
//        // with color hash code as its parameter
//        ColorDrawable colorDrawable
//                = new ColorDrawable(Color.parseColor("white"));
//
//        // Set BackgroundDrawable
//        actionBar.setBackgroundDrawable(colorDrawable);
//        //end of actionBar
//        Locale locale = new Locale("EL");
//        Locale.setDefault(locale);


        String url = "https://physioplus.000webhostapp.com/R7/TestPrint.php?range_start=2023-04-01&range_end=2023-07-01";
        try {
            OkHttpHandlerR9 okHttpHandler = new OkHttpHandlerR9();
            okHttpHandler.testPrint(url);
            System.out.println("Main Activity testPrint");
        } catch (Exception e) {
            e.printStackTrace();
        }

//        /* Topbar */
//        ImageView physiologoTopbarButton = findViewById((R.id.PhysiologoTopbar));
//        Button profileTopbarButton = findViewById((R.id.ProfilePatientTopbar));
//        Button notifTopbarButton = findViewById(R.id.calendarTopBar);
//        ImageView goBackButton = findViewById(R.id.goback);

    }

    private void openActivity2() {
        Intent intent = new Intent(this, R9_2.class);
        startActivity(intent);
    }




}