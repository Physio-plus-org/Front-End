package com.example.physio_plus_app.R4;

import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import okhttp3.OkHttpClient;

public class DisplayInfo {

    TextView name_tv;
    TextView address_tv;
    TextView date_tv;
    TextView ssrn_tv;

    String url;
    String amka;
    OkHttpClient client;

    String currentDate = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(new Date());

    public DisplayInfo(String url, String amka, OkHttpClient client, TextView name, TextView ssrn, TextView address, TextView date){

//        this.amka = amka;
//        this.date_tv = date;
//        this.name_tv = name;
//        this.address_tv = address;
//        this.ssrn_tv = ssrn;
//        this.url = url;
//        date_tv.setText(currentDate);
////        this.client = client;
//
//
//        RequestBody requestBody = new FormBody.Builder()
//                .add("amka", amka)
//                .build();
//
//        try {
//            Response response = HttpHandler.postRequest("R4/displaypatients.php", requestBody);
//
//            if (response.isSuccessful()) {
//                String json = response.body().string();
//                Log.d("MainActivity", "Server response: " + json);
//
//                try {
//                      JSONArray jsonArray = new JSONArray(json);
//
//
//                    name_tv.setText(((JSONObject)jsonArray.get(0)).getString("name"));
//                    ssrn_tv.setText(((JSONObject)jsonArray.get(0)).getString("ssrn"));
//                    address_tv.setText(((JSONObject)jsonArray.get(0)).getString("address"));
//
//                } catch (JSONException e) {
//
//                    Log.e("MainActivity", "JSON parsing error", e);
//                }
//            } else {
//
//                Log.e("MainActivity", "Error response: " + response.code());
//            }
//
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//

    }


}
