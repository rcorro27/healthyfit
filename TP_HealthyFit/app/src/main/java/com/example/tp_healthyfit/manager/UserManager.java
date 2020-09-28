package com.example.tp_healthyfit.manager;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.tp_healthyfit.entites.User;
import com.example.tp_healthyfit.services.ConnexionBd;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
public class UserManager {
    private static String queryGetAll = "select * from user where username = ? and pwd = ?";
    public static User getUserByUsernameAndPassword(Context context, String username_param, String password) {
        User user = null;
        String[] whereArgs = new String[]{"" + username_param, "" + password};
        Cursor cursor = ConnexionBd.getBd(context).rawQuery(queryGetAll, whereArgs);
        while (cursor.moveToNext()) {
            int id = cursor.getInt(cursor.getColumnIndex("id"));
            String prenom = cursor.getString(cursor.getColumnIndex("prenom"));
            String nom = cursor.getString(cursor.getColumnIndex("nom"));
            int age = cursor.getInt(cursor.getColumnIndex("age"));
            String email_from_db = cursor.getString(cursor.getColumnIndex("email"));
            String gender = cursor.getString(cursor.getColumnIndex("gender"));
            String user_name = cursor.getString(cursor.getColumnIndex("username"));
            String pwd = cursor.getString(cursor.getColumnIndex("pwd"));
            String date = cursor.getString(cursor.getColumnIndex("date"));
            user = new User(id, prenom, nom, age, email_from_db, gender, user_name, pwd, date);
        }
        return user;
    }
    public static boolean addUser(Context context, String prenom, String nom, int age, String email, String gender, String user_name, String pwd) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("prenom", prenom);
        contentValues.put("nom", nom);
        contentValues.put("age", age);
        contentValues.put("email", email);
        contentValues.put("gender", gender);
        contentValues.put("username", user_name);
        contentValues.put("pwd", pwd);
        String date = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());
        contentValues.put("date", date);
        SQLiteDatabase bd = ConnexionBd.getBd(context);
        long id = bd.insert("user", null, contentValues);
        bd.close();
        return id != -1;
    }
}
