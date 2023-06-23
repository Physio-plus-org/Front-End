package com.example.physio_plus_app.Utils.HttpHandler.PhysioCenter;

import android.util.Log;

import com.example.physio_plus_app.Utils.HttpHandler.HttpHandler;
import com.example.physio_plus_app.Utils.RequestParams;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;

import okhttp3.FormBody;
import okhttp3.RequestBody;
import okhttp3.Response;

public abstract class PatientSessionsHandler extends HttpHandler {
    private static final String FOLDER_NAME = "R4/";
    private static final String FILE_NAME = "displaysessions.php";

    public static HashMap<String, String> request(RequestParams params) {
        RequestBody requestBody = new FormBody.Builder()
                .add("amka", params.get("amka"))
                .build();

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", new Locale("Greek"));
        HashMap<String, String> map = new HashMap<>();
        try {
            Response response = postRequest(FOLDER_NAME + FILE_NAME, requestBody);
            String json = response.body().string();
            Log.d("MainActivity", "Server response: " + json);
            JSONArray sessionsArray = new JSONArray(json);
            for (int i = 0; i < sessionsArray.length(); i++) {
                JSONObject sessionObject = sessionsArray.getJSONObject(i);
                Date date = format.parse(sessionObject.get("date").toString());
                String notes = sessionObject.getString("notes");
                if (date != null) {
                    map.put(format.format(date), notes);
                }
            }
        } catch (IOException | JSONException | ParseException e) {
            throw new RuntimeException(e);
        }
        return map;
    }
}
