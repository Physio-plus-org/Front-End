package com.example.physio_plus_app;

import android.widget.EditText;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;

public class FinancialMove {
    private PhysioCenter physioCenter;
    private Session session;
    public FinancialMove(PhysioCenter physioCenter, Session session) {
        this.physioCenter = physioCenter;
        this.session = session;
    }

    public void show(EditText resultView) {
        this.physioCenter.show(resultView);
        this.session.show(resultView);
    }
}
