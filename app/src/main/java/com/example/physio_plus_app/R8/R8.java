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
import com.example.physio_plus_app.Utils.AppObserver;
import com.example.physio_plus_app.Utils.Entities.Patient;
import com.example.physio_plus_app.Utils.Entities.PhysioCenter;
import com.example.physio_plus_app.Utils.Entities.Service;
import com.example.physio_plus_app.Utils.HttpHandler.PhysioCenter.ActionRegisterHandler;
import com.example.physio_plus_app.Utils.HttpHandler.PhysioCenter.AllServicesHandler;
import com.example.physio_plus_app.Utils.RequestParams;

import com.example.physio_plus_app.R3.R3;
import com.example.physio_plus_app.R6.R6;
import com.example.physio_plus_app.R7.DropdownAppointmentSharedFactory;
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


    TextView redBubble;
    private DropdownAppointmentSharedFactory dropdownAppointmentSharedFactory;
    private TextView nameTextView;
    private TextView addressTextView;
    private TextView dateInfoTextView;
    private TextView personalNumberTextView;
    private Spinner servicesSpinner;
//    private ArrayAdapter<Service> dataAdapter;
    private ServiceSpinnerAdapter dataAdapter;
   private final ArrayList<Service> servicesList = new ArrayList<>();
    private TextView notesTextView;
    private TextView dateTextView;
    private String patient_id;
    private Patient patient;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.r8_activity);

        Intent intent = getIntent();
        patient_id = intent.getStringExtra("patient_id");

        patient = ((PhysioCenter) AppObserver.getLoggedUser()).getPatient(patient_id);


        this.nameTextView = findViewById(R.id.name_tv);
        this.personalNumberTextView = findViewById(R.id.ssrn_tv);
        this.dateInfoTextView = findViewById(R.id.date_tv);
        this.addressTextView = findViewById(R.id.address_tv);
        this.notesTextView = findViewById(R.id.notes);
        this.dateTextView = findViewById(R.id.date_text);

        this.servicesSpinner = findViewById(R.id.services);
//        this.dataAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, this.servicesList);
        /*this.dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);*/
        /*this.servicesSpinner.setAdapter(this.dataAdapter);*/
        this.dataAdapter = new ServiceSpinnerAdapter(this, this.servicesList);
        this.servicesSpinner.setAdapter(
                new NothingSelectedSpinnerAdapterR8(
                        this.dataAdapter,
                        R.layout.r8_no_item,
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
            e.printStackTrace();
        }

        /* Layout obbjects */
        redBubble = findViewById(R.id.redBubbleText);
        redBubble.setVisibility(View.GONE);

        /* Topbar */
        //Initialize Appointment Object
        dropdownAppointmentSharedFactory = new DropdownAppointmentSharedFactory(this);
        dropdownAppointmentSharedFactory.testConnection();
        ImageView goBackButton = findViewById(R.id.goback);
        goBackButton.setOnClickListener(v -> finish());

        Button appointmentsButton = findViewById(R.id.calendarTopBar);

        appointmentsButton.setOnClickListener(v->{
            dropdownAppointmentSharedFactory.fetchUpcomingAppointmentsForDropdown(this,redBubble);
        });

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
//        String url = urlRoot + "registerAction.php";
        RequestParams params = new RequestParams()
            .add("date", formatDate(this.dateTextView.getText().toString()))
            .add("note", this.notesTextView.getText().toString())
            .add("service_id", this.servicesList.get(this.servicesSpinner.getSelectedItemPosition()).getCode())
            .add("patient_id", this.personalNumberTextView.getText().toString());
//        RegistryHttpHandlerR8.request(url, params);
        if (ActionRegisterHandler.request(params)) {
            Toast.makeText(this, "Success", Toast.LENGTH_SHORT).show();
        }
    }
    protected String formatDate(String dateText) {
        List<String> dateList = Arrays.asList(dateText.split("/"));
        Collections.reverse(dateList);
        return String.join("-", dateList);
    }
    public void onCalendarClick(View view) {
        Calendar c = Calendar.getInstance();
        DatePickerDialog dpDialog = new DatePickerDialog (
                this,
                (view1, year, month, dayOfMonth) -> dateTextView.setText(getString(R.string.date_value, dayOfMonth, (month+1), year)),
                c.get(Calendar.YEAR),
                c.get(Calendar.MONTH),
                c.get(Calendar.DAY_OF_MONTH)
        );
        dpDialog.show();
    }

    protected void PatientInformationSetting() {
        this.nameTextView.setText(patient.getFullName());
        this.personalNumberTextView.setText(patient.getIdNumber());
        this.addressTextView.setText(patient.getAddress());
        this.dateInfoTextView.setText(new SimpleDateFormat("dd/MM/yyyy", new Locale("greek")).format(new Date()));

    }
    protected void PatientInitializationRequest(){
        PatientInformationSetting();
    }
    protected void ServicesSpinnerPopulation(ArrayList<Service> services) {
        this.servicesList.clear();
        this.servicesList.addAll(services);
        this.dataAdapter.notifyDataSetChanged();
    }
    protected void ServicesInitializationRequest() throws Exception {
        ArrayList<Service> services = new ArrayList<>();
        AllServicesHandler.request(services);
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