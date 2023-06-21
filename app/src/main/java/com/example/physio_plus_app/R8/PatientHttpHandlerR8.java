package com.example.physio_plus_app.R8;

import com.example.physio_plus_app.Pararms.Patient;
import com.example.physio_plus_app.Pararms.RequestParams;

import org.json.JSONArray;
import org.json.JSONObject;

public abstract class PatientHttpHandlerR8 extends HttpHandlerR8 {
    static Patient request(String url, RequestParams params) throws Exception {
        JSONArray jsonArray = makeRequest(url, params);
        if (jsonArray == null) return null;
        //        for (int i = 0; i < jsonArray.length(); i++) {
//            JSONObject obj = jsonArray.getJSONObject(i);
//            patientsArray.add(new PatientR8(
//                    obj.getString("first_name"),
//                    obj.getString("last_name"),
//                    obj.getString("soc_sec_reg_num")
//            ));
//        }
//        return patientsArray;
        JSONObject jsonObject = jsonArray.getJSONObject(0);
        return new Patient(
                jsonObject.getString("first_name"),
                jsonObject.getString("last_name"),
                jsonObject.getString("ssrn"),
                jsonObject.getString("address")
        );
    }

}
