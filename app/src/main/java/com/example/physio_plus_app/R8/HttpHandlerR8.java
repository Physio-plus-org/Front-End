package com.example.physio_plus_app.R8;

import android.os.StrictMode;
import android.util.Log;

import org.json.JSONArray;

import java.util.Iterator;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public abstract class HttpHandlerR8 {
    protected static JSONArray makeRequest(String url, RequestParamsR8 params) throws Exception {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        OkHttpClient client = new OkHttpClient().newBuilder().build();
        FormBody.Builder builder = new FormBody.Builder();
        if (params != null) {
            Iterator<String> iterator = params.iterator();
            while (iterator.hasNext()) {
                String key = iterator.next();
                String value = params.get(key);
                builder.add(key, value);
            }
        }
        RequestBody body = builder.build();
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();
        try (Response response = client.newCall(request).execute()) {
            String data = response.body().string();
            Log.e("tag:", data);
            if (!data.isEmpty())
                return new JSONArray(data);
            return null;
        }
    }
}
