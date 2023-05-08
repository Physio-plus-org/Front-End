package com.example.physio_plus_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private Button button;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        CalendarView calendarView = findViewById(R.id.calendar);
        calendarView.setMinDate(Calendar.getInstance().getTimeInMillis());

        Button button = findViewById(R.id.buttonNext);
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month,
                                            int dayOfMonth) {
                // Save the selected date
                Calendar calendar = Calendar.getInstance();


                calendar.set(year, month, dayOfMonth);
                SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy", new Locale("el", "GR"));

//                int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
//                SimpleDateFormat dayFormat = new SimpleDateFormat("EEEE");
//                String dayOfWeekString = dayFormat.format(calendar.getTime());

                String selectedDate = sdf.format(calendar.getTime());
                String dayName = new SimpleDateFormat("EEEE", new Locale("el", "GR")).
                        format(calendar.getTime());

                button.setTag(selectedDate);
                button.setTag(dayName);
            }
        });
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Launch MainActivity2 with the selected date
                String selectedDate = (String) button.getTag();
                String dayName=(String) button.getTag();

                if (selectedDate != null) {
                    Intent intent = new Intent(MainActivity.this, MainActivity2.class);

                    intent.putExtra("selectedDate", selectedDate);
                    intent.putExtra("dayName",dayName);

                    startActivity(intent);
                } else {
                    // Show an error message if no date has been selected
                    Toast.makeText(MainActivity.this, "Please select a date", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }
    private void openActivity2() {
        Intent intent = new Intent(this, MainActivity2.class);
        startActivity(intent);
    }


}