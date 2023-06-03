package com.example.physio_plus_app;


public class Appointment {

    private String date;
    private String time;
    private String patientName;

    public Appointment(String date, String time, String patientName) {
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
