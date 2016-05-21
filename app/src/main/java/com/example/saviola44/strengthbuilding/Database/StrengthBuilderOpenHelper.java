package com.example.saviola44.strengthbuilding.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.saviola44.strengthbuilding.Constants;
import com.example.saviola44.strengthbuilding.Database.Tables.ExercisesTable;
import com.example.saviola44.strengthbuilding.Database.Tables.MuscleParts;

/**
 * Created by saviola44 on 21.05.16.
 */
public class StrengthBuilderOpenHelper extends SQLiteOpenHelper {

    public StrengthBuilderOpenHelper(Context context) {
        super(context, Constants.DATABASE_NAME, null, Constants.DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        MuscleParts.onCreate(db);
        ExercisesTable.onCreate(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        MuscleParts.onUpgrade(db, oldVersion, newVersion);
        ExercisesTable.onUpgrade(db, oldVersion, newVersion);
    }
}
