package com.example.physio_plus_app.R8;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.physio_plus_app.R;
import com.example.physio_plus_app.R3.R3;
import com.example.physio_plus_app.R6.R6;
import com.example.physio_plus_app.R7.R7;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class R8 extends AppCompatActivity {

    private TextView nameTextView;
    private TextView addressTextView;
    private TextView dateInfoTextView;
    private TextView personalNumberTextView;
    private Spinner servicesSpinner;
    private ArrayAdapter<ServiceR8> dataAdapter;
   private final ArrayList<ServiceR8> servicesList = new ArrayList<>();
    private TextView notesTextView;
    private TextView dateTextView;
    private String patient_id;
    private final String urlRoot = "https://physioplus.000webhostapp.com/R8/";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.r8_activity);

        Intent intent = getIntent();
        patient_id = intent.getStringExtra("patient_id");

        this.nameTextView = findViewById(R.id.name_tv);
        this.personalNumberTextView = findViewById(R.id.ssrn_tv);
        this.dateInfoTextView = findViewById(R.id.date_tv);
        this.addressTextView = findViewById(R.id.address_tv);
        this.notesTextView = findViewById(R.id.notes);
        this.dateTextView = findViewById(R.id.date_text);

        this.servicesSpinner = findViewById(R.id.services);
        this.dataAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, this.servicesList);
        /*this.dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);*/
        /*this.servicesSpinner.setAdapter(this.dataAdapter);*/
        this.servicesSpinner.setAdapter(
                new NothingSelectedSpinnerAdapterR8(
                        this.dataAdapter,
                        R.layout.r8_no_item,
                        //R.layout.contact_spinner_nothing_selected_dropdown, // Optional
                        this
                )
        );
        Log.e("patient", patient_id);
        //ServerRequest
        try {
            DataBaseInitializationRequest();
            RaiseToast("Success");
        } catch (Exception e) {
            RaiseToast("Failed");
        }

        /* Topbar */
        ImageView goBackButton = findViewById(R.id.goback);
        goBackButton.setOnClickListener(v -> finish());

    }
    public void onSubmit(View view) {
        try {
            SubmitRegistry();
            RaiseToast("Success");
        } catch (Exception e) {
            RaiseToast("Failed");
        }
    }
    protected void SubmitRegistry() throws Exception {
        String url = urlRoot + "registerAction.php";
        RequestParamsR8 params = new RequestParamsR8();
        params.add("date", formatDate(this.dateTextView.getText().toString()));
        params.add("note", this.notesTextView.getText().toString());
        params.add("service_id", this.servicesList.get(this.servicesSpinner.getSelectedItemPosition()).getCode());
        params.add("patient_id", this.personalNumberTextView.getText().toString());
        RegistryHttpHandlerR8.request(url, params);
    }
    protected String formatDate(String dateText) {
        List<String> dateList = Arrays.asList(dateText.split("/"));
        Collections.reverse(dateList);
        return String.join("-", dateList);
    }
    public void onCalendarClick(View view) {
        Calendar c = Calendar.getInstance();
        DatePickerDialog dpDialog = new DatePickerDialog (
                getApplicationContext(),
                (view1, year, month, dayOfMonth) -> dateTextView.setText(getString(R.string.date_value, dayOfMonth, (month+1), year)),
                c.get(Calendar.YEAR),
                c.get(Calendar.MONTH),
                c.get(Calendar.DAY_OF_MONTH)
        );
        dpDialog.show();
    }
    protected void PatientInformationSetting(PatientR8 patient) {
        this.nameTextView.setText(patient.getFullName());
        this.personalNumberTextView.setText(patient.getIdNumber());
        this.addressTextView.setText(patient.getAddress());
        this.dateInfoTextView.setText(new SimpleDateFormat("dd/MM/yyyy", new Locale("greek")).format(new Date()));
    }
    protected void PatientInitializationRequest() throws Exception {
        String url = this.urlRoot + "patientRequest.php";
        RequestParamsR8 params = new RequestParamsR8();
        params.add("patient_id", patient_id);
        PatientR8 patient = PatientHttpHandlerR8.request(url, params);
        if (patient == null) throw new Exception();
        PatientInformationSetting(patient);
    }
    protected void ServicesSpinnerPopulation(ArrayList<ServiceR8> services) {
        this.servicesList.clear();
        this.servicesList.addAll(services);
        this.dataAdapter.notifyDataSetChanged();
    }
    protected void ServicesInitializationRequest() throws Exception {
        String url = this.urlRoot + "serviceRequest.php";
        ArrayList<ServiceR8> services = ServiceHttpHandlerR8.request(url, null);
        if (services == null) throw new Exception();
        ServicesSpinnerPopulation(services);
    }
    protected void DataBaseInitializationRequest() throws Exception {
        PatientInitializationRequest();
        ServicesInitializationRequest();
    }
    protected void RaiseToast(String message) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }


}