package com.example.physio_plus_app;

import android.os.StrictMode;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public abstract class HttpHandler {
    private static final StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();

    protected static JSONArray request(String url) throws IOException, JSONException {
        StrictMode.setThreadPolicy(policy);
        OkHttpClient client = new OkHttpClient().newBuilder().build();
        Request request = new Request.Builder().url(url).get().build();
        Response response = client.newCall(request).execute();
        assert response.body() != null;
        String data = response.body().string();
        JSONObject jsonObject = new JSONObject(data);
        return jsonObject.getJSONArray("history");
    }
}
