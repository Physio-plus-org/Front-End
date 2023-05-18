package com.example.physio_plus_app;

import org.json.JSONException;
import org.json.JSONObject;

public class Patient {
    private String patientId;
    private String firstName;
    private String lastName;
    public Patient(JSONObject jsonObject) throws JSONException {
        this.patientId = jsonObject.get("soc_sec_reg_num").toString();
        this.firstName = jsonObject.get("first_name").toString();
        this.lastName = jsonObject.get("last_name").toString();
    }
}
