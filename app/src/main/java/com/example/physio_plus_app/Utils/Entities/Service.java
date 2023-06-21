package com.example.physio_plus_app.Utils.Entities;

import android.util.Log;
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
    public Service(String title, String code, String description, double cost) {
        this.serviceTitle = title;
        this.serviceCode = code;
        this.serviceDescription = description;
        this.serviceCost = cost;
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


    public String getTitle() {
        return serviceTitle;
    }
    public String getCode() {
        return serviceCode;
    }
    public String getDescription() {
        return serviceDescription;
    }
    public double getCost() {return serviceCost;}

    public void print() {
        Log.e("service", serviceTitle+"|"+serviceCode+"|"+serviceCost+"|"+serviceDescription);
    }
}
