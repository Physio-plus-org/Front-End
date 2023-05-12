package com.example.physio_plus_app;

public abstract class RegistryHttpHandler extends HttpHandler {
    static void request(String url, RequestParams params) throws Exception {
        makeRequest(url, params);
    }
}
