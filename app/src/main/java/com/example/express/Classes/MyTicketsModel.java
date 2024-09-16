package com.example.express.Classes;

public class MyTicketsModel {

    String SeatNum;

    public String getSeatNum() {
        return SeatNum;
    }

    public void setSeatNum(String seatNum) {
        SeatNum = seatNum;
    }

    public String getPassengerName() {
        return passengerName;
    }

    public void setPassengerName(String passengerName) {
        this.passengerName = passengerName;
    }

    public MyTicketsModel(String seatNum, String passengerName) {
        SeatNum = seatNum;
        this.passengerName = passengerName;
    }

    String passengerName;


}
