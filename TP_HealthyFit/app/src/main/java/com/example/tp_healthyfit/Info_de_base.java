package com.example.tp_healthyfit;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.tp_healthyfit.manager.MesuresManager;
import com.example.tp_healthyfit.manager.SportsManager;
public class Info_de_base extends AppCompatActivity {
    Spinner hauteur_spinner;
    Spinner poids_spinner;
    Spinner type_de_sport_Spinner;
    Spinner intensite_Spinner;
    EditText hauteur_ed;
    EditText poids_ed;
    Context ctx;
    EditText nbSeance;
    EditText duree;
    Button start_healthy_button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ctx = this;
        setContentView(R.layout.activity_info_de_base);
        nbSeance = findViewById(R.id.nbSeance);
        hauteur_ed = findViewById(R.id.hauteur_ed);
        hauteur_spinner = findViewById(R.id.hauteur_spinner);
        duree = findViewById(R.id.duree);
        start_healthy_button = findViewById(R.id.start_healthy_button);
        poids_ed = findViewById(R.id.poids_ed);
        String[] items = new String[]{"M", "Cm", "Pouce"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
        hauteur_spinner.setAdapter(adapter);

        poids_spinner = findViewById(R.id.poids_spinner);
        String[] itemsPoids = new String[]{"Kg", "Lbs"};
        ArrayAdapter<String> adapterPoids = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, itemsPoids);
        poids_spinner.setAdapter(adapterPoids);

        type_de_sport_Spinner = findViewById(R.id.type_de_sport_Spinner);
        String[] itemsTypeSport = new String[]{"Aucun (Sedentaire)", "Sports esthetiques (gymnastique, danse, arts du critique...)", "Sports d'endurance (Velo, course, natation, longues randonnees...)", "Sports de puissance (halterophilie, boxe, sprints...)", "Periode d'entrainement pour la plupart des autres sports (maintien de la masse musculaire)", "Periode d'entrainement en vue d'un developpement de la masse musculaire"};
        ArrayAdapter<String> adapterTypeSport = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, itemsTypeSport);
        type_de_sport_Spinner.setAdapter(adapterTypeSport);

        intensite_Spinner = findViewById(R.id.intensite_Spinner);
        String[] itemsIntensite = new String[]{"Legere", "Moyen", "Intense"};
        ArrayAdapter<String> adapterIntensite = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, itemsIntensite);
        intensite_Spinner.setAdapter(adapterIntensite);

        start_healthy_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String unite_hauteur = hauteur_spinner.getSelectedItem().toString();
                String unite_poids = poids_spinner.getSelectedItem().toString();

                String typeSportValue = type_de_sport_Spinner.getSelectedItem().toString();
                String intensiteValue = intensite_Spinner.getSelectedItem().toString();
                String hauteur_value = hauteur_ed.getText().toString();
                String poid_value = poids_ed.getText().toString();
                String nbSeance_value = nbSeance.getText().toString();
                String duree_value = duree.getText().toString();

                //conversion de hauteur
                //comme c'est la premiere fois, il n'ya encore acun suivi
                //donc Suivi vaut null

                boolean mesureAddSuccess = MesuresManager.addMesures(ctx, null, Double.parseDouble(hauteur_value), 0, 0, 0, 0, 0);
                boolean sportAddSuccess = SportsManager.addSport(ctx, null, typeSportValue, Integer.parseInt(nbSeance_value), Double.parseDouble(duree_value),intensiteValue);

                if(mesureAddSuccess == true && sportAddSuccess == true ) {
                    Intent intent = new Intent(ctx, PageAcceuil.class);
                    Toast.makeText(ctx, "Bienvenue sur Healthy", Toast.LENGTH_LONG).show();
                    startActivity(intent);
                }

            }
        });





    }
}
