package com.example.physio_plus_app.LoginPSF;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.physio_plus_app.Main_PSF.MainPSF;
import com.example.physio_plus_app.R;

import java.util.ArrayList;

public class login_screen_psf extends AppCompatActivity {
    static ArrayList<String> tax;
    ArrayList<String> cbList = new ArrayList<String>();
    EditText tax_text;
    Button loginBtn;
    Boolean ap;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_screen_psf);

        tax_text = findViewById(R.id.amka_LG);
        loginBtn = findViewById(R.id.login_Btn_psf);

    }
    public void onClick(View v) {

        try {
            OkHttpHandler okHttpHandler = new OkHttpHandler();
            cbList = okHttpHandler.withdrawTax();
        } catch (Exception e) {
            e.printStackTrace();
        }
        ap = false;
        for (int i=0; i<cbList.size(); i++) {
            if (cbList.get(i).equals(tax_text)){
                ap= true;
            }
        }
        if(ap){
            Intent intent = new Intent(getApplicationContext(), MainPSF.class);
            startActivity(intent);
            Toast.makeText(this, "success", Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(this, "try again", Toast.LENGTH_SHORT).show();
        }

    }



    }
