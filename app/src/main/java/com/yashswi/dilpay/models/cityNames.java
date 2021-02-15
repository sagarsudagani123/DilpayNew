package com.yashswi.dilpay.models;

import java.util.ArrayList;

public class cityNames {
    private static cityNames obj=null;
    ArrayList<String> names=new ArrayList<>();
    private cityNames(){
    }
    public static cityNames getInstance(){
        if (obj == null){
            obj=new cityNames();
        }
        return obj;
    }

    public ArrayList<String> getNames() {
        return names;
    }

    public void setNames(String names) {
        this.names.add(names);
    }
}