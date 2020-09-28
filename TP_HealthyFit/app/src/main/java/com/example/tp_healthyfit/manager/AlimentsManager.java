package com.example.tp_healthyfit.manager;
import android.content.Context;
import android.database.Cursor;

import com.example.tp_healthyfit.entites.Aliments;
import com.example.tp_healthyfit.services.ConnexionBd;

import java.util.ArrayList;
public class AlimentsManager {

    private static String queryGetAll = "select * from aliments";
    public static ArrayList<Aliments> getAll(Context context) {
        ArrayList<Aliments> listAliments = new ArrayList<>();
        Cursor cursor = ConnexionBd.getBd(context).rawQuery(queryGetAll, null);
        while (cursor.moveToNext()) {
            int id = cursor.getInt(cursor.getColumnIndex("id"));
            String aliment = cursor.getString(cursor.getColumnIndex("aliment"));
            Aliments aliments = new Aliments(id, aliment);
            listAliments.add(aliments);
        }
        return listAliments;
    }

}
