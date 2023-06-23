package com.example.physio_plus_app.Utils.HttpHandler.PhysioCenter;

import com.example.physio_plus_app.Utils.Entities.Service;
import com.example.physio_plus_app.Utils.HttpHandler.HttpHandler;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import okhttp3.FormBody;
import okhttp3.RequestBody;
import okhttp3.Response;

public abstract class AllServicesHandler extends HttpHandler {
    private static final String FOLDER_NAME = "R8/";
    private static final String FILE_NAME = "serviceRequest.php";

    public static void request(ArrayList<Service> servicesArray) throws Exception {
        RequestBody body = new FormBody.Builder().build();
        Response response = postRequest(FOLDER_NAME + FILE_NAME, body);
        JSONArray jsonArray = new JSONArray(response.body().string());
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject obj = jsonArray.getJSONObject(i);
            servicesArray.add(new Service(
                    obj.getString("title"),
                    obj.getString("code"),
                    obj.getString("description"),
                    obj.getInt("cost")
            ));
        }
    }
}
