package com.example.tp_healthyfit.entitesapi;
public class MetabolismeApi {
    int idCategorie;
    String titre;
    String categorie;
    String message;

    public MetabolismeApi() {
    }

    public MetabolismeApi(String titre, String categorie, String message) {
        this.titre = titre;
        this.categorie = categorie;
        this.message = message;
    }

    public MetabolismeApi(int idCategorie, String titre, String categorie, String message) {
        this.idCategorie = idCategorie;
        this.titre = titre;
        this.categorie = categorie;
        this.message = message;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getIdCategorie() {
        return idCategorie;
    }

    public void setIdCategorie(int idCategorie) {
        this.idCategorie = idCategorie;
    }

}
