package com.example.tp_healthyfit.manager;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.tp_healthyfit.entites.Notes;
import com.example.tp_healthyfit.entites.User;
import com.example.tp_healthyfit.entites.UserSuivi;
import com.example.tp_healthyfit.services.ConnexionBd;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
public class NotesManager {
    //String queryGetAll = "select * from notes where id_suivi_fk ='"+idUserSuivi+"'";
    // si probleme ce PEUT ETRE LE ESPACE APRES LE SIGNE DE INTERROGATION
    private static String queryGetAll = "select * from notes where id_suivi_fk = ? ";
    public static ArrayList<Notes> getAllByIdUserSuivi(Context context, int idUserSuivi) {
        ArrayList<Notes> listNotes = new ArrayList<>();
        String[] whereArgs = new String[]{"" + idUserSuivi};
        Cursor cursor = ConnexionBd.getBd(context).rawQuery(queryGetAll, whereArgs);
        while (cursor.moveToNext()) {
            int id = cursor.getInt(cursor.getColumnIndex("id"));
            String note = cursor.getString(cursor.getColumnIndex("note"));
            int id_suivi_fk = cursor.getInt(cursor.getColumnIndex("id_suivi_fk"));
            String date = cursor.getString(cursor.getColumnIndex("date"));
            Notes notes = new Notes(id, note, id_suivi_fk, date);
            listNotes.add(notes);
        }
        return listNotes;
    }
    public static boolean addNote(Context context, UserSuivi userSuivi, String note) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("note", note);
        if(userSuivi == null){
            contentValues.put("id_suivi_fk", "1");
        }else {
            contentValues.put("id_suivi_fk", userSuivi.getId());
        }
        String date = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());
        contentValues.put("date", date);
        SQLiteDatabase bd = ConnexionBd.getBd(context);
        long id = bd.insert("user_suivi", null, contentValues);
        bd.close();
        return id != -1;
    }
}
