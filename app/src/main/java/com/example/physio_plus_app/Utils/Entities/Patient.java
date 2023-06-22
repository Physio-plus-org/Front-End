package com.example.physio_plus_app.Utils.Entities;

import androidx.annotation.NonNull;

import org.json.JSONException;
import org.json.JSONObject;

public class Patient implements User{
    private final String first_name;
    private final String last_name;
    private final String id_number;
    private final String address;
    public Patient(String fname, String lname, String pnumber, String address) {
        this.first_name = fname;
        this.last_name = lname;
        this.id_number = pnumber;
        this.address = address;
    }

    public Patient(JSONObject obj) throws JSONException {
        this.first_name = obj.getString("first_name");
        this.last_name = obj.getString("last_name");
        this.address = obj.getString("address");
        this.id_number = obj.getString("ssrn");
    }

    @NonNull
    @Override
    public String toString() {
        return first_name + " " + last_name + " " + id_number;
    }
    public String getFullName() {
        return this.last_name + " " + this.first_name;
    }
    public String getIdNumber() {
        return this.id_number;
    }
    public String getAddress() {return this.address;}

    public String getnamePatient() {
        return first_name;
    }

    public String getSurnamePatient(){return last_name;}

    @Override
    public String getId() {
        return this.id_number;
    }
}
