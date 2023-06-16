package com.example.physio_plus_app;

import android.widget.LinearLayout;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

public class PhysioCenterR10 {
    private String centerId;
    private String name;
    public PhysioCenterR10(JSONObject jsonObject) throws JSONException {
        this.centerId = jsonObject.get("tax_id_number").toString();
        this.name = jsonObject.get("name").toString();
    }

    public void show(LinearLayout linearLayout) {
        TextView textView = new TextView(linearLayout.getContext());
        textView.setLayoutParams(new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.WRAP_CONTENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                )
        );
        textView.setText(this.name);
        linearLayout.addView(textView);
    }
}
