package com.example.express.Classes;

public class ActiveReportsModel {

    String status;
    String complainNum;



    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getComplainNum() {
        return complainNum;
    }

    public void setComplainNum(String complainNum) {
        this.complainNum = complainNum;
    }

    public ActiveReportsModel(String status, String complainNum) {
        this.status = status;
        this.complainNum = complainNum;
    }
}
