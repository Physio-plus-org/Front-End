package com.example.physio_plus_app.R10;

import org.json.JSONException;
import org.json.JSONObject;

public class PatientR10 {
    private String patientId;
    private String firstName;
    private String lastName;
    public PatientR10(JSONObject jsonObject) throws JSONException {
        this.patientId = jsonObject.get("soc_sec_reg_num").toString();
        this.firstName = jsonObject.get("first_name").toString();
        this.lastName = jsonObject.get("last_name").toString();
    }
}
