package com.example.physio_plus_app.Main_PSF;

import android.util.Log;

import com.example.physio_plus_app.Utils.HttpHandler.HttpHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class OkHttpHandlerFacilitiesDetails {

    public static void withdrawData() throws IOException {
  //        StrictMode.setThreadPolicy(policy);
//        final String myIP = "192.168.1.6";
//        String url = "http://"+myIP+"/request.php";
//
//        OkHttpClient client = new OkHttpClient().newBuilder().build();
//        String url = "https://physioplus.000webhostapp.com/R_2_5/facilities.php";
//        FormBody.Builder builder = new FormBody.Builder();
//        RequestBody body = builder.build();
        try {
            Response response = HttpHandler.postRequest("R_2_5/facilities.php", null);
            final String responseBody = response.body().string();
            JSONArray jsonArray = new JSONArray(responseBody);
            if (jsonArray.length() > 0) {
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    String title = jsonObject.getString("title");
                    String code = jsonObject.getString("code");
                    String description = jsonObject.getString("description");
                    double cost = jsonObject.getDouble("cost");
                    Service service = new Service(title, code, description, cost);
                    MainPSF.services.add(service);
                    //MainPSF.selectedView.put(title,cost);
                }
            }
        } catch (IOException ignored) {

        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }
}