package com.example.physio_plus_app.Utils.HttpHandler.PSF;

import com.example.physio_plus_app.Utils.RequestParams;

import java.io.IOException;

import okhttp3.FormBody;
import okhttp3.RequestBody;

public abstract class PSFRegisterServiceHandler extends PSFHandler {
    private static final String FOLDER_NAME = "R2/";
    private static final String FILE_NAME = "request.php";

    public static String request(RequestParams params) throws IOException {
        RequestBody body = new FormBody.Builder()
                .add("title", params.get("title"))
                .add("description", params.get("description"))
                .add("code", params.get("code"))
                .add("cost", params.get("cost"))
                .build();
        return psfRequest(FOLDER_NAME + FILE_NAME, body);
    }
}
