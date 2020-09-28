package com.example.tp_healthyfit.manager;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.tp_healthyfit.entites.Sports;
import com.example.tp_healthyfit.entites.UserSuivi;
import com.example.tp_healthyfit.services.ConnexionBd;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
public class SportsManager {
    //String queryGetAll = "select * from sports where id_suivi_fk ='" + idUserSuivi + "'";
    //si probleme ce PEUT ETRE LE ESPACE APRES LE SIGNE DE INTERROGATION
    private static String queryGetAll = "select * from sports where id_suivi_fk = ?";
    public static ArrayList<Sports> getAllByUserSuiviId(Context context, int idUserSuivi) {
        ArrayList<Sports> listSports = new ArrayList<>();
        String[] whereArgs = new String[]{"" + idUserSuivi};
        Cursor cursor = ConnexionBd.getBd(context).rawQuery(queryGetAll, whereArgs);
        while (cursor.moveToNext()) {
            int id = cursor.getInt(cursor.getColumnIndex("id"));
            String type = cursor.getString(cursor.getColumnIndex("type"));
            int nb_seance = cursor.getInt(cursor.getColumnIndex("nb_seance"));
            double duree_seance = cursor.getDouble(cursor.getColumnIndex("duree_seance"));
            String intensite = cursor.getString(cursor.getColumnIndex("intensite"));
            String date = cursor.getString(cursor.getColumnIndex("date"));
            int id_suivi_fk = cursor.getInt(cursor.getColumnIndex("id_suivi_fk"));
            Sports sports = new Sports(id, type, nb_seance, duree_seance, intensite, date, id_suivi_fk);
            listSports.add(sports);
        }
        return listSports;
    }
    public static boolean addSport(Context context, UserSuivi userSuivi, String type, int nb_seance, Double duree_seance, String intensite) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("type", type);
        contentValues.put("nb_seance", nb_seance);
        contentValues.put("duree_seance", duree_seance);
        contentValues.put("intensite", intensite);
        String date = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());
        contentValues.put("date", date);
        if(userSuivi == null){
            contentValues.put("id_suivi_fk", "1");
        }else {
            contentValues.put("id_suivi_fk", userSuivi.getId());
        }
        SQLiteDatabase bd = ConnexionBd.getBd(context);
        long id = bd.insert("sports", null, contentValues);
        bd.close();
        return id != -1;
    }
}
