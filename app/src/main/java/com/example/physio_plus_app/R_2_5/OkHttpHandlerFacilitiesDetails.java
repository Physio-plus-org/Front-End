package com.example.physio_plus_app.R_2_5;

import android.os.StrictMode;

import com.example.physio_plus_app.R5.R5;
import com.example.physio_plus_app.R5.User;
import com.example.physio_plus_app.Utils.HttpHandler.HttpHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class OkHttpHandlerFacilitiesDetails {

    public static ArrayList<Service> withdrawData() {
  //        StrictMode.setThreadPolicy(policy);
//        final String myIP = "192.168.1.6";
//        String url = "http://"+myIP+"/request.php";
//
//        OkHttpClient client = new OkHttpClient().newBuilder().build();
        String url = "https://physioplus.000webhostapp.com/R_2_5/facilities.php";
        ArrayList<Service> services = new ArrayList<>();
        OkHttpClient client = new OkHttpClient();
        FormBody.Builder builder = new FormBody.Builder();
        RequestBody body = builder.build();
        Request request = new Request.Builder().url(url).post(body).build();

        try {
            Response response = client.newCall(request).execute();
            final String responseBody = response.body().string();
            JSONArray jsonArray = new JSONArray(responseBody);
            if (jsonArray.length() > 0) {
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    String title = jsonObject.optString("title");
                    String code = jsonObject.optString("code");
                    String description = jsonObject.optString("description");
                    String cost = jsonObject.optString("cost");
                    Service service = new Service(code, title, description, cost);
                    services.add(service);
                }
            }
        } catch (IOException ignored) {

        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
        return services;
    }
}