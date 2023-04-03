package com.example.medbd.models;

public class Doctor extends Person{
    int idSpec;
    int room;

    public Doctor(int id, String fam, String imya, String otch, int idSpec, int room) {
        super(id, fam, imya, otch);
        this.idSpec = idSpec;
        this.room = room;
    }

    public int getIdSpec() {
        return idSpec;
    }

    public void setIdSpec(int idSpec) {
        this.idSpec = idSpec;
    }

    public int getRoom() {
        return room;
    }

    public void setRoom(int room) {
        this.room = room;
    }
}
