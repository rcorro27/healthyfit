package com.example.tp_healthyfit.manager;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.tp_healthyfit.entites.Photos;
import com.example.tp_healthyfit.entites.UserSuivi;
import com.example.tp_healthyfit.services.ConnexionBd;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
public class PhotosManager {
    // String queryGetAll = "select * from photos where id_suivi_fk ='"+idUserSuivi+"'";
    // si probleme ce PEUT ETRE LE ESPACE APRES LE SIGNE DE INTERROGATION
    private static String queryGetAll = "select * from photos where id_suivi_fk = ? ";
    public static ArrayList<Photos> getAllByUserSuiviId(Context context, int idUserSuivi) {
        String[] whereArgs = new String[]{"" + idUserSuivi};
        ArrayList<Photos> listPhotos = new ArrayList<>();
        Cursor cursor = ConnexionBd.getBd(context).rawQuery(queryGetAll, whereArgs);
        while (cursor.moveToNext()) {
            int id = cursor.getInt(cursor.getColumnIndex("id"));
            String photo1 = cursor.getString(cursor.getColumnIndex("photo1"));
            String photo2 = cursor.getString(cursor.getColumnIndex("photo2"));
            String photo3 = cursor.getString(cursor.getColumnIndex("photo3"));
            String date = cursor.getString(cursor.getColumnIndex("date"));
            int id_suivi_fk = cursor.getInt(cursor.getColumnIndex("id_suivi_fk"));
            Photos photos = new Photos(id, photo1, photo2, photo3, date, id_suivi_fk);
            listPhotos.add(photos);
        }
        return listPhotos;
    }
    public static boolean addPhotos(Context context, UserSuivi userSuivi, String photo1, String photo2, String photo3) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("photo1", photo1);
        contentValues.put("photo2", photo2);
        contentValues.put("photo3", photo3);
        String date = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());
        contentValues.put("date", date);
        if(userSuivi == null){
            contentValues.put("id_suivi_fk", "1");
        }else {
            contentValues.put("id_suivi_fk", userSuivi.getId());
        }
        SQLiteDatabase bd = ConnexionBd.getBd(context);
        long id = bd.insert("photos", null, contentValues);
        bd.close();
        return id != -1;
    }
}
