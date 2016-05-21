package com.example.saviola44.strengthbuilding.Database.Tables;

import android.database.sqlite.SQLiteDatabase;


/**
 * Created by saviola44 on 21.05.16.
 */
public class ExercisesTable {
    public static final String TABLE_NAME = "exercises";

    public static final String Id = "id";
    public static final String exerciseName = "exercise_name";
    public static final String muscleWorked = "muscle_worked";
    public static final String isCompound = "is_compound";

    public static void onCreate(SQLiteDatabase db){
        StringBuilder sb = new StringBuilder();
        sb.append("CREATE TABLE " + TABLE_NAME + " (");
        sb.append(Id + " INTEGER PRIMARY KEY AUTOINCREMENT, ");
        sb.append(exerciseName+ " TEXT NOT NULL, ");
        sb.append(muscleWorked+ " INTEGER NOT NULL, ");
        sb.append(isCompound + " INTEGER NOT NULL,");
        sb.append(" FOREIGN KEY (" + muscleWorked + ") REFERENCES " + MuscleParts.TABLE_NAME);
        sb.append("(" + MuscleParts.Id + "))");
        db.execSQL(sb.toString());
    }

    public static void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        MuscleParts.onCreate(db);
    }
}
