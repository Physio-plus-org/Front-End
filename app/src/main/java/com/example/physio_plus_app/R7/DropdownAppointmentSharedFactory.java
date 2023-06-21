package com.example.physio_plus_app.R7;


import android.graphics.Typeface;
import android.os.StrictMode;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.StyleSpan;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

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

public class DropdownAppointmentSharedFactory extends AppCompatActivity {


    public RelativeLayout cardContainer;

    TextView redBubble;

    public final StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();

    public static final int MAX_APPOINTMENTS = 3;


    public static final String TAG = "DropdownAppointmentSharedFactory";
    public static final String MOVE_TO_ACCEPTED_URL ="https://physioplus.000webhostapp.com/R7/move_to_accepted.php";


    public static final String TEST_CONNECTION_URL = "https://physioplus.000webhostapp.com/R7/verify_connection.php";
    public static final String UPCOMING_APPOINTMENTS_URL = "https://physioplus.000webhostapp.com/R7/fetch_upcomingAppoint.php";


    public final Gson gson;
    public final OkHttpClient client;

    public final AppCompatActivity currActivity;

    public DropdownAppointmentSharedFactory(AppCompatActivity currActivity) {
        this.currActivity = currActivity;
        gson = new Gson();
        client = new OkHttpClient();
    }

    public void testConnection() {
        Request request = new Request.Builder()
                .url(TEST_CONNECTION_URL)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                // Handle network request failure
                Log.e(TAG, "Failed to test connection: " + e.getMessage());
                showToast("Failed to test connection");
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) {
                // Handle network request success
                if (response.isSuccessful()) {
                    // Connection is successful
                    Log.d(TAG, "Connection test successful");
                    showToast("Connection test successful");
                } else {
                    // Connection is unsuccessful
                    Log.e(TAG, "Failed to test connection. Response code: " + response.code());
                    showToast("Failed to test connection");
                }
            }
        });
    }


    public void fetchUpcomingAppointmentsForDropdown(AppCompatActivity currActivity) {

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
                runOnUiThread(() -> Toast.makeText(currActivity, "Failed to fetch upcoming appointments", Toast.LENGTH_SHORT).show());
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) {
                // Handle network request success
                try {
                    if (response.isSuccessful()) {
                        String responseData = response.body().string();
                        // Process the response data
                        runOnUiThread(() -> {
                            // Update UI with the fetched data
                            try {
                                List<AppointmentR7> appointments = parseAppointmentsFromJson(responseData);
                                if (appointments != null) {
                                    showAppointmentDropdown(appointments,currActivity,redBubble);
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

    public void showAppointmentDropdown(List<AppointmentR7> appointments, AppCompatActivity currActivity, TextView redBubble) {

        AlertDialog.Builder builder = new AlertDialog.Builder(currActivity);
        builder.setTitle("Επερχόμενα Ραντεβού");
        builder.setIcon(R.drawable.r7_baseline_access_time_24);

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
                AppointmentR7 appointment = appointments.get(i);
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
                    AppointmentR7 selectedAppointment = appointments.get(which);
                    showAppointmentDialog(selectedAppointment,currActivity);
                }
            });
        } else {
            appointmentItems = new String[upcomingAppointmentsCount];
            for (int i = 0; i < upcomingAppointmentsCount; i++) {
                AppointmentR7 appointment = appointments.get(i);
                String appointmentText = appointment.getPatientName() + " - " + appointment.getDate() + " - " + appointment.getTime();
                appointmentItems[i] = appointmentText + separator;
            }
            builder.setItems(appointmentItems, (dialog, which) -> {
                AppointmentR7 selectedAppointment = appointments.get(which);
                showAppointmentDialog(selectedAppointment,currActivity);
            });
        }

        builder.show();
    }


    public void showAppointmentDialog(AppointmentR7 appointment, AppCompatActivity currActivity) {
        AlertDialog.Builder builder = new AlertDialog.Builder(currActivity, R.style.AlertDialogTheme);
        builder.setTitle("Απόφαση Ραντεβού");
        builder.setIcon(R.drawable.r7_baseline_access_time_24);

        builder.setMessage("Θέλετε να πραγματοποιήσετε το ραντεβού σας " + appointment.getDate() +
                "\n ,την ώρα  " + appointment.getTime() +
                "\n, για τον ασθενή " + appointment.getPatientName() + " ;");
        builder.setPositiveButton("Accept", (dialog, which) -> {
            // Handle accept action
            // You can perform any necessary operations when the appointment is accepted
            acceptAppointment(appointment,currActivity,cardContainer);
        });
        builder.setNegativeButton("Άκυρο", (dialog, which) -> dialog.dismiss());
        builder.setCancelable(false);
        builder.create().show();
    }


    public void acceptAppointment(AppointmentR7 appointment, AppCompatActivity currActivity, RelativeLayout cardContainer) {
        // Update the TextView with a green border
        for (int i = 0; i < cardContainer.getChildCount(); i++) {
            View appointmentCard = cardContainer.getChildAt(i);
            TextView textPatientName = appointmentCard.findViewById(R.id.textPatientName);

            try {
                if (textPatientName.getText().toString().equals(appointment.getPatientName())) {
                    // Apply the green border to the appointment card
                    appointmentCard.setBackgroundResource(R.drawable.r7_roundedshape_accepted);

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

    public void sendPatientNameToServer(String patientName) {
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



    public void redirectToAppointmentsPage() {
        recreate();

    }
    public List<AppointmentR7> parseAppointmentsFromJson(String json) {
        Type listType = new TypeToken<List<AppointmentR7>>() {
        }.getType();
        try {
            return gson.fromJson(json, listType);
        } catch (Exception e) {
            Log.e(TAG, "Failed to parse JSON: " + e.getMessage());
        }
        return null;
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






}
