package com.example.physio_plus_app.R4;

import android.util.Log;
import android.widget.TextView;

import androidx.annotation.NonNull;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class DisplayInfo {

    TextView name_tv;
    TextView age_tv;
    TextView address_tv;
    TextView date_tv;

    String url;
    String amka;
    OkHttpClient client;

    String currentDate = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(new Date());

    public DisplayInfo(String url, String amka, OkHttpClient client, TextView name, TextView age, TextView address, TextView date){

        this.amka = amka;
        this.date_tv = date;
        this.name_tv = name;
        this.address_tv = address;
        this.age_tv = age;
        this.url = url;
        date_tv.setText(currentDate);
        this.client = client;


        Request request = new Request.Builder().url(url).build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.d("MainActivity","Call failure!"+ e.getMessage());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Log.d("MainActivity","Call Responded!");
                if (response.isSuccessful()) {
                    String json = response.body().string();
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

                        name_tv.post(()->name_tv.setText(name));
                        address_tv.post(()->address_tv.setText(address));
                        age_tv.post(()->age_tv.setText(age));
                    } catch (JSONException e) {
                        // Handle JSON parsing error
                        Log.e("MainActivity", "JSON parsing error", e);
                    }
                } else {
                    //code
                    Log.e("MainActivity", "Error response: " + response.code());
                }
                response.close();
                Log.d("DisplayInfo", "Responce closed");
            }


        });



    }


    public void sendPatientNameToServer(String amka) {
        RequestBody requestBody = new FormBody.Builder()
                .add("amka", amka)
                .build();

        Request request = new Request.Builder()
                .url(url)
                .post(requestBody)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                // Handle network request failure
                Log.e("DisplayInfo", "Failed to send patient name: " + e.getMessage());
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) {
                // Handle network request success
                try (response) {
                    if (response.isSuccessful()) {
                        // Process the response if needed
                        assert response.body() != null;
                        String responseData = response.body().string();
                        Log.d("DisplayInfo", "Response: " + responseData);
                    } else {
                        // Handle unsuccessful response
                        Log.e("DisplayInfo", "Failed to send patient name. Response code: " + response.code());
                    }
                } catch (IOException e) {
                    Log.e("DisplayInfo", "Exception occurred while processing network response: " + e.getMessage());
                }
            }
        });

    }
}
