package com.example.physio_plus_app.R2;

import com.example.physio_plus_app.Pararms.Service;
import com.example.physio_plus_app.Utils.HttpHandler.HttpHandler;

import java.io.IOException;

import okhttp3.FormBody;
import okhttp3.RequestBody;
import okhttp3.Response;

public abstract class OkHttpHandlerR2 {
    private static final String file_folder = "R2/";
    public static String insertData(Service params) throws IOException {

        String file_name = "request.php";
        FormBody.Builder builder = new FormBody.Builder();

        if (params != null) {
            builder.add("title", params.getTitle());
            builder.add("description", params.getDescription());
            builder.add("code", params.getCode());
            builder.add("cost", String.valueOf(params.getCost()));
        }

        RequestBody body = builder.build();
        Response response = HttpHandler.postRequest(file_folder + file_name, body);
        return response.body().string();
    }
}
