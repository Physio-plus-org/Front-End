package com.example.physio_plus_app;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.IOException;
import java.text.ParseException;

public abstract class FinancialHttpHandler extends HttpHandler {

    public static FinancialHistory makeRequest(String url) throws JSONException, IOException, ParseException {
        JSONArray jsonArray = request(url);
        FinancialHistory financialHistory = new FinancialHistory();
        financialHistory.MapJsonArray(jsonArray);
        return financialHistory;
    }
}
