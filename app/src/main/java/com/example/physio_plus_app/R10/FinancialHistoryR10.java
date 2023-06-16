package com.example.physio_plus_app.R10;

import android.widget.LinearLayout;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.util.ArrayList;

public class FinancialHistoryR10 {
    private ArrayList<FinancialMoveR10> history;
    public FinancialHistoryR10() {
        this.history = new ArrayList<>();
    }
    public void MapJsonArray(JSONArray jsonArray) throws JSONException, ParseException {
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject jsonMove = jsonArray.getJSONObject(i);
            PhysioCenterR10 center = new PhysioCenterR10(jsonMove.getJSONObject("center"));
            SessionR10 session = new SessionR10(jsonMove.getJSONObject("session"));
            FinancialMoveR10 move = new FinancialMoveR10(center, session);
            this.history.add(move);
        }
    }
    public void show(LinearLayout linearLayout) {
        for (FinancialMoveR10 move : this.history) {
            move.show(linearLayout);
        }
    }
}
