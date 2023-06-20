package com.example.physio_plus_app.R2;

import android.os.StrictMode;

import com.example.physio_plus_app.Utils.HttpHandler.HttpHandler;

import java.io.IOException;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public abstract class OkHttpHandlerR2 {
    private static final String file_folder = "R2/";
    private static final StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
    public static String insertData(ParamsR2 params) throws IOException {
//        StrictMode.setThreadPolicy(policy);
//        final String myIP = "192.168.1.6";
//        String url = "http://"+myIP+"/request.php";
//
//        OkHttpClient client = new OkHttpClient().newBuilder().build();
        String file_name = "request.php";
        FormBody.Builder builder = new FormBody.Builder();

        if (params != null) {
            builder.add("title", params.getTitle());
            builder.add("description", params.getDesc());
            builder.add("code", params.getCode());
            builder.add("cost", params.getPrice());
        }
        RequestBody body = builder.build();
        Response response = HttpHandler.postRequest(file_folder + file_name, body);
        return response.body().string();
//        Request request = new Request.Builder()
//                .url(url)
//                .post(body)
//                .build();
//        client.newCall(request).execute();
    }
}
