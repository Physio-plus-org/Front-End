package com.example.physio_plus_app;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONException;

import java.io.IOException;
import java.text.ParseException;
import java.util.Hashtable;

public class MainActivity extends AppCompatActivity {
    private EditText patient_id;
    private EditText resultView;
    private FinancialHistory financialHistory;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.patient_id = findViewById(R.id.name);
        this.resultView = findViewById(R.id.resultView);
        this.patient_id.setText("279869537922");
    }

    public void onSubmit(View view) {
        try {
            Hashtable<String, String> params = new Hashtable<>();
            params.put("patient_id", patient_id.getText().toString());
            this.financialHistory = FinancialHttpHandler.makeRequest(params);
            this.financialHistory.show(this.resultView);
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