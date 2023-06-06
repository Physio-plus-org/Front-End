package com.example.physio_plus_app;

import java.util.ArrayList;
import java.util.List;

public class RequestEvent {
    private final String myIP = "192.168.1.100";
    private List<Event> events = new ArrayList<>();
    String range_start;
    String range_end;
    String status;


    public RequestEvent() {
    }


    public List<Event> requestEvents(String range_start, String range_end) {


        String url = "http://"+myIP+"/physio_stl/requestEvents.php?range_start="+ range_start +"&range_end="+range_end+"&status=UPCOMING";

        try {
            OkHttpHandler okHttpHandler = new OkHttpHandler();
            this.events = okHttpHandler.reqEvents(url);
        } catch (Exception e) {
            e.printStackTrace();
        }


        return events;
    }
}
