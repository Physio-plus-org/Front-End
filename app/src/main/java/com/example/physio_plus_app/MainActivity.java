package com.example.physio_plus_app;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONException;

import java.io.IOException;
import java.text.ParseException;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {
    public static final String ip = "192.168.1.75";
    private EditText patient_id;
    private EditText resultView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.patient_id = findViewById(R.id.name);
        this.resultView = findViewById(R.id.resultView);
    }

    public void onSubmit(View view) {
        try {
            FinancialHistory financialHistory = FinancialHttpHandler.makeRequest("http://" + ip + "/requestFinancial?patient_id=" + patient_id.getText().toString());
            financialHistory.show(this.resultView);
        } catch (Exception e) {
            this.resultView.setText(Arrays.toString(e.getStackTrace()));
        }
    }
}