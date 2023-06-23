package com.example.physio_plus_app.R4;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.physio_plus_app.R;
import com.example.physio_plus_app.R5.R5;
import com.example.physio_plus_app.R7.AppointmentForIntentFactory;
import com.example.physio_plus_app.R7.DropdownAppointmentSharedFactory;
import com.example.physio_plus_app.Utils.AppObserver;
import com.example.physio_plus_app.Utils.Entities.Patient;
import com.example.physio_plus_app.Utils.Entities.PhysioCenter;
import com.example.physio_plus_app.Utils.HttpHandler.PhysioCenter.PatientSessionsHandler;
import com.example.physio_plus_app.Utils.RequestParams;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;


public class R4 extends AppCompatActivity {


    /* Topbar */


    private RelativeLayout cardContainer;

    AppointmentForIntentFactory appointmentManager;

    TextView redBubble;

    private DropdownAppointmentSharedFactory dropdownAppointmentSharedFactory;
//    private Gson gson;
//    private OkHttpClient client;
//    TextView age_tv;


    TextView name_tv;

    TextView address_tv;
    TextView date_tv;

    TextView ssrn_tv;
    LinearLayout verticalLayout;

//
//    public DisplayInfo handler;
//    public Sessions sessions;
//    String url1, url2;
//
//    OkHttpClient client2;
//
//    private static final String DISPLAY_INFO = "https://physioplus.000webhostapp.com/R4/displaypatients.php";
//    private static final String DISPLAY_SESSIONS= "https://physioplus.000webhostapp.com/R4/displaysessions.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.r4_activity);

        Log.d("MainActivity", "onCreate method called");

        name_tv = findViewById(R.id.name_tv);
        ssrn_tv = findViewById(R.id.ssrn_tv);
        address_tv = findViewById(R.id.address_tv);
        date_tv = findViewById(R.id.date_tv);
        verticalLayout = findViewById(R.id.verticalLayout);


//        url1 = DISPLAY_INFO;
//        url2 = DISPLAY_SESSIONS;
//


        Log.d("Main Activity", "Sessions is running without problems");





        cardContainer = findViewById(R.id.cardContainer);


        /* Layout obbjects */

        redBubble = findViewById(R.id.redBubbleText);
        redBubble.setVisibility(View.GONE);
//        LinearLayout appointmentCardsContainer = findViewById(R.id.appointmentCardsContainer);


        Button appointmentsButton = findViewById(R.id.calendarTopBar);

        appointmentsButton.setOnClickListener(v->{
            dropdownAppointmentSharedFactory.fetchUpcomingAppointmentsForDropdown(this,redBubble);
        });





//
//        gson = new Gson();
//        client = new OkHttpClient();
//        dropdownAppointmentSharedFactory = new DropdownAppointmentSharedFactory(R4.this);






        ImageView goBackView = findViewById(R.id.goback);
        goBackView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View goBackView) {
                finish();
            }
        });




        Intent intent = getIntent();
        String message = intent.getStringExtra("patient_id");


        if (message != null) {
//            Patient patient = PatientInformationHandler.request(
//                    new RequestParams()
//                        .add("amka", message)
//            );
            Patient patient = ((PhysioCenter) AppObserver.getLoggedUser()).getPatient(message);
            if (patient != null) {
                name_tv.setText(patient.getFullName());
                ssrn_tv.setText(patient.getIdNumber());
                address_tv.setText(patient.getAddress());
                String currentDate = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(new Date());
                date_tv.setText(currentDate);
                Log.d("Main Activity", "DisplayInfo is running without problems");
            }

//            sessions = new Sessions(url2, message, client, verticalLayout);
//
//            sessions.displaySessions();
            HashMap<String, String> map = PatientSessionsHandler.request(
                    new RequestParams()
                            .add("amka", message)
            );
            for (String key : map.keySet()) {
                createCardView(key, map.get(key));
            }

        }else{
            Log.d("Main R4","Data parsed from R4 to R5 is null!");
        }


    }

    private void createCardView(String date, String notes) {
        LayoutInflater inflater = LayoutInflater.from(verticalLayout.getContext());
        View cardViewLayout = inflater.inflate(R.layout.r4_card_view_layout, verticalLayout, false);

        TextView dateText = cardViewLayout.findViewById(R.id.date_text);
//        TextView hoursText = cardViewLayout.findViewById(R.id.hours_text);
        TextView notesText = cardViewLayout.findViewById(R.id.notes_text);

        dateText.setText(date);
//        hoursText.setText(hours);
        notesText.setText(notes);

        verticalLayout.addView(cardViewLayout);
    }
}