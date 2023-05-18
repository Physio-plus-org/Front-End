package com.example.physio_plus_app;

import android.widget.EditText;

import org.json.JSONArray;
import org.json.JSONException;

import java.text.ParseException;

public class FinancialMove {
    private Patient patient;
    private PhysioCenter physioCenter;
    private Session session;
    public FinancialMove(Patient patient, PhysioCenter physioCenter, Session session) {
        this.patient = patient;
        this.physioCenter = physioCenter;
        this.session = session;
    }
    public FinancialMove(JSONArray jsonArray) throws JSONException, ParseException {
        Patient patient = new Patient(jsonArray.getJSONObject(0));
        PhysioCenter physioCenter = new PhysioCenter(jsonArray.getJSONObject(1));
        Session session = new Session(jsonArray.getJSONObject(3));
    }

    public void show(EditText resultView) {
        this.physioCenter.show(resultView);
        this.session.show(resultView);
    }
}
