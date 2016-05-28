package com.example.saviola44.strengthbuilding.Database.DAO;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;

import com.example.saviola44.strengthbuilding.Database.StrengthBuilderOpenHelper;
import com.example.saviola44.strengthbuilding.Database.Tables.ExercisesTable;
import com.example.saviola44.strengthbuilding.Model.ExerciseInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by saviola44 on 21.05.16.
 */
public class ExerciseDAO implements DAO<ExerciseInfo> {
    public static final String INSERT = "insert into " + ExercisesTable.TABLE_NAME
            + " (" + ExercisesTable.exerciseName + ", " +
            ExercisesTable.muscleWorked + ", " + ExercisesTable.isCompound +  ")"  + " values (?,?,?)";
    private SQLiteDatabase db;
    private SQLiteStatement insertStatement;
    public ExerciseDAO(Context context){
        SQLiteOpenHelper openHelper = new StrengthBuilderOpenHelper(context);
        db = openHelper.getWritableDatabase();
        insertStatement = db.compileStatement(INSERT);
    }
    @Override
    public long saveElement(ExerciseInfo element) {
        insertStatement.clearBindings();
        insertStatement.bindString(1, element.getNazwa());
        insertStatement.bindLong(2, element.getMuscleParts());
        Long isCompound = 0L;
        if(element.isCompound())isCompound=1L;
        insertStatement.bindLong(3,isCompound);
        return insertStatement.executeInsert();
    }

    @Override
    public void delete(ExerciseInfo element) {

    }

    @Override
    public List<ExerciseInfo> getAllElements() {
        List<ExerciseInfo> exercises = new ArrayList<>();
        //argumenty to kolejno nazwa tabeli
        //kolumny ktore chcemy wyswietlic
        //where
        //arg dla where
        //dalej
        Cursor c = db.query(ExercisesTable.TABLE_NAME, new String[]{ExercisesTable.Id, ExercisesTable.exerciseName,
                ExercisesTable.muscleWorked, ExercisesTable.isCompound}, null, null, null, null, null, null);
        if(c.moveToFirst()){
            do{
                long id = c.getLong(0);
                String name = c.getString(1);
                long workedMuscle = c.getLong(2);
                long isCompoundLong = c.getLong(3);
                boolean isCompound = (isCompoundLong==1L);
                ExerciseInfo exercise = new ExerciseInfo(id, name, workedMuscle, isCompound);
                exercises.add(exercise);

            }while(c.moveToNext());
        }
        if(!c.isClosed()){
            c.close();
        }
        return exercises;
    }

    @Override
    public ExerciseInfo getElementById(long id) {
        ExerciseInfo exercise = null;
        Cursor c = db.query(ExercisesTable.TABLE_NAME, new String[]{ExercisesTable.exerciseName,
                ExercisesTable.muscleWorked, ExercisesTable.isCompound},
                ExercisesTable.Id +  "=?", //where clausule
                new String[]{"" + id}, //where args
                null, null, null, null);
        if(c.moveToFirst()){
            String name = c.getString(0);
            long workedMuscle = c.getLong(1);
            long isCompoundLong = c.getLong(2);
            boolean isCompound = (isCompoundLong==1L);
            exercise = new ExerciseInfo(id, name, workedMuscle, isCompound);
        }
        if(!c.isClosed()){
            c.close();
        }
        return exercise;
    }
}
