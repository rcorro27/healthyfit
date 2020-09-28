package com.example.tp_healthyfit;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.tp_healthyfit.entites.Aliments;
import com.example.tp_healthyfit.entites.Mesures;
import com.example.tp_healthyfit.entites.User;
import com.example.tp_healthyfit.manager.UserManager;
import com.example.tp_healthyfit.services.ConnexionBd;
public class MainActivity extends AppCompatActivity {
    Button inscription;
    Context ctx;
    EditText username_ed;
    EditText password_ed;
    Button submit_button;
    SharedPreferences preferences;
    SharedPreferences.Editor editor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //ConnexionBd.copyBdFromAssets(this);
        ctx = this;
        setContentView(R.layout.activity_main);
        inscription = findViewById(R.id.inscription);
        username_ed = findViewById(R.id.username_ed);
        password_ed = findViewById(R.id.password_ed);
        submit_button = findViewById(R.id.submit_button);
        inscription.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ctx, Inscription.class);
                startActivity(intent);
            }
        });

        preferences = PreferenceManager.getDefaultSharedPreferences(ctx);
        editor = preferences.edit();

        submit_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username_value = username_ed.getText().toString();
                String password_value = password_ed.getText().toString();
                User user = UserManager.getUserByUsernameAndPassword(ctx, username_value, password_value);

                if(user != null){

                    editor.putString("username", username_value);
                    editor.putString("password", password_value);
                    editor.commit();

                    Intent intent = new Intent(ctx, PageAcceuil.class);
                    Toast.makeText(ctx, "Vous etes connecte", Toast.LENGTH_LONG).show();
                    startActivity(intent);
                }else{
                    Toast.makeText(ctx, "Veuillez vous inscrire d'abord", Toast.LENGTH_LONG).show();
                }
            }
        });



    }



}
