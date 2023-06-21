package com.example.physio_plus_app.Utils.HttpHandler.Login;

import com.example.physio_plus_app.Utils.HttpHandler.HttpHandler;

import org.json.JSONException;

import java.io.IOException;

import okhttp3.RequestBody;
import okhttp3.Response;

public class LoginHandler extends HttpHandler {
    protected static final String FOLDER_NAME = "login/";
    protected static String loginRequest(String file_name, RequestBody body) throws IOException {
        Response response = postRequest(FOLDER_NAME+file_name, body);
        String responseBody = response.body().string();
        LogDMessage(responseBody);
        return responseBody;
    }

    protected static void InvalidJsonMessage() throws JSONException {
        throw new JSONException("Invalid object");
    }
    protected static void ExceptionClassMessage(Class<?> className) {
        LogEMessage("Exception ("+className+")");
    }

}
