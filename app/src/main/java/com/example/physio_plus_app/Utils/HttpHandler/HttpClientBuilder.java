package com.example.physio_plus_app.Utils.HttpHandler;

import okhttp3.OkHttpClient;

public abstract class HttpClientBuilder {
    private static OkHttpClient httpClient;

    public static OkHttpClient buildClient() {
        if (httpClient == null) {
            httpClient = new OkHttpClient();
        }
        return httpClient;
    }
}
