package com.example.physio_plus_app.Utils.HttpHandler;

import android.os.StrictMode;
import android.util.Log;

import java.io.IOException;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public abstract class HttpHandler {
    private static OkHttpClient client;
    protected static final String HOST_NAME = "https://physioplus.000webhostapp.com/";
    protected static void setPolicy() {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
    }
    public static void initialize() {
        setPolicy();
        client = HttpClientBuilder.buildClient();
    }
    protected static Response postRequest(String file_path, RequestBody body) throws IOException {
        String url = HOST_NAME + file_path;
        LogDMessage(url);
        if (body == null) {
            body =  new FormBody.Builder().build();
        }
        Request request = new Request.Builder()
                    .url(url)
                    .post(body)
                    .build();
        return client.newCall(request).execute();
    }
    protected static void LogDMessage(String message) {
        Log.d("HttpHandler", message);
    }
    protected static void LogEMessage(String message) {
        Log.e("HttpHandler", message);
    }
}
