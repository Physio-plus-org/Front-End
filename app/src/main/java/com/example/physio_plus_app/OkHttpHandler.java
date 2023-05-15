package com.example.physio_plus_app;

import android.os.*;

import org.json.*;
import java.util.*;
import okhttp3.*;

public class OkHttpHandler {

    public OkHttpHandler() {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
    }

    public String psfCreate(String namePhysio, String addressPhysio, String afmPhysio) throws Exception {
        final String URL = "http://10.140.7.254/r1/r1.php";

        OkHttpClient client = new OkHttpClient();

        MediaType mediaType = MediaType.parse("application/x-www-form-urlencoded");
        String requestBodyString = "tax_id_number=" + afmPhysio + "&name=" + namePhysio + "&address=" + addressPhysio;
        RequestBody body = RequestBody.create(mediaType, requestBodyString);

        Request request = new Request.Builder()
                .url(URL)
                .post(body)
                .build();

        Response response = client.newCall(request).execute();

        // Handle the response as needed
        // For example, you can get the response body as a string:
        return response.body().string();
    }

}
