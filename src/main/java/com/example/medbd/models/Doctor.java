package com.example.medbd.models;

public class Doctor extends Person {
    String idSpec;
    String room;

    public Doctor(String id, String fam, String imya, String otch, String idSpec, String room) {
        super(id, fam, imya, otch);
        this.idSpec = idSpec;
        this.room = room;
    }

    public String getIdSpec() {
        return idSpec;
    }

    public void setIdSpec(String idSpec) {
        this.idSpec = idSpec;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }
}
