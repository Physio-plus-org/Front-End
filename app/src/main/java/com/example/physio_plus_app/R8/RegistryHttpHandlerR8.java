package com.example.physio_plus_app.R8;

import com.example.physio_plus_app.Utils.RequestParams;

public abstract class RegistryHttpHandlerR8 extends HttpHandlerR8 {
    static void request(String url, RequestParams params) throws Exception {
        makeRequest(url, params);
    }
}
