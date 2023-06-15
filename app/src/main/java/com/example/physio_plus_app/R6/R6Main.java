package com.example.physio_plus_app.R6;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.physio_plus_app.R;
import com.shrikanthravi.collapsiblecalendarview.data.Day;
import com.shrikanthravi.collapsiblecalendarview.widget.CollapsibleCalendar;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class R6Main extends AppCompatActivity implements SelectListener {
    private final String myIP = "192.168.1.100";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_r7);
        Locale locale = new Locale("EL", "GR");
        Locale.setDefault(locale);
       RequestEvent request = new RequestEvent();

        final CollapsibleCalendar collapsibleCalendar = findViewById(R.id.calendarId);
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
        LocalDateTime date_now =  LocalDateTime.now();
        createEvents(date_now, savedInstanceState);
        greekDateHandler(date_now);



    }

    private List<String> dateHandler(LocalDateTime dateTime){
        List<String> dates = new ArrayList<>();
        LocalDateTime date_start = dateTime;
        LocalDateTime date_end = date_start.plusDays(1);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        dates.add(date_start.format(formatter));
        dates.add(date_end.format(formatter));
        return dates;
    }

    private void createEvents(LocalDateTime dateTime, Bundle bundle){
        RequestEvent request = new RequestEvent();
        List<String> dates;
        dates = dateHandler(dateTime);

        List<Event> events;
        events = request.requestEvents(dates.get(0),dates.get(1));

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recyclerView.setAdapter(new MyAdapter(getApplicationContext(), events, this, bundle));
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
        AlertDialog.Builder builder = new AlertDialog.Builder(R6Main.this);

        builder.setTitle("Προσοχή!");
        builder.setMessage("Είστε σίγουρος ότι θέλετε να διαγράψετε το ραντεβού;");
        builder.setCancelable(true);

        builder.setNegativeButton("Άκυρο", (DialogInterface.OnClickListener) (dialog, which) -> {
            dialog.cancel();
        });

        builder.setPositiveButton("Ναι", (DialogInterface.OnClickListener) (dialog, which) -> {
            String url = "http://"+myIP+"/physio_stl/requestCancel.php?id=" + eventID;
            String response = null;
            try {
                OkHttpHandler okHttpHandler = new OkHttpHandler();
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
                alertDialog.getWindow().setBackgroundDrawableResource(R.drawable.roundly_light_view);
            }
        });
        alertDialog.show();
    }

    @Override
    public void onCompleteItemClicked(int eventID) {
        String url = "http://"+myIP+"/physio_stl/requestCompleted.php?id=" + eventID;
        String response = null;
        try {
            OkHttpHandler okHttpHandler = new OkHttpHandler();
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