package com.example.medbd.models;

public class Patient extends Person {
    String sex;
    String date;
    String street;
    String home;
    String apart;
    String snils;
    String phone;

    public Patient(String id, String fam, String imya,
                   String otch, String sex, String date,
                   String street, String home, String apart,
                   String snils, String phone) {
        super(id, fam, imya, otch);
        this.sex = sex;
        this.date = date;
        this.snils = snils;
        this.street = street != null ? street : "";
        this.home = home != null ? home : "";
        this.apart = apart != null ? apart : "";
        this.phone = phone != null ? phone : "";
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getHome() {
        return home;
    }

    public void setHome(String home) {
        this.home = home;
    }

    public String getApart() {
        return apart;
    }

    public void setApart(String apart) {
        this.apart = apart;
    }

    public String getSnils() {
        return snils;
    }

    public void setSnils(String snils) {
        this.snils = snils;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
