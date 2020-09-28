package com.example.tp_healthyfit.entites;
public class Sports {

    private int id;
    private String type;
    private int nb_seance;
    private double duree_seance;
    private String intensite;
    private String date;
    private int id_suivi_fk;

    public Sports(int id, String type, int nb_seance, double duree_seance, String intensite, String date, int id_suivi_fk) {
        this.id = id;
        this.type = type;
        this.nb_seance = nb_seance;
        this.duree_seance = duree_seance;
        this.intensite = intensite;
        this.date = date;
        this.id_suivi_fk = id_suivi_fk;
    }

    public int getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public int getNb_seance() {
        return nb_seance;
    }

    public double getDuree_seance() {
        return duree_seance;
    }

    public String getIntensite() {
        return intensite;
    }

    public String getDate() {
        return date;
    }

    public int getId_suivi_fk() {
        return id_suivi_fk;
    }
}
