package com.example.tp_healthyfit.manager;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.tp_healthyfit.entites.Photos;
import com.example.tp_healthyfit.entites.Repas;
import com.example.tp_healthyfit.entites.UserSuivi;
import com.example.tp_healthyfit.services.ConnexionBd;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
public class RepasManager {
    //String queryGetAll = "select * from repas where id_suivi_fk ='"+idUserSuivi+"'";
    //si probleme ce PEUT ETRE LE ESPACE APRES LE SIGNE DE INTERROGATION
    private static String queryGetAll = "select * from repas where id_suivi_fk = ? ";
    public static ArrayList<Repas> getAllByUserSuiviId(Context context, int idUserSuivi) {
        ArrayList<Repas> listRepas = new ArrayList<>();
        String[] whereArgs = new String[]{"" + idUserSuivi};
        Cursor cursor = ConnexionBd.getBd(context).rawQuery(queryGetAll, whereArgs);
        while (cursor.moveToNext()) {
            int id = cursor.getInt(cursor.getColumnIndex("id"));
            String moment_journee = cursor.getString(cursor.getColumnIndex("moment_journee"));
            String date = cursor.getString(cursor.getColumnIndex("date"));
            int id_suivi_fk = cursor.getInt(cursor.getColumnIndex("id_suivi_fk"));
            Repas repas = new Repas(id, moment_journee, date, id_suivi_fk);
            listRepas.add(repas);
        }
        return listRepas;
    }
    public static boolean addRepas(Context context, UserSuivi userSuivi, String moment_journee) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("moment_journee", moment_journee);
        String date = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());
        contentValues.put("date", date);
        if(userSuivi == null){
            contentValues.put("id_suivi_fk", "1");
        }else {
            contentValues.put("id_suivi_fk", userSuivi.getId());
        }
        SQLiteDatabase bd = ConnexionBd.getBd(context);
        long id = bd.insert("repas", null, contentValues);
        bd.close();
        return id != -1;
    }
}
