package com.example.physio_plus_app.LoginCenter;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.physio_plus_app.R;
import com.example.physio_plus_app.R6.R6;

public class LoginCenter extends AppCompatActivity {

    EditText tax_text;
    Button loginBtn;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_screen_physio_center);

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
