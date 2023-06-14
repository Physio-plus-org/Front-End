package com.example.physio_plus_app;

import android.os.StrictMode;

import org.joda.time.LocalDateTime;
import org.joda.time.format.DateTimeFormat;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import okhttp3.*;

public class OkHttpHandler {

    private  final String myIP = "192.168.2.6";

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
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return reqList;
    }

    public void sendRequestDate(String JsonString) throws Exception{
        URL myURL = new URL("http://"+myIP+"/physio_app_db/requestCreate.php");
        HttpURLConnection con = (HttpURLConnection)myURL.openConnection();
        con.setRequestMethod("POST");
        con.setRequestProperty("Content-Type", "application/json");
        con.setRequestProperty("Accept", "application/json");
        con.setDoOutput(true);

        try (OutputStream os = con.getOutputStream()) {
            byte[] input = JsonString.getBytes("utf-8");
            os.write(input, 0, input.length);
        }

        try (BufferedReader br = new BufferedReader(
                new InputStreamReader(con.getInputStream(), "utf-8"))) {
            StringBuilder response = new StringBuilder();
            String responseLine = null;
            while ((responseLine = br.readLine()) != null) {
                response.append(responseLine.trim());
            }
            System.out.println(response.toString());
        }

    }
}
