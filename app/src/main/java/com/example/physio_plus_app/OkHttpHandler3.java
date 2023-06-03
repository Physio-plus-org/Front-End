package com.example.physio_plus_app;

import android.os.*;

import java.io.IOException;

import okhttp3.*;
public abstract class OkHttpHandler3 {
    public static StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();

    public static String loghistory(ParamsR3 params) throws IOException {
        StrictMode.setThreadPolicy(policy);
        final String URL = "http://192.168.1.248/logHistory.php";

        OkHttpClient client = new OkHttpClient().newBuilder().build();

        FormBody.Builder builder = new FormBody.Builder();
        builder.add("namep", params.getnamePatient());
        builder.add("surname", params.getSurnamePatient());
        builder.add("soc_sec_reg_num", params.getAmkaPatient());
        builder.add("address", params.getAddressPatient());

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