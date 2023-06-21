package com.example.physio_plus_app.R6;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.physio_plus_app.R;
import com.example.physio_plus_app.R5.R5;
import com.example.physio_plus_app.R7.AppointmentForIntentFactory;
import com.example.physio_plus_app.R7.DropdownAppointmentSharedFactory;
import com.google.gson.Gson;
import com.shrikanthravi.collapsiblecalendarview.data.Day;
import com.shrikanthravi.collapsiblecalendarview.widget.CollapsibleCalendar;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import okhttp3.OkHttpClient;

public class R6 extends AppCompatActivity implements SelectListenerR6 {


    private RelativeLayout cardContainer;

    AppointmentForIntentFactory appointmentManager;

    TextView redBubble;

    private DropdownAppointmentSharedFactory dropdownAppointmentSharedFactory;
    private Gson gson;
    private OkHttpClient client;
    private final String myHost = "https://physioplus.000webhostapp.com/R6";
    /* Topbar */
//    ImageView physiologoTopbarButton = findViewById((R.id.PhysiologoTopbar));
    /* Footbar */
    private ImageView calendarFootbarButton;
    private ImageView addPatientFootbarButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.r6_activity);
        Locale locale = new Locale("EL", "GR");
        Locale.setDefault(locale);
        RequestEventR6 request = new RequestEventR6();
        final CollapsibleCalendar collapsibleCalendar = findViewById(R.id.calendarView);
        collapsibleCalendar.setCalendarListener(new CollapsibleCalendar.CalendarListener() {
            @Override
            public void onDayChanged() {

            }

            @Override
            public void onClickListener() {

            }

            @Override
            public void onDaySelect() {
                Day day = collapsibleCalendar.getSelectedDay();
//                Log.i(getClass().getName(), "Selected Day: "
//                        + day.getYear() + "-" + (day.getMonth() + 1) + "-" + day.getDay());
                LocalDateTime date_now =  LocalDateTime.of(day.getYear(), (day.getMonth() + 1), day.getDay(), 0, 0);
                createEvents(date_now, savedInstanceState);
                greekDateHandler(date_now);


            }

            @Override
            public void onItemClick(View view) {

            }

            @Override
            public void onDataUpdate() {

            }

            @Override
            public void onMonthChange() {

            }

            @Override
            public void onWeekChange(int i) {

            }
        });


        // Initialize events for current date
        LocalDateTime date_now = null;//DateTime();
        date_now = LocalDateTime.now();
        createEvents(date_now, savedInstanceState);
        assert date_now != null;
        greekDateHandler(date_now);



        //Initialize Appointment Object
        dropdownAppointmentSharedFactory = new DropdownAppointmentSharedFactory(this);
        dropdownAppointmentSharedFactory.testConnection();

        /* Topbar */
        ImageView goBackButton = findViewById(R.id.goback);


        /* Footbar */
        ImageView calendarFootbarButton = findViewById(R.id.calendarFootbar);
        ImageView addPatientFootbarButton = findViewById(R.id.addPatientFootbar);



        Button appointmentsButton = findViewById(R.id.calendarTopBar);

        appointmentsButton.setOnClickListener(v->{
            dropdownAppointmentSharedFactory.fetchUpcomingAppointmentsForDropdown(this,redBubble);
        });

        /* Layout obbjects */
        redBubble = findViewById(R.id.redBubbleText);
        redBubble.setVisibility(View.GONE);

        goBackButton.setOnClickListener(v -> finish());


//        addPatientFootbarButton.setOnClickListener((v->{
//            Intent i = new Intent(R6.this, R3.class );
//            startActivity(i);
//
//        }));
//        calendarFootbarButton.setOnClickListener(v->{
//            Intent i = new Intent(getApplicationContext(), R6.class);
//            startActivity(i);
//        });
        goBackButton.setOnClickListener(v -> finish());

       addPatientFootbarButton.setOnClickListener((v->{
           Intent i = new Intent(getApplicationContext(), R5.class);
           startActivity(i);
        }));
//        calendarFootbarButton.setOnClickListener(v->{
//            Intent i = new Intent(CurrentActivity.this, R6Main.class);
//           startActivity(i);
//        });
//







        /* Network-Database oriented Objects */
        client = new OkHttpClient();



    }



    private List<String> dateHandler(LocalDateTime dateTime){
        List<String> dates = new ArrayList<>();
        LocalDateTime date_end = dateTime.plusDays(1);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        dates.add(dateTime.format(formatter));
        dates.add(date_end.format(formatter));
        return dates;
    }

    private void createEvents(LocalDateTime dateTime, Bundle bundle){
        RequestEventR6 request = new RequestEventR6();
        List<String> dates;
        dates = dateHandler(dateTime);

        List<EventR6> events;
        events = request.requestEvents(dates.get(0),dates.get(1));

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recyclerView.setAdapter(new MyAdapterR6(getApplicationContext(), events, this, bundle));
    }

    private void greekDateHandler(LocalDateTime dateTime){
        Locale locale = new Locale("EL", "GR");
        Locale.setDefault(locale);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEEE dd MMM", locale);
        String dateWordGreek = dateTime.format(formatter);
        TextView dateWordView= findViewById(R.id.date_word);
        dateWordView.setText(dateWordGreek);
    }

    @Override
    public void onRemoveItemClicked(int eventID){
        AlertDialog.Builder builder = new AlertDialog.Builder(R6.this);

        builder.setTitle("Προσοχή!");
        builder.setMessage("Είστε σίγουρος ότι θέλετε να διαγράψετε το ραντεβού;");
        builder.setCancelable(true);

        builder.setNegativeButton("Άκυρο", (dialog, which) -> dialog.cancel());

        builder.setPositiveButton("Ναι", (dialog, which) -> {
            String url = myHost +"/requestCancel.php?id=" + eventID;
            String response = null;
            try {
                OkHttpHandlerR6 okHttpHandler = new OkHttpHandlerR6();
                response = okHttpHandler.requestCancel(url);
                System.out.println(response);
                overridePendingTransition(0,0);
                this.recreate();
                overridePendingTransition(0,0);
            } catch (Exception e) {
                e.printStackTrace();
                dialog.cancel();
            }


        });

        AlertDialog alertDialog = builder.create();
        alertDialog.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(DialogInterface dialog) {
                alertDialog.getWindow().setBackgroundDrawableResource(R.drawable.r6_roundly_light_view);
            }
        });
        alertDialog.show();
    }

    @Override
    public void onCompleteItemClicked(int eventID) {
        String url = "http://"+ myHost +"/physio_stl/requestCompleted.php?id=" + eventID;
        String response = null;
        try {
            OkHttpHandlerR6 okHttpHandler = new OkHttpHandlerR6();
            response = okHttpHandler.requestCompleted(url);
            System.out.println(response);
            overridePendingTransition(0,0);
            this.recreate();
            overridePendingTransition(0,0);
        } catch (Exception e) {
            e.printStackTrace();
            overridePendingTransition(0,0);
            this.recreate();
            overridePendingTransition(0,0);
        }


    }
}