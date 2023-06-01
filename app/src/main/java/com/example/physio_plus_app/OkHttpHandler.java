package com.example.physio_plus_app;


import android.os.StrictMode;

import org.joda.time.DateTime;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.joda.time.LocalDateTime;
import org.joda.time.format.DateTimeFormat;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;

import okhttp3.*;
public class OkHttpHandler {

    public OkHttpHandler() {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
    }

    ArrayList<RequestObj> testPrint(String url) throws Exception {
        ArrayList<RequestObj> reqList = new ArrayList<>();
        OkHttpClient client = new OkHttpClient().newBuilder().build();
        RequestBody body = RequestBody.create(" ", MediaType.parse("text/plain"));
        Request request = new Request.Builder().url(url).method("POST", body).build();
        Response response = client.newCall(request).execute();
        String data = response.body().string();
        System.out.println("My response: " + data);
        org.joda.time.format.DateTimeFormatter f = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss");
        try {
//            JSONObject json = new JSONObject(data);
            JSONArray jsonArray = new JSONArray(data);


            for (int i=0; i<jsonArray.length(); i++) {
                JSONObject json = jsonArray.getJSONObject(i);
                int id = json.getInt("id");
                String physio_center = json.getString("physio_center");
                String patient_id = json.getString("patient_id");
                LocalDateTime date_time = f.parseLocalDateTime(json.getString("date_time"));
                reqList.add(new RequestObj(id, physio_center, patient_id, date_time));
            }
//            Iterator<String> keys = json.keys();
//            int i = 0;
//            while (keys.hasNext()) {
//                String key = String.valueOf(i);
//                int id = json.getJSONObject(key).getInt("id");
//                String physio_center = json.getJSONObject(key).getString("physio_center");
//                String patient_id = json.getJSONObject(key).getString("patient_id");
//                LocalDateTime date_time = f.parseLocalDateTime(json.getJSONObject(key).getString("date_time"));
//                reqList.add(new RequestObj(id, physio_center, patient_id, date_time));
//                i++;
//            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return reqList;
    }
}
