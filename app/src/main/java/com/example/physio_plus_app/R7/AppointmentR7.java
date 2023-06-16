package com.example.physio_plus_app.R7;


public class AppointmentR7 {

    private String date;
    private String time;
    private String patientName;

    public AppointmentR7(String date, String time, String patientName) {
        this.date = date;
        this.time = time;
        this.patientName = patientName;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

    public String getPatientName() {
        return patientName;
    }
}
