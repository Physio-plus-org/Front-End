package com.example.physio_plus_app;

import android.content.res.Resources;
import android.os.StrictMode;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.EOFException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Hashtable;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public abstract class HttpHandler {
    private static final StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
    protected static JSONArray request(String url, Hashtable<String,String> params) throws IOException, JSONException, ServerResponseException {
        StrictMode.setThreadPolicy(policy);
        OkHttpClient client = new OkHttpClient().newBuilder().build();
        RequestBody requestBody = new FormBody(new ArrayList<>(params.keySet()), new ArrayList<>(params.values()));
        Request request = new Request.Builder().url(url).method("POST", requestBody).build();
        Response response = client.newCall(request).execute();
        assert response.body() != null;
        if (response.code() == 200) {
            String data = response.body().string();
            Log.d("response body", data);
            JSONObject jsonObject = new JSONObject(data);
            return jsonObject.getJSONArray("history");
        } else {
            Log.e("response body", response.body().string());
            throw new ServerResponseException("Error 404");
        }
    }
}
