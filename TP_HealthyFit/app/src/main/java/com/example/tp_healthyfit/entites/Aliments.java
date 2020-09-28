package com.example.tp_healthyfit.entites;
public class Aliments {

    private int id;
    private String aliment;

    public Aliments(int id, String aliment) {
        this.id = id;
        this.aliment = aliment;
    }

    public int getId() {
        return id;
    }

    public String getAliment() {
        return aliment;
    }
}
