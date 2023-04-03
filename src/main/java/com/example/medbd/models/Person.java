package com.example.medbd.models;

public class Person {
    int id;
    String Fam;
    String Imya;
    String Otch;

    public Person(int id, String fam, String imya, String otch) {
        this.id = id;
        Fam = fam;
        Imya = imya;
        Otch = otch;
    }

    public int getId() {
        return id;
    }

    public String getFam() {
        return Fam;
    }

    public String getImya() {
        return Imya;
    }

    public String getOtch() {
        return Otch;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setFam(String fam) {
        Fam = fam;
    }

    public void setImya(String imya) {
        Imya = imya;
    }

    public void setOtch(String otch) {
        Otch = otch;
    }
}
