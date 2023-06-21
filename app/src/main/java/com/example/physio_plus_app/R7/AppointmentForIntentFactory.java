package com.example.physio_plus_app.R7;




import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.GradientDrawable;
import android.os.Build;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.example.physio_plus_app.R;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class AppointmentForIntentFactory extends AppCompatActivity {

    /* Topbar */


    private static final String TAG = "R7";
    private final AppCompatActivity currActivity;
    private RelativeLayout cardContainer;

    TextView redBubble;



    private OkHttpClient client;
    private final StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();

    private static final int MAX_APPOINTMENTS = 3;

    private static final String MOVE_TO_ACCEPTED_URL = "https://physioplus.000webhostapp.com/R7/move_to_accepted.php";
    private static final String TESTCON_URL = "https://physioplus.000webhostapp.com/R7/verify_connection.php";
    private static final String UPCOMING_APPOINTMENTS_URL = "https://physioplus.000webhostapp.com/R7/fetch_upcomingAppoint.php";

    public AppointmentForIntentFactory(AppCompatActivity currActivity) {
        this.currActivity = currActivity;
        client = new OkHttpClient();
    }

    public void fetchUpcomingAppointmentsForIntent(AppCompatActivity currActivity, TextView redBubble,LinearLayout layout) {
        Request request = new Request.Builder()
                .url(UPCOMING_APPOINTMENTS_URL)
                .post(new FormBody.Builder().build())
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                // Handle network request failure
                Log.e(TAG, "Failed to fetch upcoming appointments for dropdown: " + e.getMessage());
                e.printStackTrace();
                runOnUiThread(() -> Toast.makeText(currActivity, "Failed to fetch upcoming appointments", Toast.LENGTH_SHORT).show());
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) {
                // Handle network request success
                try {
                    if (response.isSuccessful()) {

                        String responseData = response.body().string();
                        Log.d(TAG, "parseAppointmentsFromJson: " + responseData);
                        // Process the response data
                        runOnUiThread(() -> {
                            // Update UI with the fetched data
                            try {
                                List<AppointmentR7> appointments = parseAppointmentsFromJson(responseData);
                                if (appointments != null) {
                                    populateAppointmentCards(appointments, layout, currActivity,layout);
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
                        runOnUiThread(() -> Toast.makeText(currActivity, "Failed to fetch upcoming appointments", Toast.LENGTH_SHORT).show());
                    }
                } catch (IOException e) {
                    Log.e(TAG, "Exception occurred while processing network response: " + e.getMessage());
                }
            }
        });
    }


    public void populateAppointmentCards(List<AppointmentR7> appointments, LinearLayout appointmentCardsContainer, AppCompatActivity currActivity,LinearLayout layout) {
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
                View appointmentCard = currActivity.getLayoutInflater().inflate(R.layout.r7_appointment_card, appointmentCardsContainer, false);

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
                textTime.setTextColor(currActivity.getResources().getColor(R.color.PrimaryGreen));
                textPatientName.setTextColor(Color.BLACK);

                // Set OnClickListener on appointment card
                appointmentCard.setOnClickListener(v -> showAppointmentDialog(appointment, currActivity, appointments, redBubble,layout));

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


    public void sendPatientNameToServer(AppointmentR7 appointment, boolean canceled) {
        RequestBody requestBody = new FormBody.Builder()
                .add("patientId", appointment.getPatientId())
                .add("status", appointment.getStatus())
                .add("canceled", String.valueOf(canceled))


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


    public void showAppointmentDialog(AppointmentR7 appointment, AppCompatActivity currActivity, List<AppointmentR7> appointmentList,TextView redBubble,LinearLayout layout) {
        AlertDialog.Builder builder = new AlertDialog.Builder(currActivity, R.style.AlertDialogTheme);
        builder.setTitle("Απόφαση Ραντεβού");
        builder.setIcon(R.drawable.r7_baseline_access_time_24);
        AtomicBoolean canceled = new AtomicBoolean(false);

        builder.setMessage("Θέλετε να πραγματοποιήσετε το ραντεβού σας " + appointment.getDate() +
                "\n ,την ώρα  " + appointment.getTime() +
                "\n, για τον ασθενή " + appointment.getPatientName() + " ;");
        builder.setPositiveButton("Accept", (dialog, which) -> {

            canceled.set(false);
            sendPatientNameToServer(appointment, canceled.get());
            updateDropdownList(appointment,appointmentList,redBubble,layout,canceled);
        });
        builder.setNegativeButton("Διαγραφή", (dialog, which)-> {
            canceled.set(true);
            updateDropdownList(appointment,appointmentList,redBubble,layout,canceled);
            sendPatientNameToServer(appointment, canceled.get());
        });
        builder.setNeutralButton("Ακυρο",(dialog,which)->dialog.dismiss());
        builder.setCancelable(false);
        builder.create().show();
    }

    public List<AppointmentR7> parseAppointmentsFromJson(String json) {
        List<AppointmentR7> appointments = new ArrayList<>();
        try {

            if (!json.isEmpty()) {
                JSONArray jsonArray = new JSONArray(json);

                int length = jsonArray.length();

                Log.d(TAG, "length of json array : " + length);


                for (int i = 0; i < length; i++) {
                    JSONObject appointmentObject = jsonArray.getJSONObject(i);

                    String dateTime = appointmentObject.getString("date_time");
                    String status = appointmentObject.getString("status");
                    String firstName = appointmentObject.getString("first_name");
                    String lastName = appointmentObject.getString("last_name");
                    String patientId = appointmentObject.getString("patient_id");


                    //Changes to some of the fields
                    String patientName = firstName + " " + lastName;
                    String[] dateTimeParts = dateTime.split(" ");
                    String date = dateTimeParts[0];
                    String time = dateTimeParts[1];


                    //Create the appointment


                    AppointmentR7 currAppointment = new AppointmentR7(date, time, patientName, status, patientId);
                    appointments.add(currAppointment);


                }

            }


        } catch (JSONException e) {
            e.printStackTrace();
            Log.e(TAG, "Exception occurred while processing the json file (inside the function) " + e.getMessage());
        }

        return appointments;
    }

    public void showToast(final String message) {
        // Display toast message on UI thread
        currActivity.runOnUiThread(() ->
                Toast.makeText(currActivity, message, Toast.LENGTH_SHORT).show());
    }

    public interface NetworkCallback<T> {
        void onSuccess(T data);

        void onFailure(Exception e);
    }


    public void updateDropdownList(AppointmentR7 appointment, List<AppointmentR7> appointmentList, TextView redBubble, LinearLayout layout, AtomicBoolean canceled) {
        if (canceled.get()) {
            // Remove the appointment and its associated view from the layout
            for (int i = 0; i < appointmentList.size(); i++) {
                AppointmentR7 patient = appointmentList.get(i);
                if (patient.getPatientName().equals(appointment.getPatientName())) {
                    appointmentList.remove(i);
                    layout.removeViewAt(i);
                    break;
                }
            }
        } else {
            // Set the border color of the appointment's view to green
            for (int i = 0; i < appointmentList.size(); i++) {
                AppointmentR7 patient = appointmentList.get(i);
                if (patient.getPatientName().equals(appointment.getPatientName())) {
                    // Get the appointment card view at the specified index
                    View appointmentCard = layout.getChildAt(i);
                    if (appointmentCard != null) {
                        // Apply the border drawable to the background of the appointment card

                           
                            appointmentCard.setBackgroundColor(R.drawable.r7_card_border);

                    }
                    break;
                }
            }
        }


    }


}


