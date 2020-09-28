package com.example.tp_healthyfit.manager;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.tp_healthyfit.entites.Metabolisme;
import com.example.tp_healthyfit.entites.Notes;
import com.example.tp_healthyfit.entites.User;
import com.example.tp_healthyfit.entites.UserSuivi;
import com.example.tp_healthyfit.services.ConnexionBd;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
public class MetabolismeManager {
    //String queryGetAll = "select * from metabolisme where id_user_fk ='"+idUser+"'";
    private static String queryGetAll = "select * from metabolisme where id_user_fk = ?";
    public static ArrayList<Metabolisme> getAllByIdUser(Context context, int idUser) {
        ArrayList<Metabolisme> listMetabolisme = new ArrayList<>();
        String[] whereArgs = new String[]{"" + idUser};
        Cursor cursor = ConnexionBd.getBd(context).rawQuery(queryGetAll, whereArgs);
        while (cursor.moveToNext()) {
            int id = cursor.getInt(cursor.getColumnIndex("id"));
            int id_user_fk = cursor.getInt(cursor.getColumnIndex("id_user_fk"));
            int imc = cursor.getInt(cursor.getColumnIndex("IMC"));
            int img = cursor.getInt(cursor.getColumnIndex("IMG"));
            int mb = cursor.getInt(cursor.getColumnIndex("MB"));
            int rp = cursor.getInt(cursor.getColumnIndex("RP"));
            int extra = cursor.getInt(cursor.getColumnIndex("extra"));
            Metabolisme metabolisme = new Metabolisme(id, id_user_fk, imc, img, mb, rp, extra);
            listMetabolisme.add(metabolisme);
        }
        return listMetabolisme;
    }
    public static boolean addMetabolisme(Context context, User user, int imc, int img, int mb, int rp, int extra) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("id_user_fk", user.getId());
        contentValues.put("imc", imc);
        contentValues.put("img", img);
        contentValues.put("mb", mb);
        contentValues.put("rp", rp);
        SQLiteDatabase bd = ConnexionBd.getBd(context);
        long id = bd.insert("metabolisme", null, contentValues);
        bd.close();
        return id != -1;
    }
}
