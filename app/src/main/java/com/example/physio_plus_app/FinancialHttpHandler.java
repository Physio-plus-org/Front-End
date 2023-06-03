package com.example.physio_plus_app;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.IOException;
import java.text.ParseException;
import java.util.Hashtable;

public abstract class FinancialHttpHandler extends HttpHandler {
    public static final String ip = "192.168.1.6";
    public static FinancialHistory makeRequest(Hashtable<String, String> params) throws ServerResponseException, JSONException, IOException, ParseException {
        String url = "http://" + ip + "/requestFinance.php";
        Log.d("url", url);
        JSONArray jsonArray = request(url, params);
        Log.w("jsonArray", jsonArray.toString());
        FinancialHistory financialHistory = new FinancialHistory();
        financialHistory.MapJsonArray(jsonArray);
        return financialHistory;
    }
}
