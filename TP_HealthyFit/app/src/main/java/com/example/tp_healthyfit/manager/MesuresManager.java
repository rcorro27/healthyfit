package com.example.tp_healthyfit.manager;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.tp_healthyfit.entites.Mesures;
import com.example.tp_healthyfit.entites.UserSuivi;
import com.example.tp_healthyfit.services.ConnexionBd;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
public class MesuresManager {
    //String queryGetAll = "select * from mesures where id_suivi_fk = '"+idUserSuivi+"'";
    // si probleme ce PEUT ETRE LE ESPACE APRES LE SIGNE DE INTERROGATION
    private static String queryGetAll = "select * from mesures where  id_suivi_fk = ?";
    public static ArrayList<Mesures> getAllByUserSuiviId(Context context, int idUserSuivi) {
        ArrayList<Mesures> listMesures = new ArrayList<>();
        String[] whereArgs = new String[]{"" + idUserSuivi};
        Cursor cursor = ConnexionBd.getBd(context).rawQuery(queryGetAll, whereArgs);
        while (cursor.moveToNext()) {
            int id = cursor.getInt(cursor.getColumnIndex("id"));
            double hauteur = cursor.getDouble(cursor.getColumnIndex("hauteur"));
            double epaules = cursor.getDouble(cursor.getColumnIndex("epaules"));
            double cuisse = cursor.getDouble(cursor.getColumnIndex("cuisse"));
            double pectoraux = cursor.getDouble(cursor.getColumnIndex("pectoraux"));
            double taille = cursor.getDouble(cursor.getColumnIndex("taille"));
            double biceps = cursor.getDouble(cursor.getColumnIndex("biceps"));
            String date = cursor.getString(cursor.getColumnIndex("date"));
            int id_suivi_fk = cursor.getInt(cursor.getColumnIndex("id_suivi_fk"));
            Mesures mesures = new Mesures(id, hauteur, epaules, cuisse, pectoraux, taille, biceps, date, id_suivi_fk);
            listMesures.add(mesures);
        }
        return listMesures;
    }
    public static boolean addMesures(Context context, UserSuivi userSuivi, double hauteur, double epaules, double cuisse, double pectoraux, double taille, double biceps) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("hauteur", hauteur);
        contentValues.put("epaules", epaules);
        contentValues.put("cuisse", cuisse);
        contentValues.put("pectoraux", pectoraux);
        contentValues.put("taille", taille);
        contentValues.put("biceps", biceps);
        String date = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());
        contentValues.put("date", date);
        // new code for suivi
        if(userSuivi == null){
            contentValues.put("id_suivi_fk", "1");
        }else {
            contentValues.put("id_suivi_fk", userSuivi.getId());
        }
        SQLiteDatabase bd = ConnexionBd.getBd(context);
        long id = bd.insert("mesures", null, contentValues);
        bd.close();
        return id != -1;
    }
}
