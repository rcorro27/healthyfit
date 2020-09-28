package com.example.tp_healthyfit.manager;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.tp_healthyfit.entites.User;
import com.example.tp_healthyfit.entites.UserSuivi;
import com.example.tp_healthyfit.services.ConnexionBd;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
public class UserSuiviManager {
    private static String queryGetAll = "select * from user_suivi where id = ?";
    public static UserSuivi getAllByUserId(Context context, int userId) {
        UserSuivi userSuivi = null;
        String[] whereArgs = new String[]{"" + userId};
        Cursor cursor = ConnexionBd.getBd(context).rawQuery(queryGetAll, whereArgs);
        while (cursor.moveToNext()) {
            int id = cursor.getInt(cursor.getColumnIndex("id"));
            int id_user_fk = cursor.getInt(cursor.getColumnIndex("id_user_fk"));
            String date = cursor.getString(cursor.getColumnIndex("date"));
            userSuivi = new UserSuivi(id, id_user_fk, date);
        }
        return userSuivi;
    }

    public static boolean addUserSuivi(Context context, User user) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("id_user_fk", user.getId());
        String date = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());
        contentValues.put("date", date);
        SQLiteDatabase bd = ConnexionBd.getBd(context);
        long id = bd.insert("user_suivi", null, contentValues);
        bd.close();
        return id != -1;
    }

}
