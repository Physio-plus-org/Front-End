package com.example.physio_plus_app.R10;

import android.graphics.Typeface;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.physio_plus_app.Utils.Entities.PhysioCenter;
import com.example.physio_plus_app.Utils.Entities.Session;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.Set;

public class FinancialHistory {
    private final ArrayList<FinancialMove> history;
    private final HashMap<String, List<Integer>> duplicatesMap = new HashMap<>();
    private final HashMap<String, Double> movesCosts = new HashMap<>();
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
        for (String key : this.duplicatesMap.keySet()) {
            boolean isFirst = true;
            for (int index : Objects.requireNonNull(this.duplicatesMap.get(key))) {
                FinancialMove move = this.history.get(index);
                if (isFirst) {
                    move.showCenterDetails(linearLayout);
                    isFirst = false;
                }
                move.showServiceDetails(linearLayout);
            }
            TextView textView = new TextView(linearLayout.getContext());
            textView.setLayoutParams(new LinearLayout.LayoutParams(
                            LinearLayout.LayoutParams.WRAP_CONTENT,
                            LinearLayout.LayoutParams.WRAP_CONTENT
                    )
            );
            textView.setText(String.format(Locale.getDefault(), "Συνολικό κόστος: %.2f€", this.movesCosts.get(key)));
            textView.setTypeface(null, Typeface.BOLD);
            linearLayout.addView(textView);
        }
    }

    public void CalculateCosts() {
        for (String key : this.duplicatesMap.keySet()) {
            double currentCost = 0;
            for (Integer index : Objects.requireNonNull(this.duplicatesMap.get(key))) {
                FinancialMove move = this.history.get(index);
                currentCost += move.getCost();
            }
            this.movesCosts.put(key, currentCost);
        }
    }

    public void CheckDuplicates() {
        for (int i = 0; i < this.history.size(); i++) {
            FinancialMove move = this.history.get(i);
            String key = move.getHashKey();
            List<Integer> values = this.duplicatesMap.getOrDefault(key, new ArrayList<>());
            if (values != null) {
                values.add(i);
                this.duplicatesMap.put(key, values);
            }
        }
    }
}
