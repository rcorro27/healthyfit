package com.example.tp_healthyfit.manager;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.tp_healthyfit.entites.Sports;
import com.example.tp_healthyfit.entites.TypeAliment;
import com.example.tp_healthyfit.entites.UserSuivi;
import com.example.tp_healthyfit.services.ConnexionBd;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
public class TypeAlimentManager {
    //String queryGetAll = "select * from type_aliment where id_repas_fk ='"+id_repas_fk+"'";
    // si probleme ce PEUT ETRE LE ESPACE APRES LE SIGNE DE INTERROGATION
    private static String queryGetAll = "select * from type_aliment where id_repas_fk = ? ";
    public static ArrayList<TypeAliment> getAllByIdRepas(Context context, int id_repas_fk) {
        ArrayList<TypeAliment> listTypeAliment = new ArrayList<>();
        String[] whereArgs = new String[]{"" + id_repas_fk};
        Cursor cursor = ConnexionBd.getBd(context).rawQuery(queryGetAll, whereArgs);
        while (cursor.moveToNext()) {
            int id = cursor.getInt(cursor.getColumnIndex("id"));
            int quantite = cursor.getInt(cursor.getColumnIndex("quantite"));
            int id_aliment_fk = cursor.getInt(cursor.getColumnIndex("id_aliment_fk"));
            int id_repas_fk_from_db = cursor.getInt(cursor.getColumnIndex("id_repas_fk"));
            TypeAliment typeAliment = new TypeAliment(id, quantite, id_aliment_fk, id_repas_fk_from_db);
            listTypeAliment.add(typeAliment);
        }
        return listTypeAliment;
    }
    public static boolean addType(Context context, String quantite, int id_aliment_fk, int id_repas_fk) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("quantite", quantite);
        contentValues.put("id_aliment_fk", id_aliment_fk);
        contentValues.put("id_repas_fk", id_repas_fk);
        SQLiteDatabase bd = ConnexionBd.getBd(context);
        long id = bd.insert("type_aliment", null, contentValues);
        bd.close();
        return id != -1;
    }
}
