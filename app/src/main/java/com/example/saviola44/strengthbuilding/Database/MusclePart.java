package com.example.saviola44.strengthbuilding.Database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;

import com.example.saviola44.strengthbuilding.Database.StrengthBuilderOpenHelper;
import com.example.saviola44.strengthbuilding.Database.Tables.MuscleParts;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by saviola44 on 21.05.16.
 */
public class MusclePart  {
    public static final String INSERT = "insert into " + MuscleParts.TABLE_NAME
            + " (" + MuscleParts.Id + ", " + MuscleParts.musclePart + ")"  + " values (?, ?)";
    private SQLiteDatabase db;
    private SQLiteStatement insertStatement;

    public MusclePart(Context context){
        SQLiteOpenHelper openHelper = new StrengthBuilderOpenHelper(context);
        db = openHelper.getWritableDatabase();
        insertStatement = db.compileStatement(INSERT);
    }

    public long save(long id, String element) {
        insertStatement.clearBindings();
        insertStatement.bindLong(1, id);
        insertStatement.bindString(2, element);
        return insertStatement.executeInsert();
    }


}
