package com.example.physio_plus_app.R8;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public abstract class ServiceHttpHandlerR8 extends HttpHandlerR8 {
    static ArrayList<ServiceR8> request(String url, RequestParamsR8 params) throws Exception {
        JSONArray jsonArray = makeRequest(url, params);
        if (jsonArray == null) return null;
        ArrayList<ServiceR8> servicesArray = new ArrayList<>();
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject obj = jsonArray.getJSONObject(i);
            servicesArray.add(new ServiceR8(
                    obj.getString("title"),
                    obj.getString("description"),
                    obj.getString("code"),
                    obj.getInt("cost")
            ));
        }
        return servicesArray;
    }
}
