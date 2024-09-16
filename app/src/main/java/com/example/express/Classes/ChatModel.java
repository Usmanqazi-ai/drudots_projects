package com.example.express.Classes;

import android.net.Uri;

public class ChatModel {
    public static final int ViewTypeSent = 0;
    public static final int ViewTypeReceived = 1;
    public static final int msgTypeImg = 0;
    public static final int msgTypeTxt = 1;
    private int msgType;

    public int getMsgType() {
        return msgType;
    }

    public void setMsgType(int msgType) {
        this.msgType = msgType;
    }

    public String getSentMsg() {
        return sentMsg;
    }

    public void setSentMsg(String sentMsg) {
        this.sentMsg = sentMsg;
    }

    public Uri getSentImage() {
        return sentImage;
    }

    public void setSentImage(Uri sentImage) {
        this.sentImage = sentImage;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getViewType() {
        return ViewType;
    }

    public void setViewType(int viewType) {
        ViewType = viewType;
    }

    public ChatModel(int msgType, String sentMsg, Uri sentImage, String time, int viewType) {
        this.msgType = msgType;
        this.sentMsg = sentMsg;
        this.sentImage = sentImage;
        this.time = time;
        ViewType = viewType;
    }

    private String sentMsg;
    private Uri sentImage;

    private String time;

    private int ViewType;




}
