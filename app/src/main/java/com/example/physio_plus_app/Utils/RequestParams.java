package com.example.physio_plus_app.Utils;

import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;

public class RequestParams {
    private Hashtable<String, String> params;
    public RequestParams() {
        this.params = new Hashtable<>();
    }
    public RequestParams add(String key, String value) {
        params.put(key, value);
        return this;
    }
    public void add(Hashtable<String, String> p) {
        params = p;
    }
    public void add(List<String> keys , List<String> values) {
        Iterator<String> kIterator = keys.iterator();
        Iterator<String> vIterator = values.iterator();
        while (kIterator.hasNext() && vIterator.hasNext()) {
            params.put(kIterator.next(), vIterator.next());
        }
    }
    public Hashtable<String, String> getParamsTable() {
        return params;
    }
    public Iterator<String> iterator() {
        return params.keySet().iterator();
    }
    public String get(String key) {
        return params.get(key);
    }
}
