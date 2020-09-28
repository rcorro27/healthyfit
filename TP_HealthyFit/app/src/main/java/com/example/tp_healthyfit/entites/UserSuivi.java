package com.example.tp_healthyfit.entites;
public class UserSuivi {

    private int id;
    private int id_user_fk;
    private String date;

    public UserSuivi(int id, int id_user_fk, String date) {
        this.id = id;
        this.id_user_fk = id_user_fk;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public int getId_user_fk() {
        return id_user_fk;
    }

    public String getDate() {
        return date;
    }
}
