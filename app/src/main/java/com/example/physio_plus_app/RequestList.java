package com.example.physio_plus_app;

import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.util.ArrayList;

public class RequestList {
    private ArrayList<RequestObj> requestList = new ArrayList<>();
    String range_start;
    String range_end;

    public RequestList(String ip, String range_start, String range_end){
        String url = "http://"+ip+"/physio_app_db/requestCreate.php?range_start="+ range_start +"&range_end="+range_end;

        try {
            OkHttpHandler okHttpHandler = new OkHttpHandler();
            this.requestList = okHttpHandler.testPrint(url);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ArrayList<String> getRequestHours() {
        ArrayList<String> reservedHours = new ArrayList<>();
        this.requestList.forEach( request -> {
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                DateTimeFormatter myFormat = DateTimeFormat.forPattern("HH");
                reservedHours.add(request.getDate_time().toString(myFormat));
            }
        });
        return reservedHours;
    }

}
