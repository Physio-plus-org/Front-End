package com.example.physio_plus_app.R8;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.physio_plus_app.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;

public class R8Main extends AppCompatActivity {

    private TextView nameTextView;
    private TextView personalNumberTextView;
    private Spinner servicesSpinner;
    private ArrayAdapter<Service> dataAdapter;
   private final ArrayList<Service> servicesList = new ArrayList<>();
    private TextView notesTextView;
    private TextView dateTextView;
    private final String HOST = "192.168.1.7";
    private final String urlRoot = "http://"+ HOST+"/";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_r8);

        this.nameTextView = findViewById(R.id.full_name);
        this.personalNumberTextView = findViewById(R.id.personal_number);
        this.notesTextView = findViewById(R.id.notes);
        this.dateTextView = findViewById(R.id.date_text);

        this.servicesSpinner = findViewById(R.id.services);
        this.dataAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, this.servicesList);
        /*this.dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);*/
        /*this.servicesSpinner.setAdapter(this.dataAdapter);*/
        this.servicesSpinner.setAdapter(
                new NothingSelectedSpinnerAdapter(
                        this.dataAdapter,
                        R.layout.no_item,
                        //R.layout.contact_spinner_nothing_selected_dropdown, // Optional
                        this
                )
        );
        //ServerRequest
        try {
            DataBaseInitializationRequest();
            RaiseToast("Success");
        } catch (Exception e) {
            RaiseToast("Failed");
        }
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
        RequestParams params = new RequestParams();
        params.add("date", formatDate(this.dateTextView.getText().toString()));
        params.add("note", this.notesTextView.getText().toString());
        params.add("service_id", this.servicesList.get(this.servicesSpinner.getSelectedItemPosition()).getCode());
        params.add("patient_id", this.personalNumberTextView.getText().toString());
        RegistryHttpHandler.request(url, params);
    }
    protected String formatDate(String dateText) {
        List<String> dateList = Arrays.asList(dateText.split("/"));
        Collections.reverse(dateList);
        return String.join("-", dateList);
    }
    public void onCalendarClick(View view) {
        Calendar c = Calendar.getInstance();
        DatePickerDialog dpDialog = new DatePickerDialog (
                R8Main.this,
                (view1, year, month, dayOfMonth) -> dateTextView.setText(getString(R.string.date_value, dayOfMonth, (month+1), year)),
                c.get(Calendar.YEAR),
                c.get(Calendar.MONTH),
                c.get(Calendar.DAY_OF_MONTH)
        );
        dpDialog.show();
    }
    protected void PatientInformationSetting(Patient patient) {
        String fullName = patient.getFullName();
        String pNumber = patient.getIdNumber();
        this.nameTextView.setText(fullName);
        this.personalNumberTextView.setText(pNumber);
    }
    protected void PatientInitializationRequest() throws Exception {
        String url = this.urlRoot + "patientRequest.php";
        RequestParams params = new RequestParams();
        params.add("patient_id", "*");
        ArrayList<Patient> patients = PatientHttpHandler.request(url, params);
        if (patients == null) throw new Exception();
        PatientInformationSetting(patients.get(0));
    }
    protected void ServicesSpinnerPopulation(ArrayList<Service> services) {
        this.servicesList.clear();
        this.servicesList.addAll(services);
        this.dataAdapter.notifyDataSetChanged();
    }
    protected void ServicesInitializationRequest() throws Exception {
        String url = this.urlRoot + "serviceRequest.php";
        ArrayList<Service> services = ServiceHttpHandler.request(url, null);
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