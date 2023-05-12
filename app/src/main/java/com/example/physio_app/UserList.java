package com.example.physio_app;

import java.util.ArrayList;
import java.util.List;

public class UserList {

    ArrayList<User> userlist = new ArrayList<User>();

    public UserList(String ip) {
        String url = "https://" + ip + "/logHistory.php";

        try {
            HttpHandler okHttpHandler = new HttpHandler();
            userlist = okHttpHandler.logHistory(url);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<String> getFirstNames() {
        List<String> first = new ArrayList<String>();
        for (int i = 0; i < userlist.size(); i++) {
            first.add(userlist.get(i).getFirstName());
        }
        return first;
    }

    public List<String> getLastName() {
        List<String> last = new ArrayList<String>();
        for (int i = 0; i < userlist.size(); i++) {
            last.add(userlist.get(i).getLastName());
        }
        return last;
    }

}
