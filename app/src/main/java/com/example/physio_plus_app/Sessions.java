package com.example.physio_plus_app;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class Sessions {

    private String url;
    private OkHttpClient client;
    private LinearLayout verticalLayout;

    public Sessions(String url, OkHttpClient client, LinearLayout verticalLayout) {
        this.url = url;
        this.client = client;
        this.verticalLayout = verticalLayout;
    }

    public void fetchSessions() {
        Request request = new Request.Builder()
                .url(url)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    final String responseData = response.body().string();
                    verticalLayout.post(new Runnable() {
                        @Override
                        public void run() {
                            handleResponse(responseData);
                        }
                    });
                } else {
                    Log.e("Sessions", "Error response: " + response.code());
                }
            }
        });
    }

    private void createCardView(String date, String hours, String notes) {
        LayoutInflater inflater = LayoutInflater.from(verticalLayout.getContext());
        View cardViewLayout = inflater.inflate(R.layout.card_view_layout, verticalLayout, false);

        TextView dateText = cardViewLayout.findViewById(R.id.date_text);
        TextView hoursText = cardViewLayout.findViewById(R.id.hours_text);
        TextView notesText = cardViewLayout.findViewById(R.id.notes_text);

        dateText.setText(date);
        hoursText.setText(hours);
        notesText.setText(notes);

        verticalLayout.addView(cardViewLayout);
    }

    private void handleResponse(String responseData) {
        try {
            // Parse the JSON response
            JSONObject patientObject = new JSONObject(responseData);
            String name = patientObject.getString("name");
            String age = patientObject.getString("age");
            String address = patientObject.getString("address");

            JSONArray sessionsArray = patientObject.getJSONArray("sessions");
            for (int i = 0; i < sessionsArray.length(); i++) {
                JSONObject sessionObject = sessionsArray.getJSONObject(i);
                String date = sessionObject.getString("date");
                String hours = sessionObject.getString("hours");
                String notes = sessionObject.getString("notes");

                String MDdate = date.substring(5);

                // Create a new CardView for each set of data
                createCardView(MDdate, hours, notes);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

}

