package com.example.physio_plus_app.R8;

public abstract class RegistryHttpHandler extends HttpHandlerR8 {
    public static void request(String url, RequestParams params) throws Exception {
        makeRequest(url, params);
    }
}
