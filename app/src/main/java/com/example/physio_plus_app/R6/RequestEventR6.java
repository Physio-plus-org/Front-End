package com.example.physio_plus_app.R6;

import android.widget.Toast;

import com.example.physio_plus_app.Utils.HttpHandler.PhysioCenter.AllRequestsHandler;
import com.example.physio_plus_app.Utils.RequestParams;

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


        String url = "https://physioplus.000webhostapp.com/R6/requestEvents.php?range_start="+ range_start +"&range_end="+range_end+"&status=UPCOMING";

        try {
//            OkHttpHandlerR6 okHttpHandler = new OkHttpHandlerR6();
//            this.events = okHttpHandler.reqEvents(url);
            this.events = AllRequestsHandler.request(
                    new RequestParams()
                            .add("range_start", range_start)
                            .add("range_end", range_end)
                            .add("status", "UPCOMING")
            );
        } catch (Exception e) {
            e.printStackTrace();
        }
        return events;
    }
}
