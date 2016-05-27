package com.example.saviola44.strengthbuilding.Database.Tables;

import android.database.sqlite.SQLiteDatabase;

/**
 * Created by saviola44 on 27.05.16.
 */
public class HistoryTable {
    public static final String TABLE_NAME = "train_hist";
    public static final String date = "date"; //long primary key
    public static final String trainingName = "name"; //nazwa/etykieta treningu

    public static void onCreate(SQLiteDatabase db){
        StringBuilder sb = new StringBuilder();
        sb.append("CREATE TABLE " + TABLE_NAME + " (");
        sb.append(date + " INTEGER PRIMARY KEY, ");
        sb.append(trainingName+ " TEXT NOT NULL)");
        db.execSQL(sb.toString());
    }

    public static void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        MuscleParts.onCreate(db);
    }

}


