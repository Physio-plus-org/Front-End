package com.example.physio_plus_app.Utils.HttpHandler;

import android.os.StrictMode;
import android.util.Log;

import androidx.annotation.Nullable;

import java.io.IOException;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public interface HttpHandler {
    StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
    OkHttpClient client = new OkHttpClient();
    String host = "https://physioplus.000webhostapp.com/";

    static void setPolicy() {
        StrictMode.setThreadPolicy(policy);
    }

    static Response postRequest(String file_path, RequestBody body) throws IOException {
        String url = host + file_path;
        Log.e("url", url);
        if (body == null) {
            FormBody.Builder builder = new FormBody.Builder();
            body = builder.build();
        }
        Request request = new Request.Builder()
                    .url(url)
                    .post(body)
                    .build();
        return client.newCall(request).execute();
    }

    public static Response getRequest(String file_path, String parameters) throws IOException {
        String url = host + file_path + parameters;
        Request request = new Request.Builder()
                .url(url)
                .get()
                .build();
        return client.newCall(request).execute();
    }
}
