package com.example.physio_plus_app.R8;

public abstract class RegistryHttpHandlerR8 extends HttpHandlerR8 {
    static void request(String url, RequestParamsR8 params) throws Exception {
        makeRequest(url, params);
    }
}
