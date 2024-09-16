package com.example.express.Classes;

public class CountryModel {

    private String name;
    private String dial_code;
    private String code;
    private String digit1;
    private String digit2;
    private String format;
    private String flag;

    public CountryModel(String name, String dial_code, String code, String digit1, String digit2, String format, String flag) {
        this.name = name;
        this.dial_code = dial_code;
        this.code = code;
        this.digit1 = digit1;
        this.digit2 = digit2;
        this.format = format;
        this.flag = flag;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDial_code() {
        return dial_code;
    }

    public void setDial_code(String dial_code) {
        this.dial_code = dial_code;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDigit1() {
        return digit1;
    }

    public void setDigit1(String digit1) {
        this.digit1 = digit1;
    }

    public String getDigit2() {
        return digit2;
    }

    public void setDigit2(String digit2) {
        this.digit2 = digit2;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

}
