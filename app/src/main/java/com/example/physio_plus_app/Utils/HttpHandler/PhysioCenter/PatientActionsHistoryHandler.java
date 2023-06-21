package com.example.physio_plus_app.Utils.HttpHandler.PhysioCenter;

import com.example.physio_plus_app.Utils.HttpHandler.HttpHandler;
import com.example.physio_plus_app.Utils.RequestParams;

import java.io.IOException;

import okhttp3.FormBody;
import okhttp3.RequestBody;
import okhttp3.Response;

public abstract class PatientActionsHistoryHandler extends HttpHandler {
    private static final String file_folder = "R3/";
    private static final String file_name = "logHistory.php";

    public static String request(RequestParams params) throws IOException {
        RequestBody body = new FormBody.Builder()
                .add("first_name", params.get("first_name"))
                .add("last_name", params.get("last_name"))
                .add("address", params.get("address"))
                .add("ssrn", params.get("ssrn"))
                .build();
        Response response = HttpHandler.postRequest(file_folder + file_name, body);
        return response.body().string();
    }
}
