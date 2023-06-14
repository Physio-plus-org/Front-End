package com.example.physio_plus_app;


import org.joda.time.LocalDateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;


public class RequestObj {
    private int id;
    private String physio_center;
    private String patient_id;
    private LocalDateTime date_time;
    private String date;

    public RequestObj(int id, String physio_center, String patient_id, LocalDateTime date_time) {
        this.id = id;
        this.physio_center = physio_center;
        this.patient_id = patient_id;
        this.date_time = date_time;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            DateTimeFormatter myFormat = DateTimeFormat.forPattern("yyyy-MM-dd");
            this.date = date_time.toString(myFormat);
        }
    }

    public String getDate() {
        return date;
    }

    public LocalDateTime getDate_time() {
        return date_time;
    }
}
