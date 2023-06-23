package com.example.physio_plus_app.Utils.HttpHandler.Login;

import com.example.physio_plus_app.Utils.Entities.PhysioCenter;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.FormBody;
import okhttp3.RequestBody;

public abstract class PhysioCenterLoginHandler extends LoginHandler {
    private static final String FILE_NAME = "read.php";

    public static PhysioCenter request(String taxText) {
        RequestBody body = new FormBody.Builder()
                .add("tax_id_number", taxText)
                .build();
        try {
            String responseBody = loginRequest(FILE_NAME, body);
            JSONObject json = new JSONObject(responseBody);
            if(json.has("tax_id_number")){
                return new PhysioCenter(json);
            } else {
                InvalidJsonMessage();
            }
        } catch (JSONException | IOException e) {
            ExceptionClassMessage(PhysioCenterLoginHandler.class);
        }
        return null;
    }
}
