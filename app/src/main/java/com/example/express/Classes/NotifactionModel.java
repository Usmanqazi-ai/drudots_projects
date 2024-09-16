package com.example.express.Classes;

public class NotifactionModel {

    String massege;

    String time;

    public String getMassege() {
        return massege;
    }

    public void setMassege(String massege) {
        this.massege = massege;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public NotifactionModel(String massege, String time) {
        this.massege = massege;
        this.time = time;
    }
}
