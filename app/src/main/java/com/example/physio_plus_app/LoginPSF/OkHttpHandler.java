package com.example.physio_plus_app.LoginPSF;

import android.os.StrictMode;

import com.example.physio_plus_app.Utils.HttpHandler.HttpHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import okhttp3.Response;

public class OkHttpHandler {

    public OkHttpHandler() {
        StrictMode.ThreadPolicy policy = new
                StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
    }
    ArrayList<String> withdrawTax() throws Exception {

        ArrayList<String> cbList = new ArrayList<>();

        try {
            Response response = HttpHandler.postRequest("login/read.php", null);
            final String responseBody = response.body().string();
            JSONArray jsonArray = new JSONArray(responseBody);
            if (jsonArray.length() > 0) {
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    String tax = jsonObject.getString("tax_id_number");
                    cbList.add(tax);
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return cbList;
    }

}
