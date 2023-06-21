package com.example.physio_plus_app.Main_PSF;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.physio_plus_app.R;
import com.example.physio_plus_app.Utils.Entities.Service;

import java.util.List;

public class ServiceAdapter extends RecyclerView.Adapter<ServiceAdapter.ServiceViewHolder> {//implements Filterable {
    private final Context context;
    private final List<Service> serviceList;
//    private List<Service> filteredData;
//    public ServiceAdapter.ServiceClickListener userClickListener;
    public ServiceAdapter(List<Service> service,Context context) {
        this.context = context;
        this.serviceList = service;
        //this.userClickListener = userClickListener;
//        this.filteredData = service;
    }
//    @Override
//    public Filter getFilter() {
//        Filter filter = new Filter() {
//            @Override
//            protected FilterResults performFiltering(CharSequence constraint) {
//                FilterResults filterResults = new FilterResults();
//                if(constraint == null || constraint.length() == 0){
//                    filterResults.values = filteredData;
//                    filterResults.count = filteredData.size();
//                }else{
//                    String x = constraint.toString().toLowerCase();
//                    List<User> Users = new ArrayList<>();
//                    for(User u: filteredData){
//                        if(u.getFirstName().toLowerCase().contains(x) || u.getLastName().toLowerCase().contains(x) || u.getAMKA().toLowerCase().contains(x)){
//                            Users.add(u);
//                        }
//                    }
//
//                    filterResults.values = Users;
//                    filterResults.count = Users.size();
//                }
//                return filterResults;
//            }
//
//            @Override
//            protected void publishResults(CharSequence constraint, FilterResults results) {
//                userList = (List<User>) results.values;
//                notifyDataSetChanged();
//
//            }
//        };
//        return filter;
//    }


//    public interface ServiceClickListener{
//        void clicked_user(User user);
//    }
    @NonNull
    @Override
    public ServiceAdapter.ServiceViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.main_psf_list, parent, false);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (view.findViewById(R.id.code_field).getVisibility() == View.GONE && view.findViewById(R.id.desc_field).getVisibility() == View.GONE) {
                    view.findViewById(R.id.code_field).setVisibility(View.VISIBLE);
                    view.findViewById(R.id.desc_field).setVisibility(View.VISIBLE);
                }else{
                    view.findViewById(R.id.code_field).setVisibility(View.GONE);
                    view.findViewById(R.id.desc_field).setVisibility(View.GONE);
                }
            }
        });
        return new ServiceViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ServiceAdapter.ServiceViewHolder holder, int position) {
        Service service = serviceList.get(position);
        holder.getTitle().setText(service.getTitle());
        holder.getDescription().setText(service.getDescription());
        holder.getCode().setText(service.getCode());
        holder.getCost().setText(service.getCost() + "€");

//        holder.itemView.setOnClickListener(v -> userClickListener.clicked_user(user));
    }

    @Override
    public int getItemCount() {
        return serviceList.size();
    }


    static class ServiceViewHolder extends RecyclerView.ViewHolder  {

        private final TextView title;
        private final TextView description;
        private final TextView code;
        private final TextView cost;

        public ServiceViewHolder (@NonNull View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.title_field);
            code = itemView.findViewById(R.id.code_field);
            cost = itemView.findViewById(R.id.cost_field);
            description = itemView.findViewById(R.id.desc_field);
        }
        public TextView getTitle() {
            return title;
        }
        public TextView getDescription() {
            return description;
        }
        public TextView getCode() {
            return code;
        }
        public TextView getCost() {
            return cost;
        }

    }

}