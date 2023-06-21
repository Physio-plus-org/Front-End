package com.example.physio_plus_app.Utils.HttpHandler.PSF;

import com.example.physio_plus_app.Utils.Entities.Service;
import com.example.physio_plus_app.Utils.HttpHandler.HttpHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Response;

public abstract class ServicesHandler extends HttpHandler {
    private static final String FOLDER_NAME = "R_2_5/";
    private static final String FILE_NAME = "facilities.php";

    public static void request(ArrayList<Service> services) throws IOException {
        try {
            Response response = postRequest(FOLDER_NAME+FILE_NAME, null);
            String responseBody = response.body().string();
            JSONArray jsonArray = new JSONArray(responseBody);
            if (jsonArray.length() > 0) {
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    String title = jsonObject.getString("title");
                    String code = jsonObject.getString("code");
                    String description = jsonObject.getString("description");
                    double cost = jsonObject.getDouble("cost");
                    Service service = new Service(title, code, description, cost);
                    services.add(service);
                }
            }
        } catch (JSONException | IOException e) {
            LogEMessage("Exception " + ServicesHandler.class);
        }
    }
}
