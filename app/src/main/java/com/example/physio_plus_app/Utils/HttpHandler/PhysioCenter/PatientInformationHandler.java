package com.example.physio_plus_app.Utils.HttpHandler.PhysioCenter;

import android.util.Log;

import com.example.physio_plus_app.Utils.Entities.Patient;
import com.example.physio_plus_app.Utils.HttpHandler.HttpHandler;
import com.example.physio_plus_app.Utils.RequestParams;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.FormBody;
import okhttp3.RequestBody;
import okhttp3.Response;

public abstract class PatientInformationHandler extends HttpHandler {
    private static final String FOLDER_NAME = "R4/";
    private static final String FILE_NAME = "displaypatients.php";

    public static Patient request(RequestParams params){
        RequestBody requestBody = new FormBody.Builder()
                .add("amka", params.get("amka"))
                .build();
        try {
            Response response = postRequest(FOLDER_NAME+FILE_NAME, requestBody);
            String json = response.body().string();
            Log.d("MainActivity", "Server response: " + json);
            JSONArray jsonArray = new JSONArray(json);
            return new Patient(((JSONObject)jsonArray.get(0)));
        } catch (IOException | JSONException e) {
            LogEMessage("Exception " + PatientInformationHandler.class);
        }
        return null;
    }
}
