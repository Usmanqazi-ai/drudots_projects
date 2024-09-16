package com.example.express.Classes;

public class FAQModel {
    String qus;
    String ans;
    boolean isShow = false;

    public boolean isShow() {
        return isShow;
    }

    public void setShow(boolean show) {
        isShow = show;
    }

    public String getQus() {
        return qus;
    }

    public void setQus(String qus) {
        this.qus = qus;
    }

    public String getAns() {
        return ans;
    }

    public void setAns(String ans) {
        this.ans = ans;
    }

    public FAQModel(String qus, String ans) {
        this.qus = qus;
        this.ans = ans;
    }
}
