package com.example.saviola44.strengthbuilding.Activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.Toast;

import com.example.saviola44.strengthbuilding.Adapters.ExpandableExercisesListAdapter;
import com.example.saviola44.strengthbuilding.Database.DAO.ExerciseDAO;
import com.example.saviola44.strengthbuilding.Database.MusclePart;
import com.example.saviola44.strengthbuilding.Model.ExerciseInfo;
import com.example.saviola44.strengthbuilding.Model.Muscle;
import com.example.saviola44.strengthbuilding.Model.WorkoutExerciseInfo;
import com.example.saviola44.strengthbuilding.R;
import com.example.saviola44.strengthbuilding.SetMaxWeightDialog;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by saviola44 on 22.05.16.
 * klasa ta działa tak
 * jesli w intencji przeslesz 1 to tylko w celach informacyjnych wyswietla ci cwiczenia
 * jesli w intencji przeslesz 2 to po kliknieciu zwraca ci cwiczenie spowrotem do aktywnosci
 * jesli w intencji przeslesz 3 to jesli cwiczenie zlozone to sie zapyta o 1RM i zwroci spowrotem
 * do aktywnosci cwiczenie
 *
 */
public class ShowAllExercisesActivity extends AppCompatActivity implements SetMaxWeightDialog.SetMaxWeight{
    ExpandableListView expListView;
    ExpandableListAdapter listAdapter;
    List<Muscle> muscleParts;
    HashMap<Long, List<ExerciseInfo>> exercises;
    public static final int returnExAfterClickTAG = 1;
    public static final int showExAfterClickTAG = 2;
    public static final int askMaxWeightAfterClickTAG = 3;

    ExerciseInfo selectedEx; //zmienna ktora pamieta wybrane cwiczenie zeby po powrocie z dialogu
                            // ustawic jej 1Rm

    int currentMode;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.show_all_exercises_layout);
        currentMode = getIntent().getIntExtra("mode", showExAfterClickTAG); //domyslnie
        final MusclePart musclePart = new MusclePart(getApplicationContext());
        ExerciseDAO exerciseDAO = new ExerciseDAO(getApplicationContext());
        muscleParts = musclePart.getAllMuscleParts();
        exercises = new HashMap<>();
        List<ExerciseInfo> allExercises = exerciseDAO.getAllElements();
        for(int i=0; i<allExercises.size(); i++){
            ExerciseInfo e = allExercises.get(i);
            List<ExerciseInfo> tmp;
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
        expListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                if(currentMode == returnExAfterClickTAG) {
                    Muscle m = muscleParts.get(groupPosition);
                    ExerciseInfo e = exercises.get(m.getId()).get(childPosition);
                    Intent result = new Intent();
                    result.putExtra("exercise", e);
                    setResult(Activity.RESULT_OK, result);
                    finish();
                }
                else if (currentMode == askMaxWeightAfterClickTAG){
                    Muscle m = muscleParts.get(groupPosition);
                    selectedEx = exercises.get(m.getId()).get(childPosition);

                    Toast.makeText(getApplicationContext(), "elo", Toast.LENGTH_LONG).show();
                    SetMaxWeightDialog dialog = new SetMaxWeightDialog();
                    dialog.show(getSupportFragmentManager(), "setMaxTAG");
                }
                return false;
            }
        });
    }

    @Override
    public void setMaxWeight(double weight) {
        WorkoutExerciseInfo w = new WorkoutExerciseInfo(selectedEx);
        w.setMaxWeight(weight);
        Intent result = new Intent();
        result.putExtra("exercise", w);
        setResult(Activity.RESULT_OK, result);
        finish();
    }

    @Override
    public void cancel() {
        setResult(Activity.RESULT_CANCELED);
        finish();
    }
}
