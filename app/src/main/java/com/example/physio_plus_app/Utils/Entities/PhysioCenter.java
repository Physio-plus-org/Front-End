package com.example.physio_plus_app.Utils.Entities;

import android.widget.LinearLayout;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class PhysioCenter implements User{
    private String centerId;
    private String name;
    private String address;
    private List<Patient> patients = new ArrayList<>();
    public PhysioCenter(JSONObject jsonObject) throws JSONException {
        this.centerId = jsonObject.get("tax_id_number").toString();
        this.name = jsonObject.get("name").toString();
        this.address = jsonObject.get("address").toString();
    }

    public void setPatients(List<Patient> array) {
        patients = array;
    }

    public Patient getPatient(String id) {
        for (Patient patient : patients) {
            if (id.equals(patient.getIdNumber())) {
                return patient;
            }
        }
        return null;
    }

    public void show(LinearLayout linearLayout) {
        TextView textView = new TextView(linearLayout.getContext());
        textView.setLayoutParams(new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.WRAP_CONTENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                )
        );
        textView.setText(this.name);
        linearLayout.addView(textView);
    }
}
