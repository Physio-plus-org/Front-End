package com.example.physio_plus_app.R10;

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

public class SessionR10 {
    private int sessionId;
    private ArrayList<ServiceR10> services = new ArrayList<>();
    private Date sessionDate;
    private String sessionNotes;
    private double sessionCost;
    public SessionR10(JSONObject jsonObject) throws JSONException, ParseException {
        this.sessionId = Integer.parseInt(jsonObject.get("id").toString());
//        String[] date_details = jsonObject.getJSONArray("date").toString().split("-");
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", new Locale("Greek"));
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
            services.add(new ServiceR10(jsonService));
        }
        this.sessionCost = 0;
        for (ServiceR10 service : this.services) {
            this.sessionCost += service.getServiceCost();
        }
    }

    public void show(LinearLayout linearLayout) {
        LinearLayout ser_linearLayout = new LinearLayout(linearLayout.getContext());
        ser_linearLayout.setOrientation(LinearLayout.VERTICAL);
        for (ServiceR10 service : this.services) {
            service.show(ser_linearLayout);
        }
        TextView textView = new TextView(linearLayout.getContext());
        textView.setLayoutParams(new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.WRAP_CONTENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                )
        );
        textView.setText(String.format(Locale.getDefault(), "Συνολικό κόστος: %.2f€", this.sessionCost));
        ser_linearLayout.addView(textView);
        linearLayout.addView(ser_linearLayout);
    }
}
