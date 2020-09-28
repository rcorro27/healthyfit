package com.example.tp_healthyfit.entites;
public class TypeAliment {

    private int id;
    private int quantite;
    private int id_aliment_fk;
    private int id_repas_fk;

    public TypeAliment(int id, int quantite, int id_aliment_fk, int id_repas_fk) {
        this.id = id;
        this.quantite = quantite;
        this.id_aliment_fk = id_aliment_fk;
        this.id_repas_fk = id_repas_fk;
    }

    public int getId() {
        return id;
    }

    public int getQuantite() {
        return quantite;
    }

    public int getId_aliment_fk() {
        return id_aliment_fk;
    }

    public int getId_repas_fk() {
        return id_repas_fk;
    }
}
