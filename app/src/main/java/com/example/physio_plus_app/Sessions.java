package com.example.physio_plus_app;

import android.os.StrictMode;
import android.util.Log;
import android.widget.ScrollView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class Sessions {

    private RecyclerView recyclerView;
    private CardViewAdapter adapter;
    private List<String> dataList;

    private String url;
    private OkHttpClient client;

    public Sessions(String url, OkHttpClient client) {
        this.url = url;
        this.client = client;
        this.dataList = new ArrayList<>();

    }

    public void addData() {

        Log.d("Sessions", "addData called!");


        Request request = new Request.Builder().url(url).build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.d("SessionsActivity", "Call failure! " + e.getMessage());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Log.d("SessionsActivity", "Call Responded!");

                if (response.isSuccessful()) {
                    String json = response.body().string();
                    Gson gson = new Gson();
                    Log.d("SessionsActivity", "Server response: " + json);

                    try {
                        JSONArray jsonArray = new JSONArray(json);

                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject jsonObject = jsonArray.getJSONObject(i);
                            String data = jsonObject.getString("date");
                            Log.d("SessionsActivity","date updated");
                            dataList.add(data);
                        }

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                } else {
                    Log.e("SessionsActivity", "Error response: " + response.code());
                }

                response.close();
                Log.d("Sessions", "Responce closed");
            }
        });
    }

    public void setRecyclerView(RecyclerView recyclerView){

        Log.d("Sessions","setRecyclerView called!");

        this.recyclerView = recyclerView;
        recyclerView.setLayoutManager(new LinearLayoutManager(recyclerView.getContext()));
        adapter = new CardViewAdapter(dataList);
        recyclerView.setAdapter(adapter);

    }
}
