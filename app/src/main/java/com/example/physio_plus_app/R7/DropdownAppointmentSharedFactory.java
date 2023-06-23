package com.example.physio_plus_app.R7;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.StrictMode;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.StyleSpan;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;
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

public class DropdownAppointmentSharedFactory extends AppCompatActivity {


    public RelativeLayout cardContainer;



    public final StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();

    public static final int MAX_APPOINTMENTS = 3;


    public static final String TAG = "DropdownAppointmentSharedFactory";
    public static final String MOVE_TO_ACCEPTED_URL ="https://physioplus.000webhostapp.com/R7/move_to_accepted.php";


    public static final String TEST_CONNECTION_URL = "https://physioplus.000webhostapp.com/R7/verify_connection.php";
    public static final String UPCOMING_APPOINTMENTS_URL = "https://physioplus.000webhostapp.com/R7/fetch_upcomingAppoint.php";



    public final OkHttpClient client;

    public final AppCompatActivity currActivity;

    public DropdownAppointmentSharedFactory(AppCompatActivity currActivity) {
        this.currActivity = currActivity;
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


    public void fetchUpcomingAppointmentsForDropdown(AppCompatActivity currActivity, TextView  redBubble) {

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

        Log.d(TAG, "upcomingAppointmentsCount: " + upcomingAppointmentsCount);

        if (upcomingAppointmentsCount > 0) {
            redBubble.setVisibility(View.VISIBLE);
            redBubble.setText(String.valueOf(upcomingAppointmentsCount));
        } else {
            redBubble.setVisibility(View.GONE);
        }
        String separator = "\n⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯\n";


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
                if (which >= MAX_APPOINTMENTS) {

                    redirectToAppointmentsPage(currActivity);
                } else {
                    AppointmentR7 selectedAppointment = appointments.get(which);
                    showAppointmentDialog(selectedAppointment,currActivity ,  appointments,redBubble);
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
                showAppointmentDialog(selectedAppointment,currActivity ,appointments,redBubble);
            });
        }




        ImageButton MoveToR7 = new ImageButton(currActivity);
        MoveToR7.setImageResource(R.drawable.icon_map_fullscreen); // Set the icon for the button
        MoveToR7.setBackground(null); // Remove the button background
        MoveToR7.setScaleType(ImageView.ScaleType.CENTER_INSIDE); // Set the scale type

        LinearLayout.LayoutParams MoveToR7Params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );
        MoveToR7Params.gravity = Gravity.END | Gravity.TOP; // Set gravity to top right corner
        MoveToR7.setLayoutParams(MoveToR7Params); // Apply the layout parameters

        LinearLayout dialogLayout = new LinearLayout(currActivity);
        dialogLayout.setOrientation(LinearLayout.VERTICAL);
        dialogLayout.addView(MoveToR7);
        builder.setView(dialogLayout);

        MoveToR7.setOnClickListener(view -> {
            Intent intent = new Intent(currActivity,R7.class);
            currActivity.startActivity(intent);
        });

        builder.show();
    }


    public void showAppointmentDialog(AppointmentR7 appointment, AppCompatActivity currActivity, List<AppointmentR7> appointmentList,TextView redBubble) {
        AlertDialog.Builder builder = new AlertDialog.Builder(currActivity, R.style.AlertDialogTheme);
        builder.setTitle("Απόφαση Ραντεβού");
        builder.setIcon(R.drawable.r7_baseline_access_time_24);
        AtomicBoolean canceled = new AtomicBoolean(false);

        builder.setMessage("Θέλετε να πραγματοποιήσετε το ραντεβού σας " + appointment.getDate() +
                "\n ,την ώρα  " + appointment.getTime() +
                "\n, για τον ασθενή " + appointment.getPatientName() + " ;");
        builder.setPositiveButton("Accept", (dialog, which) -> {

            canceled.set(false);
            updateDropdownList(appointment,appointmentList,redBubble);
            sendPatientNameToServer(appointment, canceled.get());

        });
        builder.setNegativeButton("Διαγραφή", (dialog, which)-> {
            canceled.set(true);
            updateDropdownList(appointment,appointmentList,redBubble);
            sendPatientNameToServer(appointment, canceled.get());
        });
        builder.setNeutralButton("Ακυρο",(dialog,which)->dialog.dismiss());
        builder.setCancelable(false);
        builder.create().show();
    }



    public void updateDropdownList(AppointmentR7 appointment , List<AppointmentR7> appointmentList,TextView redBubble) {


            for (int i = 0; i < appointmentList.size(); i++) {
                AppointmentR7 patient = appointmentList.get(i);
                if (patient.getPatientName().equals(appointment.getPatientName())) {
                    appointmentList.remove(i);
                    break;
                }
            }
            redBubble.setText(String.valueOf(appointmentList.size()));
        }



    public void sendPatientNameToServer(AppointmentR7 appointment,boolean canceled) {
        RequestBody requestBody = new FormBody.Builder()
                .add("patientId", appointment.getPatientId())
                .add("status",appointment.getStatus())
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



    public void redirectToAppointmentsPage(AppCompatActivity currActivity) {
        Intent i = new Intent(currActivity, R7.class);
       currActivity.startActivity(i);

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
                    String patientId =appointmentObject.getString("patient_id");


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






}
