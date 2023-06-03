package com.example.physio_plus_app;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;


public class R2 extends AppCompatActivity {
    EditText name,desc,code,price;
    Button conf;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        code = findViewById(R.id.codein);
        name = findViewById(R.id.namein);
        desc = findViewById(R.id.descin);
        price = findViewById(R.id.pricein);
        conf = findViewById(R.id.confirm);
    }

    public void onClick(View view) {
        String c,n,d,p;
        c = code.getText().toString();
        n = name.getText().toString();
        d = desc.getText().toString();
        p = price.getText().toString();
        try {
            OkHttpHandlerR2.insertData(new ParamsR2(n,d,c,p));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}