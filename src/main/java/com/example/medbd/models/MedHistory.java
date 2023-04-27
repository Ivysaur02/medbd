package com.example.medbd.models;

public class MedHistory {
    String id_hist,type,date,info;

    public MedHistory(String id, String type, String date, String info) {
        this.id_hist = id;
        this.type = type;
        this.date = date;
        this.info = info != null ? info : "";
    }

    public String getId_hist() {
        return id_hist;
    }

    public void setId_hist(String id_hist) {
        this.id_hist = id_hist;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}
