package com.example.physio_plus_app.Utils.HttpHandler.PhysioCenter;

import com.example.physio_plus_app.R;
import com.example.physio_plus_app.R6.EventR6;
import com.example.physio_plus_app.Utils.HttpHandler.HttpHandler;
import com.example.physio_plus_app.Utils.RequestParams;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public abstract class AllRequestsHandler extends HttpHandler {
    private static final String FOLDER_PATH = "R6/";
    private static final String FILE_NAME = "requestEvents.php";
    private static final int image = R.drawable.r6_baseline_person_24;

    public static List<EventR6> request(RequestParams params) throws IOException {
        ArrayList<EventR6> events = new ArrayList<>();
        RequestBody body = new FormBody.Builder()
                .add("range_start", params.get("range_start"))
                .add("range_end", params.get("range_end"))
                .add("status", params.get("status"))
                .build();
        Response response = postRequest(FOLDER_PATH + FILE_NAME, body);
        String data = response.body().string();
        if (data.isEmpty()) return null;
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
                events.add(new EventR6(event_id, physio_center, patient_id, date_time, status, image));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return events;
    }
}
