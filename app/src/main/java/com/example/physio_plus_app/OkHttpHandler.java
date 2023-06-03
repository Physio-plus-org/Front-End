package com.example.physio_plus_app;

import android.os.StrictMode;

import java.io.IOException;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

public abstract class OkHttpHandler {
    private static final StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
    public static void insertData(Params params) throws IOException {
        StrictMode.setThreadPolicy(policy);
        final String myIP = "192.168.1.6";
        String url = "http://"+myIP+"/request.php";

        OkHttpClient client = new OkHttpClient().newBuilder().build();
        FormBody.Builder builder = new FormBody.Builder();

        if (params != null) {
            builder.add("title", params.getTitle());
            builder.add("desc", params.getDesc());
            builder.add("code", params.getCode());
            builder.add("cost", params.getPrice());
        }
        RequestBody body = builder.build();
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();
        client.newCall(request).execute();
    }
}
