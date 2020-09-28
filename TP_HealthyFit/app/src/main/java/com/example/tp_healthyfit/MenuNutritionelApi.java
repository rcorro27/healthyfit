package com.example.tp_healthyfit;
import android.os.AsyncTask;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.example.tp_healthyfit.entitesapi.MenuNutritionel;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
public class MenuNutritionelApi extends AsyncTask<String, Nullable, String> {
    TextView textpourcentages,textPetitDej, textCollationMatin, textDejeuner, textCollatonMidi, textDiner;
    String result = "";
    Gson gson;
    TextView img;
    public MenuNutritionelApi(TextView textpourcentages, TextView textPetitDej, TextView textCollationMatin, TextView textDejeuner, TextView textCollatonMidi, TextView textDiner) {
        this.textpourcentages = textpourcentages;
        this.textPetitDej = textPetitDej;
        this.textCollationMatin = textCollationMatin;
        this.textDejeuner = textDejeuner;
        this.textCollatonMidi = textCollatonMidi;
        this.textDiner = textDiner;
    }
    // ... reupere un tableau
    protected String doInBackground(String... urls) {
        try {
            URL url = new URL(urls[0]);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoOutput(false);
            connection.setDoInput(true);
            connection.connect();
//            PrintWriter out = connection.getOutputStream();
//            out.println("idCategorie=2");
            int codeRetour = connection.getResponseCode();
            if (codeRetour == HttpURLConnection.HTTP_OK) {
//                Toast.makeText(ctx, "YouYu", Toast.LENGTH_SHORT).show();
                String line;
                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                while ((line = in.readLine()) != null)
                    result += line;
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }
    protected void onPostExecute(String result) {
        gson = new Gson();
        MenuNutritionel[] menus = gson.fromJson(result, MenuNutritionel[].class);
        String test = "";
        for (MenuNutritionel m : menus) {
           textpourcentages.setText(m.getPourcentage());
            textPetitDej.setText(m.getPetitDejeuner());
            textCollationMatin.setText(m.getCollationMatin());
            textDejeuner.setText(m.getDejeuner());
            textCollatonMidi.setText(m.getCollationApresMidi());
            textDiner.setText(m.getDiner());
        }
    }
    /*private void showDialog(String s) {

    }*/
}
