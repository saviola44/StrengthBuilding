package com.example.saviola44.strengthbuilding.Database.DAO;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;

import com.example.saviola44.strengthbuilding.Database.StrengthBuilderOpenHelper;
import com.example.saviola44.strengthbuilding.Database.Tables.ExHistTable;
import com.example.saviola44.strengthbuilding.Database.Tables.ExercisesTable;
import com.example.saviola44.strengthbuilding.Model.ExerciseInfo;
import com.example.saviola44.strengthbuilding.Model.WorkoutExercise;
import com.example.saviola44.strengthbuilding.StrengthBuilderApp;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by saviola44 on 27.05.16.
 */
public class ExHistDAO implements DAO<List<WorkoutExercise>>{
    public static final String INSERT = "insert into " + ExHistTable.TABLE_NAME
            + " (" + ExHistTable.date + ", " + ExHistTable.exercise + ", " +
            ExHistTable.reps + ", " + ExHistTable.weight + ", " +
             ExHistTable.comment + ")" + " values (?,?,?,?,?)";

    private SQLiteDatabase db;
    private SQLiteStatement insertStatement;

    Context context;

    public ExHistDAO(Context context){
        SQLiteOpenHelper openHelper = new StrengthBuilderOpenHelper(context);
        db = openHelper.getWritableDatabase();
        insertStatement = db.compileStatement(INSERT);
        this.context = context;
    }

    @Override
    public long saveElement(List<WorkoutExercise> elements) {
        final long date = Calendar.getInstance().getTimeInMillis();
        for(int i=0; i<elements.size(); i++) {
            WorkoutExercise element = elements.get(i);
            insertStatement.clearBindings();
            insertStatement.bindLong(1, date);
            insertStatement.bindLong(2, element.getExercise().getId());
            insertStatement.bindLong(3, element.getReps());
            insertStatement.bindDouble(4, element.getWeight());
            String comment = element.getComment();
            if (comment == null) comment = "";
            insertStatement.bindString(5, comment);
        }
        DAO history = new HistoryDAO(context);
        history.saveElement(date);
        return insertStatement.executeInsert();
    }

    @Override
    public void delete(List<WorkoutExercise> element) {

    }

    @Override
    public List<List<WorkoutExercise>> getAllElements() {
        List<List<WorkoutExercise>> history = new ArrayList<>();
        List<Long> dates = new HistoryDAO(context).getAllElements();
        Cursor c=null;
        for(int i=0; i<dates.size(); i++){
            List<WorkoutExercise> exercises = new ArrayList<>();
            long date = dates.get(i);
             c = db.query(ExHistTable.TABLE_NAME, //Table name
                    new String[]{ExHistTable.exercise,ExHistTable.reps, ExHistTable.weight, ExHistTable.comment}, //Columns
                    ExHistTable.date + "=?", //Where clause
                    new String[]{""+date}, //Where args
                    null, null, null, null);
            if(c.moveToFirst()){
                ExerciseDAO exerciseDAO = new ExerciseDAO(context);
                do{
                    long ex = c.getLong(0);
                    int reps = c.getInt(1);
                    double weight = c.getDouble(2);
                    String comment = c.getString(3);
                    ExerciseInfo ei = exerciseDAO.getElementById(ex);
                    WorkoutExercise exercise = new WorkoutExercise(ei);
                    exercise.setReps(reps);
                    exercise.setWeight((int) weight);
                    exercise.setComment(comment);
                    exercise.setDate(date);
                    exercises.add(exercise);
                }while(c.moveToNext());
            }
            history.add(exercises);
        }
        if(c!=null && !c.isClosed()){
            c.close();
        }
        return history;
    }

    @Override
    public List<WorkoutExercise> getElementById(long date) {
        List<WorkoutExercise> exercises = new ArrayList<>();

        Cursor c = db.query(ExHistTable.TABLE_NAME, new String[]{ExHistTable.exercise,
                ExHistTable.reps, ExHistTable.weight, ExHistTable.comment},
                ExHistTable.date + "=?", //Where clause
                new String[]{""+date}, //Where args
                null, null, null, null);
        if(c.moveToFirst()){
            ExerciseDAO exerciseDAO = new ExerciseDAO(context);
            do{
                long ex = c.getLong(0);
                int reps = c.getInt(1);
                double weight = c.getDouble(2);
                String comment = c.getString(3);
                ExerciseInfo ei = exerciseDAO.getElementById(ex);
                WorkoutExercise exercise = new WorkoutExercise(ei);
                exercise.setReps(reps);
                exercise.setWeight((int) weight);
                exercise.setComment(comment);
                exercise.setDate(date);
                exercises.add(exercise);
            }while(c.moveToNext());
        }
        if(c!=null && !c.isClosed()){
            c.close();
        }
        return exercises;
    }
}
