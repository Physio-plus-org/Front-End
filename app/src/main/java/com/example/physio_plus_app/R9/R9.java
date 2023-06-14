package com.example.physio_plus_app;

import android.app.ActionBar;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Locale;


//import com.cheesecake.mytoxictraits.Week.ThisLocalizedWeek;

public class R9 extends AppCompatActivity{

    //    private Button button;
    private  final String myIP = "192.168.2.6";

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        button= findViewById(R.id.buttonNext);
        Bundle args = new Bundle();
        //start of actionBar
        ActionBar actionBar;
        actionBar = getSupportActionBar();

        actionBar.setDisplayShowCustomEnabled(true);
        LayoutInflater inflater=(LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.custom_image,null);
        actionBar.setCustomView(view);

        // Define ColorDrawable object and parse color
        // using parseColor method
        // with color hash code as its parameter
        ColorDrawable colorDrawable
                = new ColorDrawable(Color.parseColor("white"));

        // Set BackgroundDrawable
        actionBar.setBackgroundDrawable(colorDrawable);
        //end of actionBar
        Locale locale = new Locale("EL");
        Locale.setDefault(locale);


        String url = "http://"+myIP+"/physio_app_db/TestPrint.php?range_start=2023-04-01&range_end=2023-07-01";
        try {
            com.example.physio_plus_app.OkHttpHandler okHttpHandler = new com.example.physio_plus_app.OkHttpHandler();
            okHttpHandler.testPrint(url);
            System.out.println("Main Activity testPrint");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void openActivity2() {
        Intent intent = new Intent(this, MainActivity2.class);
        startActivity(intent);
    }


}