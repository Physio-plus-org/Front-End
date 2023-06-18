package com.example.physio_plus_app.R1;

import android.os.StrictMode;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

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
