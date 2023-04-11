package com.example.medbd.models;

public class User extends Person{
    String login;
    String password;
    String title;

    public User(String id, String fam, String imya, String otch, String login, String password, String title) {
        super(id, fam, imya, otch);
        this.login = login;
        this.password = password;
        this.title = title;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
