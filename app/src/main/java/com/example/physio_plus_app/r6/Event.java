package com.example.physio_plus_app;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event {

    int event_id;
    String physio_center;
    String patient_id;
    LocalDateTime date_time;
    String dateString;
    String status;
    int image;



    public Event(int event_id, String physio_center, String patient_id,  LocalDateTime date_time, String status, int image) {
        this.patient_id = patient_id;
        this.event_id = event_id;
        this.physio_center = physio_center;
        this.date_time = date_time;
        this.status = status;
        this.image = image;
    }

    public String getPatient_id() {
        return patient_id;
    }

    public void setPatient_id(String patient_name) {
        this.patient_id = patient_name;
    }

    public int getEvent_id() {
        return event_id;
    }

    public void setEvent_id(int event_id) {
        this.event_id = event_id;
    }

    public String getDateString() {
        //TODO return String. LocalTimeFormatter
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm");
        dateString = date_time.format(dateTimeFormatter);
        return dateString;
    }

    public LocalDateTime getDate_time() {
        return date_time;
    }

    public void setDate(LocalDateTime date) {
        this.date_time = date;
    }
    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getPhysio_center() {
        return physio_center;
    }

    public void setPhysio_center(String physio_center) {
        this.physio_center = physio_center;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
