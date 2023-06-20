package com.example.physio_plus_app.LoginCenter;

import com.example.physio_plus_app.R10.PhysioCenterR10;
import com.example.physio_plus_app.Utils.HttpHandler.HttpHandler;

import org.json.JSONException;
import org.json.JSONObject;

import okhttp3.FormBody;
import okhttp3.RequestBody;
import okhttp3.Response;

public abstract class OkHttpHandler {

    public static PhysioCenterR10 withdrawTax(String taxText) throws Exception {

        FormBody.Builder builder = new FormBody.Builder();

        builder.add("tax_id_number", taxText);

        RequestBody body = builder.build();

        try {
            Response response = HttpHandler.postRequest("login/read.php", body);
            final String responseBody = response.body().string();
            JSONObject json = new JSONObject(responseBody);
//            Log.e("Response", responseBody);
            if(json.has("tax_id_number")){

                return new PhysioCenterR10(json);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

}
