package com.example.physio_plus_app.Utils.HttpHandler.PhysioCenter;

import com.example.physio_plus_app.Utils.HttpHandler.HttpHandler;
import com.example.physio_plus_app.Utils.RequestParams;

import java.io.IOException;

import okhttp3.FormBody;
import okhttp3.RequestBody;
import okhttp3.Response;

public abstract class ActionRegisterHandler extends HttpHandler {
    private static final String FOLDER_NAME = "R8/";
    private static final String FILE_NAME = "registerAction.php";

    public static boolean request(RequestParams params) throws IOException {
        RequestBody body = new FormBody.Builder()
                .add("date", params.get("date"))
                .add("note", params.get("note"))
                .add("service_id", params.get("service_id"))
                .add("patient_id", params.get("patient_id"))
                .build();
        Response response = postRequest(FOLDER_NAME + FILE_NAME, body);
        return response.code() == 200;
    }
}
