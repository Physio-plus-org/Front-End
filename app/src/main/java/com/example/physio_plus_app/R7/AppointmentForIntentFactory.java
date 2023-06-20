package com.example.physio_plus_app.R7;




import android.graphics.Color;
import android.graphics.Typeface;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.example.physio_plus_app.R;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class AppointmentForIntentFactory {

    /* Topbar */


    private static final String TAG = "R7";
    private RelativeLayout cardContainer;

    TextView redBubble;

    private DropdownAppointmentSharedFactory dropdownAppointmentSharedFactory;
    private Gson gson;
    private OkHttpClient client;
    private final StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();

    private static final int MAX_APPOINTMENTS = 3;

    private static final String MOVE_TO_ACCEPTED_URL = "https://physioplus.000webhostapp.com/R7/move_to_accepted.php";
    private static final String TESTCON_URL = "https://physioplus.000webhostapp.com/R7/verify_connection.php";
    private static final String UPCOMING_APPOINTMENTS_URL = "https://physioplus.000webhostapp.com/R7/fetch_upcomingAppoint.php";


    public void fetchUpcomingAppointments(AppCompatActivity currActivity) {
        Request request = new Request.Builder()
                .url(UPCOMING_APPOINTMENTS_URL)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                // Handle network request failure
                Log.e(TAG, "Failed to fetch upcoming appointments: " + e.getMessage());
               currActivity.runOnUiThread(() -> Toast.makeText(currActivity, "Failed to fetch upcoming appointments", Toast.LENGTH_SHORT).show());
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) {
                // Handle network request success
                try {
                    if (response.isSuccessful()) {
                        String responseData = response.body().string();
                        // Process the response data
                       currActivity.runOnUiThread(() -> {
                            // Update UI with the fetched data
                            // For example, parse the JSON response and populate the appointment cards
                            List<AppointmentR7> appointments = dropdownAppointmentSharedFactory.parseAppointmentsFromJson(responseData);
                            assert appointments != null;



                        });
                    } else {
                        // Handle unsuccessful response
                        Log.e(TAG, "Failed to fetch upcoming appointments. Response code: " + response.code());
                       currActivity.runOnUiThread(() -> Toast.makeText(currActivity, "Failed to fetch upcoming appointments", Toast.LENGTH_SHORT).show());
                    }
                } catch (IOException e) {
                    Log.e(TAG, "Exception occurred while processing network response: " + e.getMessage());
                }
            }
        });
    }



    public void populateAppointmentCards(List<AppointmentR7> appointments,LinearLayout appointmentCardsContainer,AppCompatActivity currActivity) {
        // Clear the appointmentCardsContainer
        appointmentCardsContainer.removeAllViews();

        // Check for null appointments list
        if (appointments == null) {
            Log.e(TAG, "Appointments list is null");
            return;
        }

        // Iterate through the appointments list and create/update the appointment cards
        for (AppointmentR7 appointment : appointments) {
            try {
                View appointmentCard =currActivity.getLayoutInflater().inflate(R.layout.r7_appointment_card, appointmentCardsContainer, false);

                // Populate the appointment card with data from the appointment object
                TextView textDate = appointmentCard.findViewById(R.id.textDate);
                TextView textTime = appointmentCard.findViewById(R.id.textTime);
                TextView textPatientName = appointmentCard.findViewById(R.id.textPatientName);

                textDate.setText(appointment.getDate());
                textPatientName.setText(appointment.getPatientName());

                // Apply custom text appearance
                textDate.setTypeface(Typeface.create("sans-serif-medium", Typeface.NORMAL));
                textTime.setTypeface(Typeface.create("sans-serif-light", Typeface.NORMAL));
                textPatientName.setTypeface(Typeface.create("sans-serif", Typeface.ITALIC));
                textPatientName.setTextSize(18);

                // Set text colors
                textDate.setTextColor(ContextCompat.getColor(currActivity, R.color.PrimaryGreen));
                textTime.setTextColor( currActivity.getResources().getColor(R.color.PrimaryGreen));
                textPatientName.setTextColor(Color.BLACK);

                // Set OnClickListener on appointment card
                appointmentCard.setOnClickListener(v -> dropdownAppointmentSharedFactory.showAppointmentDialog(appointment,currActivity));

                // Add the appointment card to the appointmentCardsContainer with appropriate layout parameters
                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                layoutParams.setMargins(10, 10, 10, 50); // Add bottom margin to create spacing between cards
                appointmentCard.setLayoutParams(layoutParams);

                // Add the appointment card to the appointmentCardsContainer
                appointmentCardsContainer.addView(appointmentCard);
            } catch (Exception e) {
                Log.e(TAG, "Exception occurred while populating appointment cards: " + e.getMessage());
                e.printStackTrace();
            }
        }
}


    }
