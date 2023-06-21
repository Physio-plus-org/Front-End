package com.example.physio_plus_app.R7;


public class AppointmentR7 {

    private String date;
    private String status;
    private String time;
    private String patientName;

    private String patientId;

    public AppointmentR7(String date, String time, String patientName,String status,String patientId) {
        this.date = date;
        this.time = time;
        this.patientName = patientName;
        this.status = status;
        this.patientId = patientId;
    }

    public String getDate() {
        return date;
    }

    public String getStatus() {
        return status;
    }
    public String getTime() {
        return time;
    }

    public String getPatientName() {
        return patientName;
    }


    public String getPatientId(){ return patientId;}
}
