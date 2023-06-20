package com.example.physio_plus_app.R4;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.physio_plus_app.R;
import com.example.physio_plus_app.Utils.HttpHandler.HttpHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import okhttp3.Response;

public class Sessions {

    private String url;
    String amka;
    private OkHttpClient client;
    private LinearLayout verticalLayout;

    public Sessions(String url, String amka, OkHttpClient client, LinearLayout verticalLayout) {
        this.url = url;
        this.amka = amka;
        this.client = client;
        this.verticalLayout = verticalLayout;
    }

    public void displaySessions() {

        try {
            Response response = HttpHandler.postRequest("R4/displaysessions.php", null);
            if (response.isSuccessful()) {
                String json = response.body().string();
                Log.d("MainActivity", "Server response: " + json);

                try {

                    JSONObject sessionObject = new JSONObject();
                    JSONArray sessionsArray = sessionObject.getJSONArray("sessions");

                    for (int i = 0; i < sessionsArray.length(); i++) {
                         sessionObject = sessionsArray.getJSONObject(i);
                         String date = sessionObject.getString("date");
                         String hours = sessionObject.getString("hours");
                         String notes = sessionObject.getString("notes");

                // Modifying the date in order to obtain just the day and month
                          String MDdate = date.substring(5);

                          createCardView(MDdate, hours, notes);
                }

                } catch (JSONException e) {
                    // Handle JSON parsing error
                    Log.e("MainActivity", "JSON parsing error", e);
                }
            } else {
                //code
                Log.e("MainActivity", "Error response: " + response.code());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
//
//        Request request = new Request.Builder()
//                .url(url)
//                .build();

//        client.newCall(request).enqueue(new Callback() {
//            @Override
//            public void onFailure(Call call, IOException e) {
//                e.printStackTrace();
//            }
//
//            @Override
//            public void onResponse(Call call, Response response) throws IOException {
//                if (response.isSuccessful()) {
//                    final String responseData = response.body().string();
//                    verticalLayout.post(new Runnable() {
//                        @Override
//                        public void run() {
//                            handleResponse(responseData);
//                        }
//                    });
//                } else {
//                    Log.e("Sessions", "Error response: " + response.code());
//                }
//            }
//        });
    }

    private void createCardView(String date, String hours, String notes) {
        LayoutInflater inflater = LayoutInflater.from(verticalLayout.getContext());
        View cardViewLayout = inflater.inflate(R.layout.r4_card_view_layout, verticalLayout, false);

        TextView dateText = cardViewLayout.findViewById(R.id.date_text);
        TextView hoursText = cardViewLayout.findViewById(R.id.hours_text);
        TextView notesText = cardViewLayout.findViewById(R.id.notes_text);

        dateText.setText(date);
        hoursText.setText(hours);
        notesText.setText(notes);

        verticalLayout.addView(cardViewLayout);
    }

//    private void handleResponse(String responseData) {
//        try {
//
//            JSONObject patientObject = new JSONObject(responseData);
//
//            JSONArray sessionsArray = patientObject.getJSONArray("sessions");
//            for (int i = 0; i < sessionsArray.length(); i++) {
//                JSONObject sessionObject = sessionsArray.getJSONObject(i);
//                String date = sessionObject.getString("date");
//                String hours = sessionObject.getString("hours");
//                String notes = sessionObject.getString("notes");
//
//                // Modifying the date in order to obtain just the day and month
//                String MDdate = date.substring(5);
//
//                createCardView(MDdate, hours, notes);
//            }
//
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//    }


    public void sendPatientNameToServer(String amka) {
        RequestBody requestBody = new FormBody.Builder()
                .add("amka", amka)
                .build();

        try {
            Response response = HttpHandler.postRequest("R4/displaysessions.php", requestBody);
            if (response.isSuccessful()) {

                String responseData = response.body().string();
                Log.d("DisplayInfo", "Response: " + responseData);

            } else {
                //code
                Log.e("DisplayInfo", "Failed to send patient name. Response code: " + response.code());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

//        client.newCall(request).enqueue(new Callback() {
//            @Override
//            public void onFailure(@NonNull Call call, @NonNull IOException e) {
//                // Handle network request failure
//                Log.e("DisplayInfo", "Failed to send patient name: " + e.getMessage());
//            }
//
//            @Override
//            public void onResponse(@NonNull Call call, @NonNull Response response) {
//                // Handle network request success
//                try (response) {
//                    if (response.isSuccessful()) {
//                        // Process the response if needed
//                        assert response.body() != null;
//                        String responseData = response.body().string();
//                        Log.d("DisplayInfo", "Response: " + responseData);
//                    } else {
//                        // Handle unsuccessful response
//                        Log.e("DisplayInfo", "Failed to send patient name. Response code: " + response.code());
//                    }
//                } catch (IOException e) {
//                    Log.e("DisplayInfo", "Exception occurred while processing network response: " + e.getMessage());
//                }
//            }
//        });


    }

}

