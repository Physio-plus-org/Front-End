package com.example.physio_plus_app;

import android.os.*;

import java.io.IOException;

import okhttp3.*;
public class OkHttpHandler {
    public OkHttpHandler() {
        StrictMode.ThreadPolicy policy = new
                StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
    }

    public void loghistory(String fullnamePatient, String amkaPatient, String addressPatient) throws IOException {
        final String URL = "http://192.168.1.248/logHistory.php";

        OkHttpClient client = new OkHttpClient().newBuilder().build();

        // Build the request body with the parameters
        MediaType mediaType = MediaType.parse("application/x-www-form-urlencoded");
        String requestBodyString = "fullname=" + fullnamePatient + "&soc_sec_reg_num=" + amkaPatient + "&address=" + addressPatient;
        RequestBody body = RequestBody.create(mediaType, requestBodyString);

        Request request = new Request.Builder()
                .url(URL)
                .post(body)
                .build();

        Response response = client.newCall(request).execute();

        // Handle the response as needed
        // For example, you can get the response body as a string:
        String responseBody = response.body().string();
    }
}