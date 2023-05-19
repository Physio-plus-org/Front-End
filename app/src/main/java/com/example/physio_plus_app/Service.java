package com.example.physio_plus_app;

import android.widget.EditText;

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

    public void show(EditText resultView) {
        String prevText = resultView.getText().toString();
        prevText += String.format(Locale.getDefault(), "Title: %s\nCost: %.2f\nDescription: %s%n", this.serviceTitle, this.serviceCost, this.serviceDescription);
        resultView.setText(prevText);
    }
}
