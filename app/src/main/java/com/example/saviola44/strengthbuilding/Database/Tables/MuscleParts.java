package com.example.saviola44.strengthbuilding.Database.Tables;

import android.database.sqlite.SQLiteDatabase;

/**
 * Created by saviola44 on 21.05.16.
 */
public class MuscleParts {
    public static final String TABLE_NAME = "muscle_parts";

    public static final String Id = "id";
    public static final String musclePart = "muscle_part";

    public static void onCreate(SQLiteDatabase db){
        StringBuilder sb = new StringBuilder();
        sb.append("CREATE TABLE " + TABLE_NAME + " (");
        sb.append(Id + " INTEGER PRIMARY KEY AUTOINCREMENT, ");
        sb.append(musclePart + " TEXT NOT NULL)");
        db.execSQL(sb.toString());
    }

    public static void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        MuscleParts.onCreate(db);
    }
}
