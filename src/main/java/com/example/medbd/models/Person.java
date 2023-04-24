package com.example.medbd.models;

public abstract class Person {
    String id;
    String Fam;
    String Imya;
    String Otch;

    public Person(String id, String fam, String imya, String otch) {
        this.id = id;
        Fam = fam;
        Imya = imya;
        Otch = otch != null ? otch : "";;
    }

    public String getId() {
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

    public void setId(String id) {
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
