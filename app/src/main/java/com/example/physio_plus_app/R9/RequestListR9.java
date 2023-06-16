package com.example.physio_plus_app.R9;

import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.util.ArrayList;

public class RequestListR9 {
    private ArrayList<RequestObjR9> requestList = new ArrayList<>();
    String range_start;
    String range_end;

    public RequestListR9(String ip, String range_start, String range_end){
        String url = "http://"+ip+"/physio_app_db/requestCreate.php?range_start="+ range_start +"&range_end="+range_end;

        try {
            OkHttpHandlerR9 okHttpHandler = new OkHttpHandlerR9();
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
