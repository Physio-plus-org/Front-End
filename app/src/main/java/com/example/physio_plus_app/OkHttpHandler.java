package com.example.physio_plus_app;

import android.os.*;

import org.json.*;
import java.util.*;
import okhttp3.*;

public abstract class OkHttpHandler {
    private static final StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();

    public static String psfCreate(String namePhysio, String addressPhysio, String afmPhysio) throws Exception {
        StrictMode.setThreadPolicy(policy);
        final String URL = "http://192.168.1.6/r1/r1.php";

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
