package com.example.physio_plus_app.LoginPSF;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.physio_plus_app.R;
import com.example.physio_plus_app.R6.R6;

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
        loginBtn.setOnClickListener(this::onClick);

    }
    public void onClick(View view) {

        try {
            OkHttpHandler.withdrawTax(tax_text.getText().toString());
        } catch (Exception e) {
            e.printStackTrace();
        }

        Intent intent = new Intent(getApplicationContext(), R6.class);
        startActivity(intent);

    }

}
