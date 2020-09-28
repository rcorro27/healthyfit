package com.example.tp_healthyfit.manager;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.tp_healthyfit.entites.Professionnel;
import com.example.tp_healthyfit.entites.UserSuivi;
import com.example.tp_healthyfit.services.ConnexionBd;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
public class ProfessionnelManager {
    //String queryGetAll = "select * from professionnel where id_suivi_fk ='"+idUserSuivi+"'";
    //// si probleme ce PEUT ETRE LE ESPACE APRES LE SIGNE DE INTERROGATION
    private static String queryGetAll = "select * from professionnel where id_suivi_fk = ? ";
    public static ArrayList<Professionnel> getAllByUserSuiviId(Context context, int idUserSuivi) {
        ArrayList<Professionnel> listProfessionnel = new ArrayList<>();
        String[] whereArgs = new String[]{"" + idUserSuivi};
        Cursor cursor = ConnexionBd.getBd(context).rawQuery(queryGetAll, whereArgs);
        while (cursor.moveToNext()) {
            int id = cursor.getInt(cursor.getColumnIndex("id"));
            String user_object = cursor.getString(cursor.getColumnIndex("user_objectif"));
            int id_specialist = cursor.getInt(cursor.getColumnIndex("id_specialist"));
            //USER ALERGIE ?? on le mets pas ?? (Richard)
            String nome_specialist = cursor.getString(cursor.getColumnIndex("nome_specialist"));
            String expertise = cursor.getString(cursor.getColumnIndex("expertise"));
            String date = cursor.getString(cursor.getColumnIndex("date"));
            String notes = cursor.getString(cursor.getColumnIndex("notes"));
            int id_suivi_fk = cursor.getInt(cursor.getColumnIndex("id_suivi_fk"));
            Professionnel professionnel = new Professionnel(id, user_object, id_specialist, nome_specialist, expertise, date, notes, id_suivi_fk);
            listProfessionnel.add(professionnel);
        }
        return listProfessionnel;
    }
    public static boolean addProfessionnalisme(Context context, UserSuivi userSuivi, String user_object, int id_specialist, String nome_specialist, String expertise, String notes) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("muser_object", user_object);
        contentValues.put("id_specialist", id_specialist);
        contentValues.put("nome_specialist", nome_specialist);
        contentValues.put("expertise", expertise);
        String date = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());
        contentValues.put("date", date);
        contentValues.put("notes", notes);
        if(userSuivi == null){
            contentValues.put("id_suivi_fk", "1");
        }else {
            contentValues.put("id_suivi_fk", userSuivi.getId());
        }
        SQLiteDatabase bd = ConnexionBd.getBd(context);
        long id = bd.insert("professionnel", null, contentValues);
        bd.close();
        return id != -1;
    }
}
