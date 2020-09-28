package com.example.tp_healthyfit;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.tp_healthyfit.manager.UserManager;
public class Inscription extends AppCompatActivity {
    Button connexion;
    Button soumettre;
    Context ctx;
    EditText prenom;
    EditText nom;
    EditText age;
    EditText email;
    Spinner genderSpinner;
    EditText pseudo;
    EditText password;
    SharedPreferences preferences;
    SharedPreferences.Editor editor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ctx = this;
        setContentView(R.layout.activity_inscription);
        connexion = findViewById(R.id.connexion);
        soumettre = findViewById(R.id.soumettre);
        age = findViewById(R.id.age);
        prenom = findViewById(R.id.prenom);
        nom = findViewById(R.id.nom);
        email = findViewById(R.id.email);
        pseudo = findViewById(R.id.pseudo);
        password = findViewById(R.id.password);
        genderSpinner = findViewById(R.id.genderSpinner);
        String[] items = new String[]{"Homme", "Femme"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
        genderSpinner.setAdapter(adapter);




        connexion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ctx,  MainActivity.class);
                startActivity(intent);
            }
        });

        preferences = PreferenceManager.getDefaultSharedPreferences(ctx);
        editor = preferences.edit();

        soumettre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String prenomText = prenom.getText().toString();
                String nomText = nom.getText().toString();
                String ageText = age.getText().toString();
                String emailText = email.getText().toString();
                String genderText = genderSpinner.getSelectedItem().toString();
                String pseudoText = pseudo.getText().toString();
                String passwordText = password.getText().toString();
                boolean addSuccess = UserManager.addUser(ctx, prenomText, nomText, Integer.parseInt(ageText), emailText, genderText, pseudoText,passwordText);

                if(addSuccess){

                    editor.putString("username", pseudoText);
                    editor.putString("password", passwordText);
                    editor.commit();

                    Intent intent = new Intent(ctx, Info_de_base.class);
                    Toast.makeText(ctx, "Etape 2", Toast.LENGTH_LONG).show();
                    startActivity(intent);
                }


            }
        });



    }
}
