package com.example.physio_plus_app;

import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONException;

import java.io.IOException;
import java.text.ParseException;
import java.util.Hashtable;

public class R10 extends AppCompatActivity {
    private FinancialHistory financialHistory;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_r10);
        Request();
        ShowFinancialHistory();
    }

    protected void ShowFinancialHistory() {
        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.main_ll);
        financialHistory.show(linearLayout);
    }

    protected void Request() {
        try {
            Hashtable<String, String> params = new Hashtable<>();
            params.put("patient_id", "279869537922"); //!!!THIS IS FOR TESTING
            this.financialHistory = FinancialHttpHandler.makeRequest(params);
            Toast.makeText(this, "Done", Toast.LENGTH_SHORT).show();
        } catch (ServerResponseException e) {
            Toast.makeText(this, "Page not found", Toast.LENGTH_SHORT).show();
            throw new RuntimeException(e);
        } catch (JSONException e) {
            Toast.makeText(this, "Json parsing error", Toast.LENGTH_SHORT).show();
            throw new RuntimeException(e);
        } catch (IOException e) {
            Toast.makeText(this, "Request execution error", Toast.LENGTH_SHORT).show();
            throw new RuntimeException(e);
        } catch (ParseException e) {
            Toast.makeText(this, "Parsing error", Toast.LENGTH_SHORT).show();
            throw new RuntimeException(e);
        } catch (Exception e) {
            Toast.makeText(this, "Failed", Toast.LENGTH_SHORT).show();
            throw new RuntimeException(e);
        }
    }
}