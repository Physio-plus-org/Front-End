package com.example.physio_plus_app.R6;

import java.util.ArrayList;
import java.util.List;

public class RequestEventR6 {
    private final String myIP = "192.168.1.100";
    private List<EventR6> events = new ArrayList<>();
    String range_start;
    String range_end;
    String status;


    public RequestEventR6() {
    }


    public List<EventR6> requestEvents(String range_start, String range_end) {


        String url = "http://"+myIP+"/physio_stl/requestEvents.php?range_start="+ range_start +"&range_end="+range_end+"&status=UPCOMING";

        try {
            OkHttpHandlerR6 okHttpHandler = new OkHttpHandlerR6();
            this.events = okHttpHandler.reqEvents(url);
        } catch (Exception e) {
            e.printStackTrace();
        }


        return events;
    }
}