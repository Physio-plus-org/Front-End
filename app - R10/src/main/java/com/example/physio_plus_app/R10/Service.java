package com.example.physio_plus_app;

import android.widget.LinearLayout;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Locale;

public class Service {
    private String serviceCode;
    private String serviceTitle;
    private String serviceDescription;
    private double serviceCost;

    public Service(JSONObject jsonObject) throws JSONException {
        this.serviceTitle = jsonObject.get("title").toString();
        this.serviceCode = jsonObject.get("code").toString();
        this.serviceDescription = jsonObject.get("description").toString();
        this.serviceCost = Double.parseDouble(jsonObject.get("cost").toString());
    }

    public void show(LinearLayout linearLayout) {
        TextView textView = new TextView(linearLayout.getContext());
        textView.setLayoutParams(new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.WRAP_CONTENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                )
        );
        textView.setText(String.format(Locale.getDefault(), "%s: %.2f€", this.serviceTitle, this.serviceCost));
        linearLayout.addView(textView);
    }

    public double getServiceCost() {
        return this.serviceCost;
    }
}
