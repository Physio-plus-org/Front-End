package com.example.physio_plus_app.R5;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.physio_plus_app.R;


import java.util.ArrayList;
import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.UserViewHolder> implements Filterable {

    private final Context context;
    private List<User> userList;
    private List<User> filteredData;
    public UserClickListener userClickListener;

    public MyAdapter(List<User> Users,Context context) {
        this.context = context;
        this.userList = Users;
        //this.userClickListener = userClickListener;
        this.filteredData = Users;
    }


    @Override
    public Filter getFilter() {
        Filter filter = new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                FilterResults filterResults = new FilterResults();
                if(constraint == null || constraint.length() == 0){
                    filterResults.values = filteredData;
                    filterResults.count = filteredData.size();
                }else{
                    String x = constraint.toString().toLowerCase();
                    List<User> Users = new ArrayList<>();
                    for(User u: filteredData){
                        if(u.getFirstName().toLowerCase().contains(x) || u.getLastName().toLowerCase().contains(x) || u.getAMKA().toLowerCase().contains(x)){
                            Users.add(u);
                        }
                    }

                    filterResults.values = Users;
                    filterResults.count = Users.size();
                }
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                userList = (List<User>) results.values;
                notifyDataSetChanged();

            }
        };
        return filter;
    }


    public interface UserClickListener{
        void clicked_user(User user);
    }


    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.r5_list, parent, false);

        return new UserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
        User user = userList.get(position);

        holder.getFirstName().setText(user.getFirstName());
        holder.getLastname().setText(user.getLastName());
        holder.getAmka().setText(user.getAMKA());
        holder.getAddress().setText(user.getAddress());
        holder.itemView.setOnClickListener(v -> userClickListener.clicked_user(user));
    }

    @Override
    public int getItemCount() {
        return userList.size();
    }


    class UserViewHolder extends RecyclerView.ViewHolder  {

        private final TextView firstname_text;
        private final TextView lastname_text;
        private final TextView amka_text;
        private final TextView address_text;

        public UserViewHolder (@NonNull View itemView) {
            super(itemView);

            firstname_text = itemView.findViewById(R.id.FirstText);
            lastname_text = itemView.findViewById(R.id.LastText);
            amka_text = itemView.findViewById(R.id.amkaText);
            address_text = itemView.findViewById(R.id.address_text);



        }
        public TextView getFirstName() {
            return firstname_text;
        }
        public TextView getLastname() {
            return lastname_text;
        }
        public TextView getAddress() {
            return address_text;
        }
        public TextView getAmka() {
            return amka_text;
        }

    }

}
