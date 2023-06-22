package com.example.physio_plus_app.R10;

import android.widget.LinearLayout;

import com.example.physio_plus_app.Utils.Entities.PhysioCenter;
import com.example.physio_plus_app.Utils.Entities.Session;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.util.ArrayList;

public class FinancialHistory {
    private ArrayList<FinancialMove> history;
    public FinancialHistory() {
        this.history = new ArrayList<>();
    }
    public void MapJsonArray(JSONArray jsonArray) throws JSONException, ParseException {
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject jsonMove = jsonArray.getJSONObject(i);
            PhysioCenter center = new PhysioCenter(jsonMove.getJSONObject("center"));
            Session session = new Session(jsonMove.getJSONObject("session"));
            FinancialMove move = new FinancialMove(center, session);
            this.history.add(move);
        }
    }
    public void show(LinearLayout linearLayout) {
        for (FinancialMove move : this.history) {
            move.show(linearLayout);
        }
    }
}