package com.example.physio_plus_app.R9;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializationConfig;
import org.codehaus.jackson.map.annotate.JsonView;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class RequestObjectR9 {
    int event_id;

    @JsonView(RequestObjectJsonViewR9.sendRequest.class)
    java.lang.String physio_center;

    @JsonView(RequestObjectJsonViewR9.sendRequest.class)
    java.lang.String patient_id;
    LocalDateTime date_time;


    java.lang.String dateString;

    @JsonView(RequestObjectJsonViewR9.sendRequest.class)
    java.lang.String status;

    @JsonView(RequestObjectJsonViewR9.sendRequest.class)
    String requestedDate;



    public RequestObjectR9(int event_id, java.lang.String physio_center, java.lang.String patient_id, LocalDateTime date_time, java.lang.String status) {
        this.patient_id = patient_id;
        this.event_id = event_id;
        this.physio_center = physio_center;
        this.date_time = date_time;
        this.status = status;
    }

    public RequestObjectR9(java.lang.String physio_center, java.lang.String patient_id, String requestedDate, java.lang.String status) {
        this.patient_id = patient_id;
        this.physio_center = physio_center;
        this.requestedDate = requestedDate;
        this.status = status;
    }

    public java.lang.String getPatient_id() {
        return patient_id;
    }

    public void setPatient_id(java.lang.String patient_name) {
        this.patient_id = patient_name;
    }

    public int getEvent_id() {
        return event_id;
    }

    public void setEvent_id(int event_id) {
        this.event_id = event_id;
    }

    public java.lang.String getDateString() {
        //TODO return String. LocalTimeFormatter
        DateTimeFormatter dateTimeFormatter = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            dateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm");
            dateString = date_time.format(dateTimeFormatter);
        }

        return dateString;
    }

    public LocalDateTime getDate_time() {
        return date_time;
    }


    public java.lang.String getPhysio_center() {
        return physio_center;
    }

    public void setPhysio_center(java.lang.String physio_center) {
        this.physio_center = physio_center;
    }

    public java.lang.String getStatus() {
        return status;
    }

    public void setStatus(java.lang.String status) {
        this.status = status;
    }

    public String toJSON(){
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationConfig.Feature.DEFAULT_VIEW_INCLUSION, false);

        try {
            String jsonStringOut = mapper.writerWithView(RequestObjectJsonViewR9.sendRequest.class).writeValueAsString(this);
            return jsonStringOut;
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }
}
