package com.example.physio_plus_app;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentTransaction;


import android.graphics.RectF;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;


//import com.cheesecake.mytoxictraits.Week.ThisLocalizedWeek;


import com.harrywhewell.scrolldatepicker.DayScrollDatePicker;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Locale;
public class MainActivity extends AppCompatActivity{

    private Button button;



    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //start of actionBar
        ActionBar actionBar;
        actionBar = getSupportActionBar();

        actionBar.setDisplayShowCustomEnabled(true);
        LayoutInflater inflater=(LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.custom_image,null);
        actionBar.setCustomView(view);

        // Define ColorDrawable object and parse color
        // using parseColor method
        // with color hash code as its parameter
        ColorDrawable colorDrawable
                = new ColorDrawable(Color.parseColor("white"));

        // Set BackgroundDrawable
        actionBar.setBackgroundDrawable(colorDrawable);
        //end of actionBar
        Locale locale = new Locale("EL");
        Locale.setDefault(locale);


//        // Begin the transaction
//        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
//        ft.replace(R.id.fragmentContainerView, DateFragment.class, null);
//        ft.commit();


//        LocalDateTime myDateObj = null;
//        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
//            myDateObj = LocalDateTime.now();
//        }
//
//        DateTimeFormatter myDayObj = null;
//        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
//            myDayObj = DateTimeFormatter.ofPattern("dd");
//        }
//        DateTimeFormatter mymonthObj = null;
//        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
//            mymonthObj = DateTimeFormatter.ofPattern("MM");
//        }
//        DateTimeFormatter myyearObj = null;
//        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
//            myyearObj = DateTimeFormatter.ofPattern("yyyy");
//        }
//
//        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
//            String formattedDay = myDateObj.format(myDayObj);
//        }
//        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
//            String formattedMonth = myDateObj.format(mymonthObj);
//        }
//        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
//            String formattedyear = myDateObj.format(myyearObj);
//        }


//        mPicker = findViewById(R.id.day_date_picker);
//        //logic behind weekview
//        final ThisLocalizedWeek usWeek = new ThisLocalizedWeek(Locale.US);
//        System.out.println(usWeek);
//        // The English (United States) week starts on SUNDAY and ends on SATURDAY
//        System.out.println(usWeek.getFirstDay());
//        System.out.println(usWeek.getLastDay());
//        Log.d("TAG", "First day: " + usWeek.getFirstDay());
//        //mPicker.setEndDate(day+7, month, year);
//        String maindate = String.valueOf(usWeek.getFirstDay());
//        int startdate = Integer.parseInt(maindate.substring(8, 10));
//        Log.d("TAG", "First day: " + startdate);
//        int startmonth = Integer.parseInt(maindate.substring(5, 7));
//        Log.d("TAG", "First month: " + startmonth);
//        int startyear = Integer.parseInt(maindate.substring(0, 4));
//        Log.d("TAG", "First year: " + startyear);
//        mPicker.setStartDate(startdate, startmonth, startyear);
//
//
//        String enddate = String.valueOf(usWeek.getLastDay());
//        int endday = Integer.parseInt(enddate.substring(8, 10));
//        Log.d("TAG", "endday: " + endday);
//        int endmonth = Integer.parseInt(enddate.substring(5, 7));
//        Log.d("TAG", "endmonth: " + endmonth);
//        int endyear = Integer.parseInt(enddate.substring(0, 4));
//        Log.d("TAG", "endyear: " + endyear);
//
//        mPicker.setEndDate(endday, endmonth, endyear);
//        Button button = findViewById(R.id.buttonNext);
//        mPicker.getSelectedDate(new OnDateSelectedListener() {
//            @Override
//            public void onDateSelected(@Nullable Date date) {
//                if (date != null) {
//                    // Get the name of the day
//                    SimpleDateFormat sdf = new SimpleDateFormat("EEEE", Locale.getDefault());
//                    String dayName = sdf.format(date);
//
//                    button.setOnClickListener(new View.OnClickListener() {
//                        @Override
//                        public void onClick(View v) {
//                            // Pass the selected date and day name to the next activity
//                            Intent intent = new Intent(MainActivity.this, MainActivity2.class);
//                            intent.putExtra("selectedDate", date);
//                            intent.putExtra("dayName", dayName);
//                            startActivity(intent);
//                        }
//                    });
//                }
//            }
//        });





    }

    private void openActivity2() {
        Intent intent = new Intent(this, MainActivity2.class);
        startActivity(intent);
    }
}