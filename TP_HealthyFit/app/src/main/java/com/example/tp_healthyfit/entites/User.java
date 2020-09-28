package com.example.tp_healthyfit.entites;
public class User {

    private int id;
    private String prenom;
    private String nom;
    private int age;
    private String email;
    private String gender;
    private String user_name;
    private String pwd;
    private String date;
    public User() {
    }
    public User(int id, String prenom, String nom, int age, String email, String gender, String user_name, String pwd, String date){
        this.id = id;
        this.prenom = prenom;
        this.nom = nom;
        this.age = age;
        this.email = email;
        this.gender = gender;
        this.user_name = user_name;
        this.pwd = pwd;
        this.date = date;
    }

    public int getId(){
        return this.id;
    }

    public String getPrenom(){
        return this.prenom;
    }

    public String getNom(){
        return this.nom;
    }

    public int getAge(){
        return this.age;
    }

    public String getEmail(){
        return this.email;
    }

    public String getGender(){
        return this.gender;
    }

    public String getUser_name(){
        return this.user_name;
    }

    public String getPwd(){
        return this.pwd;
    }

    public String getDate(){
        return this.date;
    }


}
