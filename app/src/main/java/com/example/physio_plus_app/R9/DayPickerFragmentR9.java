package com.example.physio_plus_app.R9;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.physio_plus_app.R;
import com.harrywhewell.scrolldatepicker.DayScrollDatePicker;
import com.harrywhewell.scrolldatepicker.OnDateSelectedListener;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;
import java.time.ZoneId;
import java.util.Date;
import java.util.Locale;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DayPickerFragmentR9#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DayPickerFragmentR9 extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private DayScrollDatePicker dayPicker;

    public DayPickerFragmentR9() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment DayPickerFragmentR9.
     */
    // TODO: Rename and change types and number of parameters
    public static DayPickerFragmentR9 newInstance(String param1, String param2) {
        DayPickerFragmentR9 fragment = new DayPickerFragmentR9();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
        Locale locale = new Locale("EL");
        Locale.setDefault(locale);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.r9_fragment_day_picker, container, false);


        Locale locale = new Locale("EL");
        Locale.setDefault(locale);
        int monthPicked = Integer.parseInt(getArguments().getString("monthPicked"));
        int yearPicked = Integer.parseInt(getArguments().getString("yearPicked"));
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            LocalDate curDate = LocalDate.now();
            if(curDate.getMonthValue()==monthPicked && curDate.getYear()==yearPicked) {
                dayPicker = view.findViewById(R.id.day_date_picker);
                dayPicker.setStartDate(curDate.getDayOfMonth(),curDate.getMonthValue(),curDate.getYear());
                dayPicker.setEndDate(curDate.lengthOfMonth(), curDate.getMonthValue(), curDate.getYear());
            }
            else {
                dayPicker = view.findViewById(R.id.day_date_picker);
                dayPicker.setStartDate(01,monthPicked,yearPicked);
                curDate = curDate.withMonth(monthPicked);
                dayPicker.setEndDate(curDate.lengthOfMonth(), monthPicked, yearPicked);
            }
        }

//        dayPicker = view.findViewById(R.id.day_date_picker);
//        dayPicker.setStartDate(1,7,2023);
//        dayPicker.setEndDate(31, 12, 2024);

        return view;
    }

    public void onResume() {
        super.onResume();

        dayPicker.getSelectedDate(new OnDateSelectedListener() {
            @Override
            public void onDateSelected(@Nullable Date date) {

                if (date != null) {

                    Bundle result = new Bundle();
                    LocalDate localDate = null;

                    if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {

                        localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                        String day = String.valueOf(localDate.getDayOfWeek());
                        result.putString("dayPicked", day);
                        String month = String.valueOf((localDate.getMonthValue()));
                        result.putString("monthPicked", month);
                        result.putLong("date", localDate.getLong(ChronoField.DAY_OF_WEEK));
                        DateTimeFormatter myFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                        String dateFull = localDate.format(myFormat);
                        result.putString("dateFull", dateFull);
                        getParentFragmentManager().setFragmentResult("dayPickedResult", result);
                    }
                }



            }
        });
    }
}