package com.example.tp_healthyfit;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.tp_healthyfit.entites.Mesures;
import com.example.tp_healthyfit.entites.Poids;
import com.example.tp_healthyfit.entites.Sports;
import com.example.tp_healthyfit.entites.User;
import com.example.tp_healthyfit.entites.UserSuivi;
import com.example.tp_healthyfit.manager.MesuresManager;
import com.example.tp_healthyfit.manager.PoidsManager;
import com.example.tp_healthyfit.manager.SportsManager;
import com.example.tp_healthyfit.manager.UserManager;
import com.example.tp_healthyfit.manager.UserSuiviManager;

import java.util.ArrayList;
public class PageAcceuil extends AppCompatActivity {
    String username_from_preference = "";
    String password_from_preference = "";
    int idSuivi = 1;
    SharedPreferences preferences;
    TextView titreIndiceMatiereGrasse, titreIndiceMasseCorporel, titreMetabolismeBase, titreBesoinEnergetiqueJournaliere, titreBesoinProteineJournalier;
    TextView textIndiceMatiereGrasse, textIndiceMasseCorporel, textMetabolismeBase, textBesoinEnergetiqueJournaliere, textBesoinProteineJournalier;
    Button voirMenu, ajouterSuivie;
    MenuNutritionelApi donwload;
    MetabolismeApiHttps IMC, IMG, MB, BEJ,RPJ;
    Context ctx;
    Calculs calculs;
    double imc, img, mb, bej,rpj;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page_acceuil);
        ctx = this;
        preferences = PreferenceManager.getDefaultSharedPreferences(ctx);
        titreIndiceMasseCorporel = findViewById(R.id.imcvalue);
        titreIndiceMatiereGrasse = findViewById(R.id.indicematieregrassevalue);
        titreMetabolismeBase = findViewById(R.id.metabolismebasevalue);
        titreBesoinEnergetiqueJournaliere = findViewById(R.id.besoinenergetiquejournaliervalue);
        titreBesoinProteineJournalier = findViewById(R.id.besoinproteinejournaliervalue);
        textIndiceMasseCorporel = findViewById(R.id.textimc);
        textIndiceMatiereGrasse = findViewById(R.id.textimg);
        textMetabolismeBase = findViewById(R.id.textmb);
        textBesoinEnergetiqueJournaliere = findViewById(R.id.textbesoinenergetiquejour);
        textBesoinProteineJournalier = findViewById(R.id.textbesoinproteinejour);
        voirMenu = findViewById(R.id.btnaccesmenu);
        ajouterSuivie = findViewById(R.id.btnajoutersuivie);
        String username_from_preference = preferences.getString("username", "");
        String password_from_preference = preferences.getString("password", "");

        User user = UserManager.getUserByUsernameAndPassword(ctx, username_from_preference, password_from_preference);
        int userId = user.getId();
        UserSuivi userSuivi = UserSuiviManager.getAllByUserId(ctx, userId);
        if(userSuivi != null) {
            idSuivi = userSuivi.getId();
        }

        ArrayList<Mesures> mesures = MesuresManager.getAllByUserSuiviId(ctx, idSuivi);
        ArrayList<Poids> mesurePoid = PoidsManager.getAllByUserSuiviId(ctx, idSuivi);

        User userInfo = UserManager.getUserByUsernameAndPassword(ctx, username_from_preference, password_from_preference);
        ArrayList<Sports> sports = SportsManager.getAllByUserSuiviId(ctx,idSuivi);
        double hauteur = 0;
        double poids = 0;
        int age = userInfo.getAge();
        int gender;
        double nbSeances = 0;
        double dureeDeSeances = 0;
        //INTENSITE A CHANGER SOIT 1 2 ou 3
        String intensite="";
        double intensiteFinal=0;
        for (Sports s: sports){
            nbSeances=s.getNb_seance();
            dureeDeSeances=s.getDuree_seance();
            intensite=s.getIntensite();
        }
        if (intensite.equalsIgnoreCase("LEGERE")){

            intensiteFinal=1;
        }if (intensite.equalsIgnoreCase("MOYEN")){
            intensiteFinal=2;
        }if (intensite.equalsIgnoreCase("INTENSE")){
            intensiteFinal=3;
        }

        String genderString = userInfo.getGender();
        if (genderString.equalsIgnoreCase("MASCULIN")) {
            gender = 1;
        } else {
            gender = 0;
        }
        for (Mesures m : mesures) {
            hauteur = m.getHauteur();
        }
        for (Poids p : mesurePoid) {
            poids = p.getPoids();
        }
        calculs = new Calculs();
        // poids 0 regler ca LA TABLE NE MARCHE PAS ??
        imc = calculs.CalculIndiceMasseCorporel(hauteur, poids);
        IMC = new MetabolismeApiHttps(textIndiceMasseCorporel);
        IMG = new MetabolismeApiHttps(textIndiceMatiereGrasse);
        MB = new MetabolismeApiHttps(textMetabolismeBase);
        BEJ= new MetabolismeApiHttps(textBesoinEnergetiqueJournaliere);
        RPJ= new MetabolismeApiHttps(textBesoinProteineJournalier);
        if (imc < 18.5) {
            titreIndiceMasseCorporel.setTextColor(Color.RED);
            titreIndiceMasseCorporel.setText(" votre imc est de " + imc + " insuffisance pondérale (maigreur)");
            //faire requette de object json pour le message de sante .
            IMC.execute("https://tpandroid.herokuapp.com/metabolismecontroleur?idCategorie=1");
        }
        if (imc >= 18.5 && imc <= 24.9) {
            titreIndiceMasseCorporel.setTextColor(Color.GREEN);
            titreIndiceMasseCorporel.setText(" votre imc est de " + imc + " poids normal");
            IMC.execute("https://tpandroid.herokuapp.com/metabolismecontroleur?idCategorie=2");
        }
        if (imc >= 25 && imc <= 29.9) {
            titreIndiceMasseCorporel.setTextColor(Color.rgb(255, 140, 0));
            titreIndiceMasseCorporel.setText(" votre imc est de " + imc + " surpoids");
            IMC.execute("https://tpandroid.herokuapp.com/metabolismecontroleur?idCategorie=3");
        }
        if (imc >= 30) {
            titreIndiceMasseCorporel.setTextColor(Color.RED);
            titreIndiceMasseCorporel.setText(" votre imc est de " + imc + " obésité");
            IMC.execute("https://tpandroid.herokuapp.com/metabolismecontroleur?idCategorie=4");
        }
        img = calculs.CalculIndiceMasseGrasse(imc, age, gender);
        titreIndiceMatiereGrasse.setTextColor(Color.BLACK);
        if (gender == 1) {
            titreIndiceMatiereGrasse.setText("Votre indice de matiere Grasse est de : " + img);
            IMG.execute("https://tpandroid.herokuapp.com/metabolismecontroleur?idCategorie=5");
        }
        if (gender == 0) {
            titreIndiceMatiereGrasse.setText("Votre indice de matiere Grasse est de : " + img);
            IMG.execute("https://tpandroid.herokuapp.com/metabolismecontroleur?idCategorie=6");
        }
        mb = calculs.CalculMetabolismeBase(age, hauteur, poids, gender);
        titreMetabolismeBase.setTextColor(Color.BLACK);
        titreMetabolismeBase.setText("Votre metabolisme de base est de : " + mb + " Calories");
        MB.execute("https://tpandroid.herokuapp.com/metabolismecontroleur?idCategorie=7");

        bej = calculs.CalculBesoinEnergetiqueQuoitidien(mb,nbSeances,dureeDeSeances,intensiteFinal);
        titreBesoinEnergetiqueJournaliere.setTextColor(Color.BLACK);
        titreBesoinEnergetiqueJournaliere.setText("Votre BEJ est de : "+ bej+" Calories");
        BEJ.execute("https://tpandroid.herokuapp.com/metabolismecontroleur?idCategorie=8");

        // TYPE DE SPORT A AJOUTER SELON LE SELECT DANS LA BD
        rpj= calculs.CalculConsomationProteineJournalier(poids,"aucun");
        titreBesoinProteineJournalier.setTextColor(Color.BLACK);
        titreBesoinProteineJournalier.setText("Votre BPJ est de : "+rpj+" grammes");
        RPJ.execute("https://tpandroid.herokuapp.com/metabolismecontroleur?idCategorie=9");

        voirMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(PageAcceuil.this,ActivityMenuNutritionel.class);
                intent.putExtra("bej",bej);
                startActivity(intent);
            }
        });
    }
}
