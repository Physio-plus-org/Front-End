package com.example.physio_plus_app.R5;

import static android.content.ContentValues.TAG;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public abstract class OkHttpHandlerR5 {

    public static void setUsersInfo() {

        OkHttpClient client = new OkHttpClient();

        String url = "https://physioplus.000webhostapp.com/R5/logHistory.php";
        FormBody.Builder builder = new FormBody.Builder();
        RequestBody body = builder.build();
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();

        try {
            Response response = client.newCall(request).execute();
            final String responseBody = response.body().string();
            JSONArray jsonArray = new JSONArray(responseBody);
            if (jsonArray.length() > 0) {
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    String firstName = jsonObject.optString("first_name");
                    String lastName = jsonObject.optString("last_name");
                    String Amka = jsonObject.optString("ssrn");
                    String address = jsonObject.optString("address");

                    User user = new User(firstName, lastName, Amka, address);
                    R5.userList.add(user);
                }
            }
        } catch (IOException ignored) {

        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
//        client.newCall(request).enqueue(new Callback() {
//            @Override
//            public void onFailure(Call call, IOException e) {
//                e.printStackTrace();
//                Log.d(TAG, "Something went wrong");
//            }
//            @Override
//            public void onResponse(Call call, Response response) throws IOException {
//                if (response.isSuccessful()) {
//                    final String responseBody = response.body().string();
//                    try {
//                        JSONArray jsonArray = new JSONArray(responseBody);
//
//                        if (jsonArray.length() > 0) {
//
//                            for(int i = 0; i < jsonArray.length(); i++){
//                                JSONObject jsonObject = jsonArray.getJSONObject(i);
//                                String firstName = jsonObject.optString("first_name");
//                                String lastName = jsonObject.optString("last_name");
//                                String Amka = jsonObject.optString("ssrn");
//                                String address = jsonObject.optString("address");
//
//                                User user = new User(firstName, lastName, Amka, address);
//                                R5.userList.add(user);
//                            }
//                        }
//                    } catch (JSONException e) {
//                        e.printStackTrace();
//                        Log.d(TAG, "Fetching Data failed!");
//                    }
//
//                } else {
//                    Log.d(TAG, "Error occurred!");
//                }
//            }
//        });
    }
}