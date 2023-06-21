package com.example.physio_plus_app.LoginPatient;

import com.example.physio_plus_app.Utils.Entities.Patient;

import okhttp3.FormBody;
import okhttp3.RequestBody;

public abstract class OkHttpHandler {

    public static Patient withdrawId(String taxText) throws Exception {

        FormBody.Builder builder = new FormBody.Builder();

        builder.add("ssrn", taxText);

        RequestBody body = builder.build();

//        try {
//            Response response = HttpHandler.postRequest("login/readPatientid.php", body);
//            final String responseBody = response.body().string();
//            JSONObject json = new JSONObject(responseBody);
////            Log.e("Response", responseBody);
//            if(json.has("ssrn")){
//
//                return new Patient(json);
//            }
//
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
        return null;
    }

}
