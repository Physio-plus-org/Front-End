package com.example.physio_plus_app;

import androidx.appcompat.app.AppCompatActivity;


import android.os.Bundle;

import android.util.Log;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;




public class MainActivity extends AppCompatActivity {

    TextView name_tv;
    TextView age_tv;
    TextView birthday_tv;
    TextView address_tv;
    TextView date_tv;
    public OkHttpClient client;


    String currentDate = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(new Date());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide(); // Hide the action bar

        Log.d("MainActivity", "onCreate method called");


        name_tv = findViewById(R.id.name_tv);
        age_tv = findViewById(R.id.age_tv);
        birthday_tv = findViewById(R.id.yearsold_tv);
        address_tv = findViewById(R.id.address_tv);
        date_tv = findViewById(R.id.date_tv);

        date_tv.setText(currentDate);

        client =  new OkHttpClient();


        Request request = new Request.Builder()
                .url("http://192.168.56.1/patients/displaypatients.php")
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.d("MainActivity","Call failure!");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Log.d("MainActivity","Call Responded!");
                if (response.isSuccessful()) {
                    String json = response.body().string();
                    Gson gson = new Gson();
                    Log.d("MainActivity", "Server response: " + json);

                    try {
                        JSONObject jsonObject = new JSONObject(json);
                        String name = jsonObject.getString("name");
                        String address = jsonObject.getString("address");
                        String age = jsonObject.getString(("age"));
                        // Use the name variable as needed
                        Log.d("MainActivity", "Name set to: " + name);
                        Log.d("MainActivity","Address set to:" + address);
                        Log.d("MainActivity","Age set to:" + age);

                        runOnUiThread(() -> name_tv.setText(name));
                        runOnUiThread(()->address_tv.setText(address));
                        runOnUiThread(()->age_tv.setText(age));
                    } catch (JSONException e) {
                        // Handle JSON parsing error
                        Log.e("MainActivity", "JSON parsing error", e);
                    }
                } else {
                    //code
                    Log.e("MainActivity", "Error response: " + response.code());
                }
                response.close();
            }


        });



    }






}