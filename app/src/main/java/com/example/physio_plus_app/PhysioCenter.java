package com.example.physio_plus_app;

import android.widget.EditText;

import org.json.JSONException;
import org.json.JSONObject;

public class PhysioCenter {
    private String centerId;
    private String name;
    private String address;
    public PhysioCenter(JSONObject jsonObject) throws JSONException {
        this.centerId = jsonObject.get("tax_id_number").toString();
        this.name = jsonObject.get("name").toString();
        this.address = jsonObject.get("address").toString();
    }

    public void show(EditText resultView) {
        String prevText = resultView.getText().toString();
        prevText += String.format("Center: %s\n", this.name);
        resultView.setText(prevText);
    }
}
