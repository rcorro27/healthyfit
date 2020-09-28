package com.example.tp_healthyfit.entites;
public class Repas {

    private int id;
    private String moment_journee;
    private String date;
    private int id_suivi_fk;


    public Repas(int id, String moment_journee, String date, int id_suivi_fk) {
        this.id = id;
        this.moment_journee = moment_journee;
        this.date = date;
        this.id_suivi_fk = id_suivi_fk;
    }

    public int getId() {
        return id;
    }

    public String getMoment_journee() {
        return moment_journee;
    }

    public String getDate() {
        return date;
    }

    public int getId_suivi_fk(){
        return id_suivi_fk;
    }
}
