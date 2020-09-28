package com.example.tp_healthyfit.entites;
public class Poids {

    private int id_suivi_fk;
    private double poids;

    public Poids(int id_suivi_fk, double poids) {
        this.id_suivi_fk = id_suivi_fk;
        this.poids = poids;
    }

    public int getId_suivi_fk() {
        return id_suivi_fk;
    }

    public double getPoids() {
        return poids;
    }
}
