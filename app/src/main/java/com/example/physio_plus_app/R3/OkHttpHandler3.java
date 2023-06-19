package com.example.physio_plus_app.R3;

import android.os.*;

import com.example.physio_plus_app.Utils.HttpHandler.HttpHandler;

import java.io.IOException;

import okhttp3.*;
public abstract class OkHttpHandler3 {
    public static StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
    private static final String file_folder = "R3/";
    public static String loghistory(ParamsR3 params) throws IOException {
//        StrictMode.setThreadPolicy(policy);
//        final String URL = "http://192.168.1.248/logHistory.php";
//
//        OkHttpClient client = new OkHttpClient().newBuilder().build();
        String file_name = "logHistory.php";
        FormBody.Builder builder = new FormBody.Builder();
        builder.add("namep", params.getnamePatient());
        builder.add("surname", params.getSurnamePatient());
        builder.add("soc_sec_reg_num", params.getAmkaPatient());
        builder.add("address", params.getAddressPatient());

        RequestBody body = builder.build();
        Response response = HttpHandler.postRequest(file_folder + file_name, body);
//        Request request = new Request.Builder()
//                .url(URL)
//                .post(body)
//                .build();
//
//        Response response = client.newCall(request).execute();
//        assert response.body() != null;
        return response.body().string();
    }
}