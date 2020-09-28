package com.example.tp_healthyfit.entites;
public class Mesures {

    private int id;
    private double hauteur;
    private double epaules;
    private double cuisse;
    private double pectoraux;
    private double taille;
    private double biceps;
    private String date;
    private int id_suivi_fk;

    public Mesures(int id, double hauteur, double epaules, double cuisse, double pectoraux, double taille, double biceps, String date, int id_suivi_fk) {
        this.id = id;
        this.hauteur = hauteur;
        this.epaules = epaules;
        this.cuisse = cuisse;
        this.pectoraux = pectoraux;
        this.taille = taille;
        this.biceps = biceps;
        this.date = date;
        this.id_suivi_fk = id_suivi_fk;
    }

    public int getId() {
        return id;
    }

    public double getHauteur() {
        return hauteur;
    }

    public double getEpaules() {
        return epaules;
    }

    public double getCuisse() {
        return cuisse;
    }

    public double getPectoraux() {
        return pectoraux;
    }

    public double getTaille() {
        return taille;
    }

    public double getBiceps() {
        return biceps;
    }

    public String getDate() {
        return date;
    }

    public int getId_suivi_fk() {
        return id_suivi_fk;
    }
}
