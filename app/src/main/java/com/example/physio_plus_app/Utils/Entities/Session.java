package com.example.physio_plus_app.Utils.Entities;

import android.widget.LinearLayout;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class Session {
    private final int sessionId;
    private final ArrayList<Service> services = new ArrayList<>();
    private Date sessionDate;
    private final String sessionNotes;
    private double sessionCost;
    private final SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", new Locale("Greek"));

    public Session(JSONObject jsonObject) throws JSONException, ParseException {
        this.sessionId = Integer.parseInt(jsonObject.get("id").toString());
//        String[] date_details = jsonObject.getJSONArray("date").toString().split("-");
        try {
            this.sessionDate = format.parse(jsonObject.get("date").toString());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        //new SimpleDateFormat("yyyy-MM-dd", new Locale("greek")).parse(jsonObject.get("date").toString());
        this.sessionNotes = jsonObject.get("notes").toString();
        JSONArray jsonArrayServices = jsonObject.getJSONArray("services");
        for (int i = 0; i < jsonArrayServices.length(); i++) {
            JSONObject jsonService = jsonArrayServices.getJSONObject(i);
            services.add(new Service(jsonService));
        }
        this.sessionCost = 0;
        for (Service service : this.services) {
            this.sessionCost += service.getCost();
        }
    }

    public void show(LinearLayout linearLayout) {
        TextView dateView = new TextView(linearLayout.getContext());
        dateView.setLayoutParams(new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.WRAP_CONTENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                )
        );
        dateView.setText(format.format(this.sessionDate));
        linearLayout.addView(dateView);
        for (Service service : this.services) {
            service.show(linearLayout);
        }
        TextView textView = new TextView(linearLayout.getContext());
        textView.setLayoutParams(new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.WRAP_CONTENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                )
        );
        textView.setText(String.format(Locale.getDefault(), "Συνολικό κόστος: %.2f€", this.sessionCost));
        linearLayout.addView(textView);
    }

    public double getCost() {
        double cost = 0;
        for (Service service : this.services) {
            cost += service.getCost();
        }
        return cost;
    }
}
