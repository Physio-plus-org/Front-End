package com.example.physio_plus_app.R2;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.physio_plus_app.Main_PSF.MainPSF;
import com.example.physio_plus_app.R;

import java.io.IOException;


public class R2 extends AppCompatActivity {
    EditText name,desc,code,price;
    Button conf;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.r2_activity);

        code = findViewById(R.id.codein);
        name = findViewById(R.id.namein);
        desc = findViewById(R.id.descin);
        price = findViewById(R.id.pricein);
        conf = findViewById(R.id.confirm);

        //Topbar
        ImageView goBackButton = findViewById(R.id.back_button);
        goBackButton.setOnClickListener(v -> finish());

    }
    public void onClick(View v) {
        String c,n,d,p;
        c = code.getText().toString();
        n = name.getText().toString();
        d = desc.getText().toString();
        p = price.getText().toString();
        try {
            OkHttpHandlerR2.insertData(new ParamsR2(n,d,c,p));
            Toast.makeText(this, "Service successfully added", Toast.LENGTH_LONG).show();

            code.getText().clear();
            name.getText().clear();
            desc.getText().clear();
            price.getText().clear();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}