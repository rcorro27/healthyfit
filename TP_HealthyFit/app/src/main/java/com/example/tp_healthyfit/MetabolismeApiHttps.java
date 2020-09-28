package com.example.tp_healthyfit;
import android.os.AsyncTask;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.example.tp_healthyfit.entitesapi.MetabolismeApi;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
public class MetabolismeApiHttps extends AsyncTask<String, Nullable, String> {
    TextView tv;
    String result = "";
    Gson gson;
    TextView img;
    public MetabolismeApiHttps(TextView tv) {
        this.tv = tv;
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
        MetabolismeApi[] metabolisme = gson.fromJson(result, MetabolismeApi[].class);
        String test = "";
        for (MetabolismeApi m : metabolisme) {
            test += m.getMessage();
        }
        tv.setText(test);
    }
}
