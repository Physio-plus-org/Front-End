package com.example.physio_plus_app.R4;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.physio_plus_app.R;

import java.text.SimpleDateFormat;
import java.util.Locale;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;

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

        RequestBody requestBody = new FormBody.Builder()
                .add("amka", amka)
                .build();

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", new Locale("Greek"));


//        try {
//            Response response = HttpHandler.postRequest("R4/displaysessions.php", requestBody);
//            if (response.isSuccessful()) {
//                String json = response.body().string();
//                Log.d("MainActivity", "Server response: " + json);
//
//                try {
//
//
//                    JSONArray sessionsArray = new JSONArray(json);
//
//
//                    for (int i = 0; i < sessionsArray.length(); i++) {
//                         JSONObject sessionObject = sessionsArray.getJSONObject(i);
//
//                        try {
//                            Date date = format.parse(sessionObject.get("date").toString());
//                            String notes = sessionObject.getString("notes");
//
//                            if (date != null) {
//                                createCardView(format.format(date), notes);
//                            }
//
//                        } catch (ParseException e) {
//                            e.printStackTrace();
//                        }
//
//
//
//                }
//
//                } catch (JSONException e) {
//                    // Handle JSON parsing error
//                    Log.e("MainActivity", "JSON parsing error", e);
//                }
//            } else {
//                //code
//                Log.e("MainActivity", "Error response: " + response.code());
//            }
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }

    }

    private void createCardView(String date, String notes) {
        LayoutInflater inflater = LayoutInflater.from(verticalLayout.getContext());
        View cardViewLayout = inflater.inflate(R.layout.r4_card_view_layout, verticalLayout, false);

        TextView dateText = cardViewLayout.findViewById(R.id.date_text);
//        TextView hoursText = cardViewLayout.findViewById(R.id.hours_text);
        TextView notesText = cardViewLayout.findViewById(R.id.notes_text);

        dateText.setText(date);
//        hoursText.setText(hours);
        notesText.setText(notes);

        verticalLayout.addView(cardViewLayout);
    }


}

