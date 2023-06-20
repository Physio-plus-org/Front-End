package com.example.physio_plus_app.LoginPatient;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.physio_plus_app.R;
import com.example.physio_plus_app.R9.R9;

public class login_screen_patient extends AppCompatActivity {

    EditText id_text;
    Button loginBtn;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_screen_patient);

        id_text = findViewById(R.id.amka_LG);
        loginBtn = findViewById(R.id.login_Btn_LG);

    }
    public void onClick(View view) {

        try {
            OkHttpHandler.withdrawId(id_text.getText().toString());
        } catch (Exception e) {
            e.printStackTrace();
        }

        Intent intent = new Intent(getApplicationContext(), R9.class);
        startActivity(intent);

    }

}
