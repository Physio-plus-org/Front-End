package com.example.physio_plus_app.R8;

import com.example.physio_plus_app.Utils.Entities.Service;
import com.example.physio_plus_app.Utils.RequestParams;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public abstract class ServiceHttpHandlerR8 extends HttpHandlerR8 {
    static ArrayList<Service> request(String url, RequestParams params) throws Exception {
        JSONArray jsonArray = makeRequest(url, params);
        if (jsonArray == null) return null;
        ArrayList<Service> servicesArray = new ArrayList<>();
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject obj = jsonArray.getJSONObject(i);
            servicesArray.add(new Service(
                    obj.getString("title"),
                    obj.getString("description"),
                    obj.getString("code"),
                    obj.getInt("cost")
            ));
        }
        return servicesArray;
    }
}
