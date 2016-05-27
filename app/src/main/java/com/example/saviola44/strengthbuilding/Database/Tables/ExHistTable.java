package com.example.saviola44.strengthbuilding.Database.Tables;

import android.database.sqlite.SQLiteDatabase;

/**
 * Created by saviola44 on 27.05.16.
 */
public class ExHistTable {
    public static final String TABLE_NAME = "exercise_hist";

    public static final String Id = "id";
    public static final String date = "date"; //foreign kay
    public static final String exercise = "ex"; //foreign kay
    public static final String reps = "reps";
    public static final String weight = "weight";
    public static final String comment = "comment";
/*
    create table TABLE_NAME (
        Id INTEGER PRIMARY KEY AUTOINCREMENT,
        reps Integer not null,
        weight Real,
        comment TEXT,
        date Integer not null,
        exercise Integer not null,
        foreign key (date) references HistoryTable.TableName(date),
        foreign key (exercise) references ExercisesTable.TableName(Id))

 */
    public static void onCreate(SQLiteDatabase db){
        StringBuilder sb = new StringBuilder();
        sb.append("CREATE TABLE " + TABLE_NAME + " (");
        sb.append(Id + " INTEGER PRIMARY KEY AUTOINCREMENT, ");
        sb.append(reps + " INTEGER NOT NULL, ");
        sb.append(weight + " REAL, ");
        sb.append(comment + "TEXT, ");
        sb.append(date + " INTEGER NOT NULL, ");
        sb.append(exercise + " INTEGER NOT NULL, ");
        sb.append(" FOREIGN KEY (" + date + ") REFERENCES ");
        sb.append(HistoryTable.TABLE_NAME + "(" + date + "),");
        sb.append(" FOREIGN KEY (" + exercise + ") REFERENCES ");
        sb.append(ExercisesTable.TABLE_NAME + "(" + Id + "))");
        db.execSQL(sb.toString());
    }

    public static void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        MuscleParts.onCreate(db);
    }
}
