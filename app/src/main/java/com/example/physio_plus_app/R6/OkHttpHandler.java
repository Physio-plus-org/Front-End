package com.example.physio_plus_app.R6;

import android.os.StrictMode;

import com.example.physio_plus_app.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class OkHttpHandler {
    final int image = R.drawable.baseline_person_24;
    public OkHttpHandler() {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
    }

    List<Event> reqEvents(String url) throws Exception {
        ArrayList<Event> events = new ArrayList<>();
        OkHttpClient client = new OkHttpClient().newBuilder().build();
        RequestBody body = RequestBody.create(" ", MediaType.parse("text/plain"));
        Request request = new Request.Builder().url(url).method("POST", body).build();
        Response response = client.newCall(request).execute();
        String data = response.body().string();
        System.out.println("My response: " + data);
        DateTimeFormatter f = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        try {
            JSONArray jsonArray = new JSONArray(data);

            for (int i=0; i<jsonArray.length(); i++) {
                JSONObject json = jsonArray.getJSONObject(i);
                int event_id = json.getInt("id");
                String physio_center = json.getString("physio_center");
                String patient_id = json.getString("patient_id");
                LocalDateTime date_time = LocalDateTime.parse(json.getString("date_time"), f);
                String status = json.getString("status");
                events.add(new Event(event_id, physio_center, patient_id, date_time, status, image));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return events;
    }

    public String requestCancel(String url) throws Exception {
        OkHttpClient client = new OkHttpClient().newBuilder().build();
        RequestBody body = RequestBody.create(" ", MediaType.parse("text/plain"));
        Request request = new Request.Builder().url(url).method("POST", body).build();
        Response response = client.newCall(request).execute();
        String data = response.body().string();
        return data;
    }

    public String requestCompleted(String url) throws Exception {
        OkHttpClient client = new OkHttpClient().newBuilder().build();
        RequestBody body = RequestBody.create(" ", MediaType.parse("text/plain"));
        Request request = new Request.Builder().url(url).method("POST", body).build();
        Response response = client.newCall(request).execute();
        String data = response.body().string();
        return data;
    }
}
