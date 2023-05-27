package com.example.physio_app;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.UserViewHolder> {

    private final Context context;
    private List<User> userList;

    public MyAdapter(Context context, List<User> userList) {
        this.context = context;
        this.userList = userList;
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.list,null);
        return new UserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
            User user = userList.get(position);

            holder.firstname_text.setText(user.getFirstName());
            holder.lastname_text.setText(user.getLastName());
            holder.amka_text.setText(user.getAMKA());
    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    class UserViewHolder extends RecyclerView.ViewHolder{

        TextView firstname_text;
        TextView lastname_text;
        TextView amka_text;
        public UserViewHolder(@NonNull View itemView) {
            super(itemView);

            firstname_text = itemView.findViewById(R.id.FirstText);
            lastname_text = itemView.findViewById(R.id.LastText);
            amka_text = itemView.findViewById(R.id.amkaText);
        }
    }

}
