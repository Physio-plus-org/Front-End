package com.example.physio_plus_app.R10;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.IOException;
import java.text.ParseException;
import java.util.Hashtable;

public abstract class FinancialHttpHandlerR10 extends HttpHandlerR10 {
    public static FinancialHistoryR10 makeRequest(Hashtable<String, String> params) throws ServerResponseExceptionR10, JSONException, IOException, ParseException {
        String url = "https://physioplus.000webhostapp.com/R10/requestFinance.php";
        Log.d("url", url);
        JSONArray jsonArray = request(url, params);
        Log.w("jsonArray", jsonArray.toString());
        FinancialHistoryR10 financialHistory = new FinancialHistoryR10();
        financialHistory.MapJsonArray(jsonArray);
        return financialHistory;
    }
}
