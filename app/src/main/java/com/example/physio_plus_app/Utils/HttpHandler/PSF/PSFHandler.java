package com.example.physio_plus_app.Utils.HttpHandler.PSF;

import com.example.physio_plus_app.Utils.HttpHandler.HttpHandler;

import java.io.IOException;

import okhttp3.RequestBody;
import okhttp3.Response;

public abstract class PSFHandler extends HttpHandler {
    protected static String psfRequest(String file_name, RequestBody body) throws IOException {
        Response response = HttpHandler.postRequest(file_name, body);
        return response.body().string();
    }
}
