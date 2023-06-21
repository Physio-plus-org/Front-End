package com.example.physio_plus_app.R9;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentResultListener;

import com.example.physio_plus_app.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DateFragmentR9#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DateFragmentR9 extends Fragment {




    public DateFragmentR9() {
        // Required empty public constructor
    }

    public static DateFragmentR9 newInstance() {
        DateFragmentR9 fragment = new DateFragmentR9();
        Bundle args = new Bundle();;
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.r9_fragment_date, container, false);
        FragmentManager fragmentManager = getChildFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.child_fragment_container, MonthPickerFragmentR9.class, null)
                .setReorderingAllowed(true)
                .addToBackStack("name") // Name can be null
                .commit();

        // Get Start Month
        getChildFragmentManager().setFragmentResultListener("requestKey", this, new FragmentResultListener() {
            @Override
            public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle bundle) {
                // We use a String here, but any type that can be put in a Bundle is supported.
                FragmentManager fragmentManager = getChildFragmentManager();
                fragmentManager.beginTransaction()
                        .replace(R.id.child_fragment_container, DayPickerFragmentR9.class, bundle)
                        .setReorderingAllowed(true)
                        .addToBackStack("name") // Name can be null
                        .commit();
            }
        });


        return view;
    }

    @Override
    public void onResume() {
        super.onResume();

        getChildFragmentManager().setFragmentResultListener("dayPickedResult", this, new FragmentResultListener() {
            @Override
            public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle bundle) {

                Intent intent = new Intent(getActivity(), R9_2.class);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });


    }
}