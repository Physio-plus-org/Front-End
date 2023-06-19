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
    private List<User> userList = new ArrayList<>();
    private List<User> filteredData;
    public UserClickListener userClickListener;

    public MyAdapter(List<User> Users,Context context, UserClickListener userClickListener) {
        this.context = context;
        this.userList = Users;
        this.userClickListener = userClickListener;
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
        /*
        RecyclerView recyclerView = (RecyclerView) parent;
        int recyclerViewWidth = recyclerView.getWidth();
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        layoutParams.width = recyclerViewWidth;
        view.setLayoutParams(layoutParams);
         */
        return new UserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
        User user = userList.get(position);

        holder.firstname_text.setText(user.getFirstName());
        holder.lastname_text.setText(user.getLastName());
        holder.amka_text.setText(user.getAMKA());
        //holder.itemView.setOnClickListener(v -> userClickListener.clicked_user(user));


    }

    @Override
    public int getItemCount() {
        return userList.size();
    }


    class UserViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView firstname_text;
        TextView lastname_text;
        TextView amka_text;
        public UserViewHolder(@NonNull View itemView) {
            super(itemView);

            firstname_text = itemView.findViewById(R.id.FirstText);
            lastname_text = itemView.findViewById(R.id.LastText);
            amka_text = itemView.findViewById(R.id.amkaText);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int position = getBindingAdapterPosition();
            if (position != RecyclerView.NO_POSITION) {
                User user = userList.get(position);
                userClickListener.clicked_user(user);
            }
        }
    }

}
