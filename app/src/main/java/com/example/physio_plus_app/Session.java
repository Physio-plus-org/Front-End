package com.example.physio_plus_app;

import android.widget.EditText;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class Session {
    private int sessionId;
    private ArrayList<Service> services = new ArrayList<>();
    private Date sessionDate;
    private String sessionNotes;
    public Session(JSONObject jsonObject) throws JSONException, ParseException {
        this.sessionId = Integer.parseInt(jsonObject.get("id").toString());
        this.sessionDate = new SimpleDateFormat("dd/MM/yy", new Locale("greek")).parse(jsonObject.get("date").toString());
        this.sessionNotes = jsonObject.get("notes").toString();
        JSONArray jsonArrayServices = jsonObject.getJSONArray("services");
        for (int i = 0; i < jsonArrayServices.length(); i++) {
            JSONObject jsonService = jsonArrayServices.getJSONObject(i);
            services.add(new Service(jsonService));
        }
    }

    public void show(EditText resultView) {
        for (Service service : this.services) {
            service.show(resultView);
        }
    }
}
