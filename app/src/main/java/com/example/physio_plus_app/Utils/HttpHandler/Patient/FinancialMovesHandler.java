package com.example.physio_plus_app.Utils.HttpHandler.Patient;

import android.util.Log;

import com.example.physio_plus_app.R10.FinancialHistory;
import com.example.physio_plus_app.Utils.HttpHandler.HttpHandler;
import com.example.physio_plus_app.Utils.RequestParams;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.text.ParseException;

import okhttp3.FormBody;
import okhttp3.RequestBody;
import okhttp3.Response;

public abstract class FinancialMovesHandler extends HttpHandler {
    private static final String FOLDER_NAME = "R10/";
    private static final String FILE_NAME = "requestFinance.php";

    public static FinancialHistory request(RequestParams params) throws IOException, JSONException, ParseException {
        RequestBody body = new FormBody.Builder()
                .add("patient_id", params.get("patient_id"))
                .build();
        Response response = postRequest(FOLDER_NAME + FILE_NAME, body);
        JSONArray jsonArray = new JSONArray(response.body().string());
        FinancialHistory history = new FinancialHistory();
        history.MapJsonArray(jsonArray);
        return history;
    }
}
