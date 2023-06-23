package com.example.physio_plus_app.R8;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.physio_plus_app.R;
import com.example.physio_plus_app.Utils.Entities.Service;

import java.util.List;

public class ServiceSpinnerAdapter extends BaseAdapter {
    Context context;
    private List<Service> servicesList;
    LayoutInflater inflater;

    public ServiceSpinnerAdapter(Context applicationContext, List<Service> servicesList) {
        this.context = applicationContext;
        this.servicesList = servicesList;
        inflater = (LayoutInflater.from(applicationContext));
    }

    @Override
    public int getCount() {
        return servicesList.size();
    }

    @Override
    public Object getItem(int i) {
        return servicesList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = inflater.inflate(R.layout.r8_service_item, null);
        TextView service = (TextView) view.findViewById(R.id.textView3);
        service.setText(servicesList.get(i).toString());
        return view;
    }
}
