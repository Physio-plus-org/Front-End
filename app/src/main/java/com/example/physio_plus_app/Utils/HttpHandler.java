package com.example.physio_plus_app.Utils;

import android.os.StrictMode;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public interface HttpHandler {
    StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
    OkHttpClient client = new OkHttpClient();
    String host = "192.168.1.2";

    static Response makeRequest(String fileName, RequestBody body) throws IOException {
        StrictMode.setThreadPolicy(policy);
        String url = host + "/" + fileName;
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();
        return client.newCall(request).execute();
    }
}
