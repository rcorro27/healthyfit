package com.example.tp_healthyfit;
public class Calculs {
    Double iMc, iMg, mB, resultatBesoinEnergetiqueQuoitidien, proteineJournaliere, tailleEnMetres, tailleCentimetres, poidsEnkilos;
    public double CalculIndiceMasseCorporel(double taille, double poids) {
        iMc = poids / (taille * taille) * 10000;
        return Math.round(iMc);
    }
    public double CalculIndiceMasseGrasse(double imc, int age, int genre) {
        double iMg = 0;
        // Formule : (1.20 * IMC) + (0.23 * âge) − (10.8 * sexe (femme = 0, homme =1) ) −5.4
        iMg = (1.20 * imc) + (0.23 * age) - (10.8 * genre) - 5.4;
        return Math.round(iMg);
    }
    public double CalculMetabolismeBase(int age, double taille, double poids, int genre) {
        // genre femme=0, homme=1
        double a;
        double b;
        double c;
        if (genre == 0) {
            a = 2.67 * age;
            b = 401.5 * taille;
            c = 8.6 * poids;
            mB = 247 - a + b + c;
        }
        if (genre == 1) {
            a = 3.8 * age;
            b = 456.4 * taille;
            c = 10.12 * poids;
            mB = 293 - a + b + c;
        }
        return Math.round(mB);
    }
    public double CalculBesoinEnergetiqueQuoitidien(double mB, double nbSeances, double dureeSeances, double intensiteSeances) {
        // intensite seances 1= faible 2=moyen 3=intense
        double resultat = (nbSeances * dureeSeances) / 7;//example 30
        if (resultat <= 30 && intensiteSeances == 1) {
            // utilisateur sedentaire
            resultatBesoinEnergetiqueQuoitidien = mB * 1.35;
        }
        if (resultat >= 30 && intensiteSeances == 2) {
            // utilisateur faiblement actif
            resultatBesoinEnergetiqueQuoitidien = mB * 1.35;
        }
        if (resultat >= 115 && intensiteSeances == 1) {
            // utilisateur faiblement actif
            resultatBesoinEnergetiqueQuoitidien = mB * 1.55;
        }
        if (resultat >= 60 && intensiteSeances == 2) {
            // utilisateur actif
            resultatBesoinEnergetiqueQuoitidien = mB * 1.75;
        }
        if (resultat >= 45 && intensiteSeances == 3) {
            // utilisateur actif
            resultatBesoinEnergetiqueQuoitidien = mB * 1.75;
        }
        if (resultat >= 190 && intensiteSeances == 3) {
            // utilisateur tres actif
            resultatBesoinEnergetiqueQuoitidien = mB * 1.95;
        }
        if (resultatBesoinEnergetiqueQuoitidien == null) {
            resultatBesoinEnergetiqueQuoitidien = 0.0;
        }
        return Math.round(resultatBesoinEnergetiqueQuoitidien);
    }
    public double CalculConsomationProteineJournalier(double poids, String typeSport) {
        if (typeSport.equalsIgnoreCase("Aucun (Sedentaire)")) {
            proteineJournaliere = poids * 0.8;
        }
        if (typeSport.equalsIgnoreCase("Sports esthetiques (gymnastique, danse, arts du critique...)")) {
            // calcul de 1.2 a 1.7 ca depende de certains cas
            proteineJournaliere = poids * 1.7;
        }
        if (typeSport.equalsIgnoreCase("Sports d'endurance (Velo, course, natation, longues randonnees...)")) {
            // calcul de 1.2 a 1.6 ca depende de certains cas
            proteineJournaliere = poids * 1.6;
        }
        if (typeSport.equalsIgnoreCase("Sports de puissance (halterophilie, boxe, sprints...)")) {
            // calcul de 1.6 a 1.8 ca depende de certains cas
            proteineJournaliere = poids * 1.8;
        }
        if (typeSport.equalsIgnoreCase("Periode d'entrainement pour la plupart des autres sports (maintien de la masse musculaire)")) {
            // calcul de 1.2 a 1.6 ca depende de certains cas
            proteineJournaliere = poids * 1.6;
        }
        if (typeSport.equalsIgnoreCase("Periode d'entrainement en vue d'un developpement de la masse musculaire")) {
            // calcul de 1.2 a 1.6 ca depende de certains cas
            proteineJournaliere = poids * 1.8;
        }
        if (proteineJournaliere == null){
            proteineJournaliere = 0.0;
        }

        return Math.round(proteineJournaliere);
    }
    public double ConversionPoucesAMetres(double TaillePouces) {
        // a changer il faut la taille en metres pas en cntimetres
        tailleEnMetres = (TaillePouces * 2.54) / 100;
        return Math.round(tailleEnMetres);
    }
    public double ConversionMetresCentimetres(double tailleMetres) {
        // necesaire pour le IMC
        tailleCentimetres = tailleMetres * 100;
        return Math.round(tailleCentimetres);
    }
    public double ConversionLivresAKilos(double poidsEnLivres) {
        poidsEnkilos = poidsEnLivres / 2.2;
        return Math.round(poidsEnkilos);
    }
}
