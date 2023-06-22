package com.example.physio_plus_app.R10;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.physio_plus_app.R;
import com.example.physio_plus_app.Utils.AppObserver;
import com.example.physio_plus_app.Utils.ButtonActionsController;
import com.example.physio_plus_app.Utils.Entities.Patient;
import com.example.physio_plus_app.Utils.HttpHandler.Patient.FinancialMovesHandler;
import com.example.physio_plus_app.Utils.RequestParams;

import org.json.JSONException;

import java.io.IOException;
import java.text.ParseException;
import java.util.Hashtable;

public class R10 extends AppCompatActivity {
    private FinancialHistory financialHistory;
    private String patient_id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.r10_activity);

        Intent intent = getIntent();
        patient_id = intent.getStringExtra("patient_id");
        Request();
        financialHistory.CheckDuplicates();
        financialHistory.CalculateCosts();
        ShowFinancialHistory();

        //fOOTBAR
//        ImageView calendarFootbarButton = findViewById(R.id.calendarFootbar);


//        calendarFootbarButton.setOnClickListener(v->{
//            Intent i = new Intent(R10.this, R9.class);
//            startActivity(i);
//        });
    }

    public void GoToR9(View view) {
        ButtonActionsController.BackButtonAction(this);
    }

    protected void ShowFinancialHistory() {
        LinearLayout linearLayout = findViewById(R.id.main_ll);
        financialHistory.show(linearLayout);
    }

    protected void Request() {
        if (patient_id.isEmpty()) {
            Toast.makeText(this, "No patient selected", Toast.LENGTH_SHORT).show();
        }
        try {
            RequestParams params = new RequestParams()
                    .add("patient_id", patient_id);
            this.financialHistory = FinancialMovesHandler.request(params);//FinancialHttpHandlerR10.makeRequest(params);
            Toast.makeText(this, "Done", Toast.LENGTH_SHORT).show();
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