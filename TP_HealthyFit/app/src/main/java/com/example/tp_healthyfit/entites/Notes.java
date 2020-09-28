package com.example.tp_healthyfit.entites;
public class Notes {

    private int id;
    private String note;
    private int id_suivi_fk;
    private String date;

    public Notes(int id, String note, int id_suivi_fk, String date) {
        this.id = id;
        this.note = note;
        this.id_suivi_fk = id_suivi_fk;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public String getNote() {
        return note;
    }

    public int getId_suivi_fk() {
        return id_suivi_fk;
    }

    public String getDate() {
        return date;
    }
}
