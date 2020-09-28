package com.example.tp_healthyfit;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
public class ActivityMenuNutritionel extends AppCompatActivity {
    Context ctx;
    MenuNutritionelApi menu;
    TextView textPourcentages,textPetitDej, textCollationMatin, textDejeuner, textCollatonMidi, textDiner;
    Intent retour;
    double bej;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_nutritionel);
        textPourcentages=findViewById(R.id.pourcentages);
        textPetitDej = findViewById(R.id.petitdejeuner);
        textCollationMatin = findViewById(R.id.collationmatin);
        textDejeuner = findViewById(R.id.dejeuner);
        textCollatonMidi = findViewById(R.id.collationapresmidi);
        textDiner = findViewById(R.id.diner);
        retour = getIntent();
        menu = new MenuNutritionelApi(textPourcentages,textPetitDej, textCollationMatin, textDejeuner, textCollatonMidi, textDiner);
        bej = retour.getDoubleExtra("bej", 1);
        if (bej >= 1507 && bej < 2842) {
            menu.execute("https://tpandroid.herokuapp.com/menucontroleur?idCategorie=1");
            // 2842categorie2
            //2854 categorie3
            //1507 categorie1
            //4039 categorie4
        }
        else if (bej >= 2842 && bej < 2854) {
            menu.execute("https://tpandroid.herokuapp.com/menucontroleur?idCategorie=2");
        }
        else if (bej >= 2854 && bej < 4039) {
            menu.execute("https://tpandroid.herokuapp.com/menucontroleur?idCategorie=3");
        }
        else if (bej >= 4039) {
            menu.execute("https://tpandroid.herokuapp.com/menucontroleur?idCategorie=4");
        }else{
            menu.execute("https://tpandroid.herokuapp.com/menucontroleur?idCategorie=4");
        }
    }
}
