package com.example.express.Classes;

public class BusResultModel {
          String busCompany;
          String timeDeapature;

    public String getBusCompany() {
        return busCompany;
    }

    public void setBusCompany(String busCompany) {
        this.busCompany = busCompany;
    }

    public String getTimeDeapature() {
        return timeDeapature;
    }

    public void setTimeDeapature(String timeDeapature) {
        this.timeDeapature = timeDeapature;
    }

    public BusResultModel(String busCompany, String timeDeapature) {
        this.busCompany = busCompany;
        this.timeDeapature = timeDeapature;
    }
}
