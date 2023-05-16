package com.example.medbd.models;

public class TimeTable {
    String fam,im,otch,info,date;


    public TimeTable(String fam, String im, String otch, String info, String date) {
        this.fam = fam;
        this.im = im;
        this.otch = otch;
        this.info = info != null ? info : "";
        this.date = date;
    }

    public String getFam() {
        return fam;
    }

    public String getIm() {
        return im;
    }

    public String getOtch() {
        return otch;
    }

    public String getInfo() {
        return info;
    }

    public String getDate() {
        return date;
    }
}
