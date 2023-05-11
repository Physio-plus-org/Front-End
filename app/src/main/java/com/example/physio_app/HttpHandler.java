package com.example.physio_app;

import android.os.StrictMode;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class HttpHandler {



    public HttpHandler() {
        StrictMode.ThreadPolicy policy = new
                StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
    }

    OkHttpClient client = new OkHttpClient().newBuilder().build();
    RequestBody body = RequestBody.create("",
            MediaType.parse("text/plain"));
    Request request = new Request.Builder().url(url).method("POST",
            body).build();


}
