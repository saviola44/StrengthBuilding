package com.example.saviola44.strengthbuilding.Activities;

import android.app.Activity;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;

import com.example.saviola44.strengthbuilding.Adapters.ExpandableExercisesListAdapter;
import com.example.saviola44.strengthbuilding.Database.DAO.ExerciseDAO;
import com.example.saviola44.strengthbuilding.Database.MusclePart;
import com.example.saviola44.strengthbuilding.Model.Exercise;
import com.example.saviola44.strengthbuilding.Model.Muscle;
import com.example.saviola44.strengthbuilding.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by saviola44 on 22.05.16.
 */
public class ShowAllExercisesActivity extends Activity {
    ExpandableListView expListView;
    ExpandableListAdapter listAdapter;
    List<Muscle> muscleParts;
    HashMap<Long, List<Exercise>> exercises;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.show_all_exercises_layout);
        MusclePart musclePart = new MusclePart(getApplicationContext());
        ExerciseDAO exerciseDAO = new ExerciseDAO(getApplicationContext());
        muscleParts = musclePart.getAllMuscleParts();
        exercises = new HashMap<>();
        List<Exercise> allExercises = exerciseDAO.getAllElements();
        for(int i=0; i<allExercises.size(); i++){
            Exercise e = allExercises.get(i);
            List<Exercise> tmp;
            if(exercises.containsKey(e.getMuscleParts())){
                tmp = exercises.get(e.getMuscleParts());
            }
            else{
                tmp = new ArrayList<>();
            }
            tmp.add(e);
            exercises.put(e.getMuscleParts(),tmp);
        }
        expListView = (ExpandableListView) findViewById(R.id.ExpLVdatabase);

        listAdapter = new ExpandableExercisesListAdapter(getApplicationContext(), muscleParts, exercises);
        expListView.setAdapter(listAdapter);
    }
}
