package com.example.tp_healthyfit.entites;
public class Photos {

    private int id;
    private String photo1;
    private String photo2;
    private String photo3;
    private String date;
    private int id_suivi_fk;

    public Photos(int id, String photo1, String photo2, String photo3, String date, int id_suivi_fk) {
        this.id = id;
        this.photo1 = photo1;
        this.photo2 = photo2;
        this.photo3 = photo3;
        this.date = date;
        this.id_suivi_fk = id_suivi_fk;
    }

    public int getId() {
        return id;
    }

    public String getPhoto1() {
        return photo1;
    }

    public String getPhoto2() {
        return photo2;
    }

    public String getPhoto3() {
        return photo3;
    }

    public String getDate() {
        return date;
    }

    public int getId_suivi_fk() {
        return id_suivi_fk;
    }
}
