package com.example.physio_plus_app;

public abstract class RegistryHttpHandler extends HttpHandlerR8 {
    static void request(String url, RequestParams params) throws Exception {
        makeRequest(url, params);
    }
}
