package com.example.physio_plus_app;

public class ParamsR2 {
    private String title, desc, code, price;
    public ParamsR2(String t, String d, String c, String p) {
        this.title = t;
        this.desc = d;
        this.code = c;
        this.price = p;
    }

    public String getTitle() {
        return title;
    }

    public String getDesc() {
        return desc;
    }

    public String getCode() {
        return code;
    }

    public String getPrice() {
        return price;
    }
}
