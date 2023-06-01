package com.example.physio_plus_app;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;


public class HourPickerFragment extends Fragment {

    ArrayList<HourModel> hourModel = new ArrayList<>();

    public HourPickerFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        RecyclerView recyclerView = getActivity().findViewById(R.id.hourRecyclerView2);

        setUpHourModel();

        RecyclerViewAdapter adapter = new RecyclerViewAdapter(getActivity(), hourModel);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_hour_picker, container, false);
    }

    public void setUpHourModel(){
        String[] hourTime = getResources().getStringArray(R.array.hour_array);

        for (int i = 0; i<hourTime.length; i++){
            hourModel.add(new HourModel(hourTime[i]));
        }
    }


}