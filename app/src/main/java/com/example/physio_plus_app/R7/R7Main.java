package com.example.physio_plus_app.R7;


import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.StrictMode;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.StyleSpan;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.example.physio_plus_app.R;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;


public class R7Main extends AppCompatActivity {
    private static final String TAG = "R6Main";
    private RelativeLayout cardContainer;

    private ImageView calendarButton;
    private ImageView profileButton;

    private ImageView goBackButton;

    private TextView redBubble;
    private Gson gson;


    private OkHttpClient client;
    private final StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();

    private static final int MAX_APPOINTMENTS = 3;

    private static final String MOVE_TO_ACCEPTED_URL = "http://192.168.1.7/physio-backend/move_to_accepted.php";
    private static final String TESTCON_URL = "http://192.168.1.7/physio-backend/verify_connection.php";
    private static final String UPCOMING_APPOINTMENTS_URL = "http://192.168.1.7/physio-backend/fetch_upcomingAppoint.php";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_r7);
        View calendarTopBar = findViewById(R.id.calendarTopBar);
        StrictMode.setThreadPolicy(policy);


        cardContainer = findViewById(R.id.cardContainer);

        calendarButton = findViewById(R.id.calendarFootbar);
        profileButton = findViewById(R.id.addPatientFootbar);
        goBackButton = findViewById(R.id.goback);


        redBubble = findViewById(R.id.redBubbleText);
        redBubble.setVisibility(View.GONE);


        gson = new Gson();
        client = new OkHttpClient();


        CalendarTopBarClickListener(calendarTopBar);

        CalendarFootbarButtonClickListener(calendarButton);
        addProfileFootbarButtonClickListener(profileButton);
        goBackButtonClickListener(goBackButton);


        testConnection();
        fetchUpcomingAppointments();


    }

    private void redirectToAppointmentsPage() {
        recreate();
    }

    private void CalendarFootbarButtonClickListener(ImageView calendarButton) {
        Intent i = new Intent(R7Main.this, com.example.physio_plus_app.R6.R6Main.class );
        startActivity(i);


    }

    private void goBackButtonClickListener(ImageView goBackButton) {
        Intent i = new Intent(R7Main.this, com.example.physio_plus_app.R6.R6Main.class );
        startActivity(i);


    }

    private void addProfileFootbarButtonClickListener(ImageView profileButton) {
        Intent i = new Intent(R7Main.this, com.example.physio_plus_app.R3.R3Main.class );
        startActivity(i);


    }


    private void CalendarTopBarClickListener(View calendarTopBar) {

        calendarTopBar.setOnClickListener(v -> fetchUpcomingAppointmentsForDropdown());

    }
    private void addPatientFootbarButtonClickListener(ImageView calendarButton) {
        Intent i = new Intent(R7Main.this, com.example.physio_plus_app.R6.R6Main.class );
        startActivity(i);


    }




    private void testConnection() {
        Request request = new Request.Builder()
                .url(TESTCON_URL)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                // Handle network request failure
                Log.e(TAG, "Failed to test connection: " + e.getMessage());
                runOnUiThread(() -> Toast.makeText(R7Main.this, "Failed to test connection", Toast.LENGTH_SHORT).show());
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) {
                // Handle network request success
                if (response.isSuccessful()) {
                    // Connection is successful
                    Log.d(TAG, "Connection test successful");
                    runOnUiThread(() -> Toast.makeText(R7Main.this, "Connection test successful", Toast.LENGTH_SHORT).show());
                } else {
                    // Connection is unsuccessful
                    Log.e(TAG, "Failed to test connection. Response code: " + response.code());
                    runOnUiThread(() -> Toast.makeText(R7Main.this, "Failed to test connection", Toast.LENGTH_SHORT).show());
                }
            }
        });
    }


    private void fetchUpcomingAppointmentsForDropdown() {

        Request request = new Request.Builder()
                .url(UPCOMING_APPOINTMENTS_URL)
                .get()
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                // Handle network request failure
                Log.e(TAG, "Failed to fetch upcoming appointments for dropdown: " + e.getMessage());
                e.printStackTrace();
                runOnUiThread(() -> Toast.makeText(R7Main.this, "Failed to fetch upcoming appointments", Toast.LENGTH_SHORT).show());
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) {
                // Handle network request success
                try {
                    if (response.isSuccessful()) {
                        assert response.body() != null;
                        String responseData = response.body().string();
                        // Process the response data
                        runOnUiThread(() -> {
                            // Update UI with the fetched data
                            try {
                                List<Appointment> appointments = parseAppointmentsFromJson(responseData);
                                if (appointments != null) {
                                    showAppointmentDropdown(appointments);
                                } else {
                                    Log.e(TAG, "Failed to parse appointment data from JSON");
                                }
                            } catch (Exception e) {
                                Log.e(TAG, "Exception occurred while parsing JSON: " + e.getMessage());
                            }
                        });
                    } else {
                        // Handle unsuccessful response
                        Log.e(TAG, "Failed to fetch upcoming appointments for dropdown. Response code: " + response.code());
                        runOnUiThread(() -> Toast.makeText(R7Main.this, "Failed to fetch upcoming appointments", Toast.LENGTH_SHORT).show());
                    }
                } catch (IOException e) {
                    Log.e(TAG, "Exception occurred while processing network response: " + e.getMessage());
                }
            }
        });
    }


    private void showAppointmentDropdown(List<Appointment> appointments) {
        AlertDialog.Builder builder = new AlertDialog.Builder(R7Main.this);
        builder.setTitle("Επερχόμενα Ραντεβού");
        builder.setIcon(R.drawable.baseline_access_time_24);

        int upcomingAppointmentsCount = appointments.size();


        if (upcomingAppointmentsCount > 0) {
            redBubble.setVisibility(View.VISIBLE);
            redBubble.setText(String.valueOf(upcomingAppointmentsCount));
        } else {
            redBubble.setVisibility(View.GONE);
        }
        String separator = "\n⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯\n";



        String[] appointmentItems;
        if (upcomingAppointmentsCount > MAX_APPOINTMENTS) {
            appointmentItems = new String[upcomingAppointmentsCount];
            for (int i = 0; i < MAX_APPOINTMENTS; i++) {
                Appointment appointment = appointments.get(i);
                String appointmentText = appointment.getPatientName() + " - " + appointment.getDate() + " - " + appointment.getTime();
                appointmentItems[i] = appointmentText + separator;

            }

            // Make the "Δείτε περισσότερα" text bold
            String seeMoreText = "Δείτε περισσότερα";
            SpannableString spannableSeeMoreText = new SpannableString(seeMoreText);
            spannableSeeMoreText.setSpan(new StyleSpan(Typeface.BOLD), 0, seeMoreText.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            appointmentItems[MAX_APPOINTMENTS] = spannableSeeMoreText.toString();




            builder.setItems(appointmentItems, (dialog, which) -> {
                if (which == MAX_APPOINTMENTS) {

                    redirectToAppointmentsPage();
                } else {
                    Appointment selectedAppointment = appointments.get(which);
                    showAppointmentDialog(selectedAppointment);
                }
            });
        } else {
            appointmentItems = new String[upcomingAppointmentsCount];
            for (int i = 0; i < upcomingAppointmentsCount; i++) {
                Appointment appointment = appointments.get(i);
                String appointmentText = appointment.getPatientName() + " - " + appointment.getDate() + " - " + appointment.getTime();
                appointmentItems[i] = appointmentText + separator;
            }
            builder.setItems(appointmentItems, (dialog, which) -> {
                Appointment selectedAppointment = appointments.get(which);
                showAppointmentDialog(selectedAppointment);
            });
        }

        builder.show();
    }


    private void fetchUpcomingAppointments() {
        Request request = new Request.Builder()
                .url(UPCOMING_APPOINTMENTS_URL)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                // Handle network request failure
                Log.e(TAG, "Failed to fetch upcoming appointments: " + e.getMessage());
                runOnUiThread(() -> Toast.makeText(R7Main.this, "Failed to fetch upcoming appointments", Toast.LENGTH_SHORT).show());
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) {
                // Handle network request success
                try {
                    if (response.isSuccessful()) {
                        assert response.body() != null;
                        String responseData = response.body().string();
                        // Process the response data
                        runOnUiThread(() -> {
                            // Update UI with the fetched data
                            // For example, parse the JSON response and populate the appointment cards
                            List<Appointment> appointments = parseAppointmentsFromJson(responseData);
                            assert appointments != null;
                            populateAppointmentCards(appointments);


                        });
                    } else {
                        // Handle unsuccessful response
                        Log.e(TAG, "Failed to fetch upcoming appointments. Response code: " + response.code());
                        runOnUiThread(() -> Toast.makeText(R7Main.this, "Failed to fetch upcoming appointments", Toast.LENGTH_SHORT).show());
                    }
                } catch (IOException e) {
                    Log.e(TAG, "Exception occurred while processing network response: " + e.getMessage());
                }
            }
        });
    }

    private List<Appointment> parseAppointmentsFromJson(String json) {
        Type listType = new TypeToken<List<Appointment>>() {
        }.getType();
        try {
            return gson.fromJson(json, listType);
        } catch (Exception e) {
            Log.e(TAG, "Failed to parse JSON: " + e.getMessage());
        }
        return null;
    }

    private void populateAppointmentCards(List<Appointment> appointments) {
        // Clear the appointmentCardsContainer
        LinearLayout appointmentCardsContainer = findViewById(R.id.appointmentCardsContainer);
        appointmentCardsContainer.removeAllViews();

        // Check for null appointments list
        if (appointments == null) {
            Log.e(TAG, "Appointments list is null");
            return;
        }

        // Iterate through the appointments list and create/update the appointment cards
        for (Appointment appointment : appointments) {
            try {
                View appointmentCard = getLayoutInflater().inflate(R.layout.appointment_card_r7, appointmentCardsContainer, false);

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
                textDate.setTextColor(ContextCompat.getColor(this, R.color.green));
                textTime.setTextColor(getResources().getColor(R.color.green));
                textPatientName.setTextColor(Color.BLACK);

                // Set OnClickListener on appointment card
                appointmentCard.setOnClickListener(v -> showAppointmentDialog(appointment));

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

    private void showAppointmentDialog(Appointment appointment) {
        AlertDialog.Builder builder = new AlertDialog.Builder(R7Main.this, R.style.AlertDialogButtonStyle);
        builder.setTitle("Απόφαση Ραντεβού");
        builder.setIcon(R.drawable.baseline_access_time_24);

        builder.setMessage("Θέλετε να πραγματοποιήσετε το ραντεβού σας " + appointment.getDate() +
                "\n ,την ώρα  " + appointment.getTime() +
                "\n, για τον ασθενή " + appointment.getPatientName() + " ;");
        builder.setPositiveButton("Accept", (dialog, which) -> {
            // Handle accept action
            // You can perform any necessary operations when the appointment is accepted
            acceptAppointment(appointment);
        });
        builder.setNegativeButton("Άκυρο", (dialog, which) -> dialog.dismiss());
        builder.setCancelable(false);
        builder.create().show();
    }


    private void acceptAppointment(Appointment appointment) {
        // Update the TextView with a green border
        for (int i = 0; i < cardContainer.getChildCount(); i++) {
            View appointmentCard = cardContainer.getChildAt(i);
            TextView textPatientName = appointmentCard.findViewById(R.id.textPatientName);

            try {
                if (textPatientName.getText().toString().equals(appointment.getPatientName())) {
                    // Apply the green border to the appointment card
                    appointmentCard.setBackgroundResource(R.drawable.roundedshape_accepted);

                    appointmentCard.setClickable(false);

                    // Send the patient name back to the server
                    sendPatientNameToServer(appointment.getPatientName());


                    break;
                }
            } catch (Exception e) {
                Log.e(TAG, "Exception occurred while accepting appointment: " + e.getMessage());
            }
        }
    }

    private void sendPatientNameToServer(String patientName) {
        RequestBody requestBody = new FormBody.Builder()
                .add("patientName", patientName)
                .build();

        Request request = new Request.Builder()
                .url(MOVE_TO_ACCEPTED_URL)
                .post(requestBody)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                // Handle network request failure
                Log.e(TAG, "Failed to send patient name: " + e.getMessage());
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) {
                // Handle network request success
                try (response) {
                    if (response.isSuccessful()) {
                        // Process the response if needed
                        assert response.body() != null;
                        String responseData = response.body().string();
                        Log.d(TAG, "Response: " + responseData);
                    } else {
                        // Handle unsuccessful response
                        Log.e(TAG, "Failed to send patient name. Response code: " + response.code());
                    }
                } catch (IOException e) {
                    Log.e(TAG, "Exception occurred while processing network response: " + e.getMessage());
                }
            }
        });
    }



}