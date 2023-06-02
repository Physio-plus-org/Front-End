package com.example.physio_plus_app;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.harrywhewell.scrolldatepicker.MonthScrollDatePicker;
import com.harrywhewell.scrolldatepicker.OnDateSelectedListener;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MonthPickerFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MonthPickerFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private MonthScrollDatePicker MonthPicker;

    public MonthPickerFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MonthPickerFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MonthPickerFragment newInstance(String param1, String param2) {
        MonthPickerFragment fragment = new MonthPickerFragment();
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
        final View view = inflater.inflate(R.layout.fragment_month_picker, container, false);

        MonthPicker = view.findViewById(R.id.month_date_picker);
        // Set Start Month
        String formattedMonthStart = "01";
        String formattedYearEnd = "2021";
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            LocalDateTime curDate = LocalDateTime.now();
            DateTimeFormatter myFormat = DateTimeFormatter.ofPattern("MM");
            formattedMonthStart = curDate.format(myFormat);
            DateTimeFormatter myFormat2 = DateTimeFormatter.ofPattern("yyyy");
            LocalDateTime nextYear = LocalDateTime.now().plusYears(1);
            formattedYearEnd = nextYear.format(myFormat2);

        }

        MonthPicker.setStartMonth(Integer.parseInt(formattedMonthStart));
        MonthPicker.setEndDate(Integer.parseInt(formattedMonthStart), Integer.parseInt(formattedYearEnd));

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();

        MonthPicker.getSelectedDate(new OnDateSelectedListener() {
            @Override
            public void onDateSelected(@Nullable Date date) {
                if(date != null){
                    Bundle result = new Bundle();
                    LocalDate localDate = null;
                    if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                        localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                        String month = String.valueOf(localDate.getMonthValue());
                        result.putString("monthPicked", month);
                        String year = String.valueOf((localDate.getYear()));
                        result.putString("yearPicked", year);
                        getParentFragmentManager().setFragmentResult("requestKey", result);
                    }

                }
            }
        });

    }


}