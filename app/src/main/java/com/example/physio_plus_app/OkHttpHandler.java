package com.example.physio_plus_app;

import android.content.Context;
import android.os.StrictMode;
import android.util.Log;
import android.widget.Toast;

import java.io.IOException;
import java.util.Iterator;

import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class OkHttpHandler {
    public OkHttpHandler() {
        StrictMode.ThreadPolicy policy = new
                StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
    }

    public void insertData(String url, Params params) throws IOException {
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
