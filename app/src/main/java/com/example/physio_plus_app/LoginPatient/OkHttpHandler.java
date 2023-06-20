package com.example.physio_plus_app.LoginPatient;

import com.example.physio_plus_app.R8.PatientR8;
import com.example.physio_plus_app.Utils.HttpHandler.HttpHandler;

import org.json.JSONException;
import org.json.JSONObject;

import okhttp3.FormBody;
import okhttp3.RequestBody;
import okhttp3.Response;

public abstract class OkHttpHandler {

    public static PatientR8 withdrawId(String taxText) throws Exception {

        FormBody.Builder builder = new FormBody.Builder();

        builder.add("ssrn", taxText);

        RequestBody body = builder.build();

        try {
            Response response = HttpHandler.postRequest("login/readPatientid.php", body);
            final String responseBody = response.body().string();
            JSONObject json = new JSONObject(responseBody);
//            Log.e("Response", responseBody);
            if(json.has("ssrn")){

                return new PatientR8(json);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

}