package com.example.physio_plus_app;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;


public class MainActivity extends AppCompatActivity {

    private final String myIP = "192.168.1.3";
    EditText name,desc,code,price;
    Button conf;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        code = (EditText) findViewById(R.id.codein);
        name = (EditText) findViewById(R.id.namein);
        desc = (EditText) findViewById(R.id.descin);
        price = (EditText) findViewById(R.id.pricein);
        conf = (Button) findViewById(R.id.confirm);

        conf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String c,n,d,p;
                c = code.getText().toString();
                n = name.getText().toString();
                d = desc.getText().toString();
                p = price.getText().toString();
                String ip = myIP;
                String url = "http://"+ip+":8080/request.php";
                OkHttpHandler okHttpHandler = new OkHttpHandler();
                try {
                    okHttpHandler.insertData(url, new Params(n,d,c,p));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }
}