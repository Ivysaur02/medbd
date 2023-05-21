package com.example.medbd.models;

public class Ticket {
    private String date_appointment;
    private String date_receipt;
    private String id_ticket;
    private String pat_otch;
    private String dc_fam;
    private String dc_im;
    private String dc_otch;
    private String name_of_specialty;
    private String room;
    private String us_fam;
    private String us_im;
    private String us_otch;
    private String pat_fam;
    private String pat_im;

    public Ticket(String date_appointment, String date_receipt, String id_ticket,
                  String pat_otch, String dc_fam, String dc_im, String dc_otch, String name_of_specialty,
                  String room, String us_fam, String us_im, String us_otch, String pat_fam, String pat_im) {
        this.date_appointment = date_appointment;
        this.date_receipt = date_receipt;
        this.id_ticket = id_ticket;
        this.pat_otch = pat_otch;
        this.dc_fam = dc_fam;
        this.dc_im = dc_im;
        this.dc_otch = dc_otch;
        this.name_of_specialty = name_of_specialty;
        this.room = room;
        this.us_fam = us_fam;
        this.us_im = us_im;
        this.us_otch = us_otch;
        this.pat_fam = pat_fam;
        this.pat_im = pat_im;
    }

    public Ticket() {
    }

    public void setDate_appointment(String date_appointment) {
        this.date_appointment = date_appointment;
    }

    public void setDate_receipt(String date_receipt) {
        this.date_receipt = date_receipt;
    }

    public void setId_ticket(String id_ticket) {
        this.id_ticket = id_ticket;
    }

    public void setPat_otch(String pat_otch) {
        this.pat_otch = pat_otch;
    }

    public void setDc_fam(String dc_fam) {
        this.dc_fam = dc_fam;
    }

    public void setDc_im(String dc_im) {
        this.dc_im = dc_im;
    }

    public void setDc_otch(String dc_otch) {
        this.dc_otch = dc_otch;
    }

    public void setName_of_specialty(String name_of_specialty) {
        this.name_of_specialty = name_of_specialty;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public void setUs_fam(String us_fam) {
        this.us_fam = us_fam;
    }

    public void setUs_im(String us_im) {
        this.us_im = us_im;
    }

    public void setUs_otch(String us_otch) {
        this.us_otch = us_otch;
    }

    public void setPat_fam(String pat_fam) {
        this.pat_fam = pat_fam;
    }

    public void setPat_im(String pat_im) {
        this.pat_im = pat_im;
    }

    public String getDate_appointment() {
        return date_appointment;
    }

    public String getDate_receipt() {
        return date_receipt;
    }

    public String getId_ticket() {
        return id_ticket;
    }

    public String getPat_otch() {
        return pat_otch;
    }

    public String getDc_fam() {
        return dc_fam;
    }

    public String getDc_im() {
        return dc_im;
    }

    public String getDc_otch() {
        return dc_otch;
    }

    public String getName_of_specialty() {
        return name_of_specialty;
    }

    public String getRoom() {
        return room;
    }

    public String getUs_fam() {
        return us_fam;
    }

    public String getUs_im() {
        return us_im;
    }

    public String getUs_otch() {
        return us_otch;
    }

    public String getPat_fam() {
        return pat_fam;
    }

    public String getPat_im() {
        return pat_im;
    }
}