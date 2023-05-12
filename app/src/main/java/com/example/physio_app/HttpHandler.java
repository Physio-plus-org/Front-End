package com.example.physio_app;

import android.content.Intent;
import android.os.StrictMode;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Iterator;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class HttpHandler extends AppCompatActivity {


    public HttpHandler() {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
    }

        ArrayList<User>logHistory(String url) throws Exception{
            ArrayList<User> userlist = new ArrayList<>();
            OkHttpClient client = new OkHttpClient().newBuilder().build();
            RequestBody body = RequestBody.create("", MediaType.parse("text/plain"));
            Request request = new Request.Builder().url(url).method("POST", body).build();
            Response response = client.newCall(request).execute();
            String data = response.body().string();
            //System.out.println("My Response: " + data);

            try {
                JSONObject json = new JSONObject(data);
                Iterator<String> keys = json.keys();

                while(keys.hasNext()){
                    String firstName = json.getString("first_name");
                    String lastName = json.getString("last_name");
                    userlist.add(new User(firstName,lastName));
                }
            }catch (JSONException e){
                e.printStackTrace();
            }

            return userlist;

        }

        public void phpData(String url) throws Exception{
            OkHttpClient client = new OkHttpClient().newBuilder().build();
            RequestBody body = RequestBody.create("", MediaType.parse("text/plain"));
            Request request = new Request.Builder().url(url).method("POST", body).build();
            Response response = client.newCall(request).execute();
            System.out.println("My Response: " + response);
        }



}
