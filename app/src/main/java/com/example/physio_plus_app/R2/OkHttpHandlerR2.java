package com.example.physio_plus_app.R2;

import android.os.StrictMode;

import com.example.physio_plus_app.Utils.HttpHandler.HttpHandler;

import java.io.IOException;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

public abstract class OkHttpHandlerR2 {
    private static final String file_folder = "R2/";
    private static final StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
    public static void insertData(ParamsR2 params) throws IOException {
//        StrictMode.setThreadPolicy(policy);
//        final String myIP = "192.168.1.6";
//        String url = "http://"+myIP+"/request.php";
//
//        OkHttpClient client = new OkHttpClient().newBuilder().build();

        String file_name = "request.php";
        FormBody.Builder builder = new FormBody.Builder();

        if (params != null) {
            builder.add("title", params.getTitle());
            builder.add("desc", params.getDesc());
            builder.add("code", params.getCode());
            builder.add("cost", params.getPrice());
        }
        RequestBody body = builder.build();
        HttpHandler.postRequest(file_folder + file_name, body);
//        Request request = new Request.Builder()
//                .url(url)
//                .post(body)
//                .build();
//        client.newCall(request).execute();
    }
}
