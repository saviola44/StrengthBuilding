package com.example.saviola44.strengthbuilding;

import android.content.Context;
import android.os.AsyncTask;

import com.example.saviola44.strengthbuilding.Database.DAO.DAO;
import com.example.saviola44.strengthbuilding.Database.DAO.ExHistDAO;
import com.example.saviola44.strengthbuilding.Model.WorkoutExercise;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by saviola44 on 2016-06-09.
 */
public class SaveTrainingAsyncTask extends AsyncTask<Void, Void, Void> {
    List<WorkoutExercise> exercises;
    List<Boolean> doneEx;
    Context context;
    public SaveTrainingAsyncTask(Context context, List<WorkoutExercise> exercises, List<Boolean> doneEx) {
        this.context=context;
        this.exercises=exercises;
        this.doneEx=doneEx;
    }

    @Override
    protected Void doInBackground(Void... params) {
        DAO dao = new ExHistDAO(context);
        List<WorkoutExercise> doneExList = new ArrayList<>();
        for (int i=0; i<exercises.size(); i++){
            if(doneEx.get(i)){
                doneExList.add(exercises.get(i));
            }
        }
        if(doneExList.size()>0) {
            dao.saveElement(doneExList);
        }
        return null;
    }
}
