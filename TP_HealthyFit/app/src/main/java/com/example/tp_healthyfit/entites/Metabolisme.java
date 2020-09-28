package com.example.tp_healthyfit.entites;
public class Metabolisme {

    private int id;
    private int id_user_fk;
    private int imc;
    private int img;
    private int mb;
    private int rp;
    private int extra;

    public Metabolisme(int id, int id_user_fk, int imc, int img, int mb, int rp, int extra) {
        this.id = id;
        this.id_user_fk = id_user_fk;
        this.imc = imc;
        this.img = img;
        this.mb = mb;
        this.rp = rp;
        this.extra = extra;
    }

    public int getId() {
        return id;
    }

    public int getId_user_fk() {
        return id_user_fk;
    }

    public int getImc() {
        return imc;
    }

    public int getImg() {
        return img;
    }

    public int getMb() {
        return mb;
    }

    public int getRp() {
        return rp;
    }

    public int getExtra() {
        return extra;
    }

}
