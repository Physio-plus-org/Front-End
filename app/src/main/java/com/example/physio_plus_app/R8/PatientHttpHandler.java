package com.example.physio_plus_app;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public abstract class PatientHttpHandler extends HttpHandlerR8 {
    static ArrayList<Patient> request(String url, RequestParams params) throws Exception {
        JSONArray jsonArray = makeRequest(url, params);
        if (jsonArray == null) return null;
        ArrayList<Patient> patientsArray = new ArrayList<>();
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject obj = jsonArray.getJSONObject(i);
            patientsArray.add(new Patient(
                    obj.getString("first_name"),
                    obj.getString("last_name"),
                    obj.getString("soc_sec_reg_num")
            ));
        }
        return patientsArray;
    }
}
