package com.example.physio_plus_app.Utils.HttpHandler.Login;

import com.example.physio_plus_app.Utils.Entities.Patient;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.FormBody;
import okhttp3.RequestBody;

public class PatientLoginHandler extends LoginHandler {
    private static final String FILE_NAME = "readPatientid.php";

    public static Patient request(String taxText) {
        RequestBody body = new FormBody.Builder()
                .add("ssrn", taxText)
                .build();
        try {
            String responseBody = loginRequest(FILE_NAME, body);
            JSONObject json = new JSONObject(responseBody);
            if(json.has("ssrn")){
                return new Patient(json);
            } else {
                InvalidJsonMessage();
            }
        } catch (JSONException | IOException e) {
            ExceptionClassMessage(PhysioCenterLoginHandler.class);
        }
        return null;
    }
}
