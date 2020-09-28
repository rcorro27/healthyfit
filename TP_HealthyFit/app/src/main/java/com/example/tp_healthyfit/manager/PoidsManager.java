package com.example.tp_healthyfit.manager;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.tp_healthyfit.entites.Poids;
import com.example.tp_healthyfit.entites.Repas;
import com.example.tp_healthyfit.entites.UserSuivi;
import com.example.tp_healthyfit.services.ConnexionBd;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
public class PoidsManager {
    //String queryGetAll = "select * from poids where id_suivi_fk ='"+idUserSuivi+"'";
    // si probleme ce PEUT ETRE LE ESPACE APRES LE SIGNE DE INTERROGATION
    private static String queryGetAll = "select * from poids where id_suivi_fk = ? ";
    public static ArrayList<Poids> getAllByUserSuiviId(Context context, int idUserSuivi) {
        ArrayList<Poids> listPoids = new ArrayList<>();
        String[] whereArgs = new String[]{"" + idUserSuivi};
        Cursor cursor = ConnexionBd.getBd(context).rawQuery(queryGetAll, whereArgs);
        while (cursor.moveToNext()) {
            int id_suivi_fk = cursor.getInt(cursor.getColumnIndex("id_suivi_fk"));
            double poids_from_db = cursor.getDouble(cursor.getColumnIndex("poids"));
            Poids poids = new Poids(id_suivi_fk, poids_from_db);
            listPoids.add(poids);
        }
        return listPoids;
    }
    public static boolean addRepas(Context context, UserSuivi userSuivi, String moment_journee) {
        ContentValues contentValues = new ContentValues();
        if(userSuivi == null){
            contentValues.put("id_suivi_fk", "1");
        }else {
            contentValues.put("id_suivi_fk", userSuivi.getId());
        }
        contentValues.put("moment_journee", moment_journee);
        SQLiteDatabase bd = ConnexionBd.getBd(context);
        long id = bd.insert("poids", null, contentValues);
        bd.close();
        return id != -1;
    }
}
