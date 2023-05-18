package com.example.physio_plus_app;

import android.widget.EditText;

import org.json.JSONArray;
import org.json.JSONException;

import java.text.ParseException;
import java.util.ArrayList;

public class FinancialHistory {
    private final ArrayList<FinancialMove> history;
    public FinancialHistory() {
        this.history = new ArrayList<>();
    }
    public void MapJsonArray(JSONArray jsonArray) throws JSONException, ParseException {
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONArray jsonMoveArray = jsonArray.getJSONArray(i);
            FinancialMove move = new FinancialMove(jsonMoveArray);
            this.history.add(move);
        }
    }
    public void show(EditText resultView) {
        for (FinancialMove move : this.history) {
            move.show(resultView);
        }
    }
}
