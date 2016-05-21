package com.example.saviola44.strengthbuilding.Database.DAO;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;

import com.example.saviola44.strengthbuilding.Database.StrengthBuilderOpenHelper;
import com.example.saviola44.strengthbuilding.Database.Tables.ExercisesTable;
import com.example.saviola44.strengthbuilding.Model.Exercise;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by saviola44 on 21.05.16.
 */
public class ExerciseDAO implements DAO<Exercise> {
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
    public long saveElement(Exercise element) {
        insertStatement.clearBindings();
        insertStatement.bindString(1, element.getNazwa());
        insertStatement.bindLong(2, element.getMuscleParts());
        Long isCompound = 0L;
        if(element.isCompound())isCompound=1L;
        insertStatement.bindLong(3,isCompound);
        return insertStatement.executeInsert();
    }

    @Override
    public void delete(Exercise element) {

    }

    @Override
    public List<Exercise> getAllElements() {
        List<Exercise> exercises = new ArrayList<>();
        //argumenty to kolejno nazwa tabeli
        //kolumny ktore chcemy wyswietlic
        //where
        //arg dla where
        //dalej
        Cursor c = db.query(ExercisesTable.TABLE_NAME, new String[]{ExercisesTable.exerciseName,
                ExercisesTable.muscleWorked, ExercisesTable.isCompound}, null, null, null, null, null, null);
        if(c.moveToFirst()){
            do{
                String name = c.getString(0);
                String workedMuscle = c.getString(1);
                String isCompound = c.getString(2);
                Exercise exercise = new Exercise(name, Long.parseLong(workedMuscle), Boolean.parseBoolean(isCompound));
                exercises.add(exercise);
            }while(c.moveToNext());
        }
        if(!c.isClosed()){
            c.close();
        }
        return exercises;
    }
}
