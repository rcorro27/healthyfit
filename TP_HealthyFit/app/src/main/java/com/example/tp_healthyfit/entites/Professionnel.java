package com.example.tp_healthyfit.entites;
public class Professionnel {

    private int id;
    private String user_object;
    private int id_specialist;
    private String nome_specialist;
    private String expertise;
    private String date;
    private String notes;
    private int id_suivi_fk;

    public Professionnel(int id, String user_object, int id_specialist, String nome_specialist, String expertise, String date, String notes, int id_suivi_fk) {
        this.id = id;
        this.user_object = user_object;
        this.id_specialist = id_specialist;
        this.nome_specialist = nome_specialist;
        this.expertise = expertise;
        this.date = date;
        this.notes = notes;
        this.id_suivi_fk = id_suivi_fk;
    }

    public int getId() {
        return id;
    }

    public String getUser_object() {
        return user_object;
    }

    public int getId_specialist() {
        return id_specialist;
    }

    public String getNome_specialist() {
        return nome_specialist;
    }

    public String getExpertise() {
        return expertise;
    }

    public String getDate() {
        return date;
    }

    public String getNotes() {
        return notes;
    }

    public int getId_suivi_fk() {
        return id_suivi_fk;
    }
}
