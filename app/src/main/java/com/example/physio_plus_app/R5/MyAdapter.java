package com.example.physio_plus_app.R5;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.physio_plus_app.R;
import com.example.physio_plus_app.Utils.Entities.Patient;

import java.util.ArrayList;
import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.UserViewHolder> implements Filterable {

    private final Context context;
    private List<Patient> userList;
    private List<Patient> filteredData;
    public UserClickListener userClickListener;
    private int selectedUserId;
    private String selectedUserSSRN;
    private List<UserViewHolder> clickArray;
    private Button NewAppointments;
    private Button PatientHistory;


    public MyAdapter(List<Patient> Users,Context context) {
        this.context = context;
        this.userList = Users;
        //this.userClickListener = userClickListener;
        this.filteredData = Users;
        clickArray = new ArrayList<>();
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
                    List<Patient> Users = new ArrayList<>();
                    for(Patient u: filteredData){
                        if(u.getnamePatient().toLowerCase().contains(x) || u.getSurnamePatient().toLowerCase().contains(x) || u.getIdNumber().toLowerCase().contains(x)){
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
                userList = (List<Patient>) results.values;
                notifyDataSetChanged();

            }
        };
        return filter;
    }


    public interface UserClickListener{
        void clicked_user(Patient user);
    }


    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.r5_list, parent, false);
        return new UserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
        Patient user = userList.get(position);

        holder.setId(position);
        holder.getFirstName().setText(user.getnamePatient());
        holder.getLastname().setText(user.getSurnamePatient());
        holder.getAmka().setText(user.getIdNumber());
        holder.getAddress().setText(user.getAddress());
        holder.itemView.setOnClickListener(v -> userClickListener.clicked_user(user));
        clickArray.add(holder);
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
        private final LinearLayout layout;
        private final ConstraintLayout popUp;
        private  int  id;


        public UserViewHolder (@NonNull View itemView) {
            super(itemView);

            firstname_text = itemView.findViewById(R.id.FirstText);
            lastname_text = itemView.findViewById(R.id.LastText);
            amka_text = itemView.findViewById(R.id.amkaText);
            address_text = itemView.findViewById(R.id.address_text);
            layout = itemView.findViewById(R.id.usercard);
            popUp =  itemView.findViewById(R.id.PopUpActions);
            setPopUpVisibility(View.GONE);
            layout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    setPopUpVisibility(View.VISIBLE);
                    changeSelectedUser(id);
                }
            });

//      To be done. I have made the two buttons and their listeners. Also i put Intent and maybe is correct i dont know the user infos
//      I want to check it because i havent found another way

//            NewAppointments = itemView.findViewById(R.id.btnactions);
//            NewAppointments.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    Intent intent = new Intent(context.getApplicationContext(), R8.class);
//                    intent.putExtra("first_name", (CharSequence) getFirstName());
//                    intent.putExtra("last_name", (CharSequence) getLastname());
//                    intent.putExtra("address", (CharSequence) getAddress());
//                    intent.putExtra("amka", (CharSequence) getAmka());
//                    context.startActivity(intent);
//                }
//            });
//            PatientHistory = itemView.findViewById(R.id.btnhistory);
//            PatientHistory.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    Intent intent = new Intent(context.getApplicationContext(), R4.class);
//                    intent.putExtra("first_name", (CharSequence) getFirstName());
//                    intent.putExtra("last_name", (CharSequence) getLastname());
//                    intent.putExtra("address", (CharSequence) getAddress());
//                    intent.putExtra("amka", (CharSequence) getAmka());
//                    context.startActivity(intent);
//                }
//            });


        }

        public void setPopUpVisibility(int visibility){
            popUp.setVisibility(visibility);
        }

        public void setId(int position) {
            id = position;
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
        public LinearLayout getCard() {
            return layout;
        }

        public String getPatientId() {
            return amka_text.getText().toString();
        }


    }

    private void changeSelectedUser(int id) {
        clickArray.get(selectedUserId).setPopUpVisibility(View.GONE);
        selectedUserId = id;
        UserViewHolder new_holder = clickArray.get(selectedUserId);
        selectedUserSSRN = new_holder.getPatientId();
        new_holder.setPopUpVisibility(View.VISIBLE);
    }

    public String getPatientId() {
        return selectedUserSSRN;
    }

}
