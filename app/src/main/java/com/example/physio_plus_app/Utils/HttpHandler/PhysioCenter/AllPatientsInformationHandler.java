package com.example.physio_plus_app.Utils.HttpHandler.PhysioCenter;

import com.example.physio_plus_app.Utils.Entities.Patient;
import com.example.physio_plus_app.Utils.HttpHandler.HttpHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.List;

import okhttp3.FormBody;
import okhttp3.RequestBody;
import okhttp3.Response;

public abstract class AllPatientsInformationHandler extends HttpHandler {
    private static final String FOLDER_NAME = "R5/";
    private static final String FILE_NAME = "logHistory.php";

    public static void request(List<Patient> userList) {
        userList.clear();
        RequestBody body = new FormBody.Builder().build();
        try {
            Response response = postRequest(FOLDER_NAME + FILE_NAME, body);
            String responseBody = response.body().string();
            JSONArray jsonArray = new JSONArray(responseBody);
            if (jsonArray.length() > 0) {
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    String firstName = jsonObject.optString("first_name");
                    String lastName = jsonObject.optString("last_name");
                    String Amka = jsonObject.optString("ssrn");
                    String address = jsonObject.optString("address");

                    Patient user = new Patient(firstName, lastName, Amka, address);
                    userList.add(user);
                }
            }
        } catch (JSONException | IOException e) {
            LogEMessage("Exception " + AllPatientsInformationHandler.class);
        }
    }
}
