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

    public String loghistory(String namePatient,String surnamePatient, String amkaPatient, String addressPatient) throws IOException {
        final String URL = "http://192.168.1.248/logHistory.php";

        OkHttpClient client = new OkHttpClient().newBuilder().build();

        FormBody.Builder builder = new FormBody.Builder();
        builder.add("namep", namePatient);
        builder.add("surname", surnamePatient);
        builder.add("soc_sec_reg_num", amkaPatient);
        builder.add("address", addressPatient);

        RequestBody body = builder.build();
        Request request = new Request.Builder()
                .url(URL)
                .post(body)
                .build();

        Response response = client.newCall(request).execute();

        String responseBody = response.body().string();
        return responseBody;
    }
}