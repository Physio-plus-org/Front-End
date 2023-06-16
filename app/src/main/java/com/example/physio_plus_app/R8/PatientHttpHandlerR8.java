package com.example.physio_plus_app.R8;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public abstract class PatientHttpHandlerR8 extends HttpHandlerR8 {
    static ArrayList<PatientR8> request(String url, RequestParamsR8 params) throws Exception {
        JSONArray jsonArray = makeRequest(url, params);
        if (jsonArray == null) return null;
        ArrayList<PatientR8> patientsArray = new ArrayList<>();
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject obj = jsonArray.getJSONObject(i);
            patientsArray.add(new PatientR8(
                    obj.getString("first_name"),
                    obj.getString("last_name"),
                    obj.getString("soc_sec_reg_num")
            ));
        }
        return patientsArray;
    }
}
