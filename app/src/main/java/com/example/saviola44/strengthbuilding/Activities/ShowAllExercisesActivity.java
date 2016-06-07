package com.example.saviola44.strengthbuilding.Activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;

import com.example.saviola44.strengthbuilding.Adapters.ExpandableExercisesListAdapter;
import com.example.saviola44.strengthbuilding.Database.DAO.ExerciseDAO;
import com.example.saviola44.strengthbuilding.Database.MusclePart;
import com.example.saviola44.strengthbuilding.Dialogs.GetNumberDialog;
import com.example.saviola44.strengthbuilding.Model.ExerciseInfo;
import com.example.saviola44.strengthbuilding.Model.Muscle;
import com.example.saviola44.strengthbuilding.Model.WorkoutExerciseInfo;
import com.example.saviola44.strengthbuilding.R;

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
public class ShowAllExercisesActivity extends AppCompatActivity implements GetNumberDialog.SetMaxWeightOrSeries{
    ExpandableListView expListView;
    ExpandableListAdapter listAdapter;

    List<Muscle> muscleParts;
    HashMap<Long, List<ExerciseInfo>> exercises;
    List<ExerciseInfo> allExercises;
    public static final int returnExAfterClickTAG = 1;
    public static final int showExAfterClickTAG = 2;
    public static final int askMaxWeightAfterClickTAG = 3;
    public static final int askAboutSeriesTAG =4;

   /* public static final int displayFBWTAG = 1;
    public static final int displayPushTAG = 2;
    public static final int displayPullTAG = 3;
    public static final int displayLegsTAG = 4;
    */
    List<Muscle> musclePartsForLV = new ArrayList<>();
    ExerciseInfo selectedEx;
    int clickMode;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.show_all_exercises_layout);
        expListView = (ExpandableListView) findViewById(R.id.ExpLVdatabase);

        clickMode = getIntent().getIntExtra("mode", showExAfterClickTAG); //domyslnie
        List<Integer> musclePartIDs = getIntent().getIntegerArrayListExtra("muscleIDs");

        MusclePart musclePart = new MusclePart(getApplicationContext());
        muscleParts = musclePart.getAllMuscleParts();
        ExerciseDAO exerciseDAO = new ExerciseDAO(getApplicationContext());

        allExercises = exerciseDAO.getAllElements();

        exercises = new HashMap<>();

        for(int i=0; i<muscleParts.size(); i++){
            if(musclePartIDs.contains((int)muscleParts.get(i).getId())){
                musclePartsForLV.add(muscleParts.get(i));
            }
        }
        for(int i=0; i<musclePartIDs.size(); i++){
            List<ExerciseInfo> tmp = new ArrayList<>();
            exercises.put((long)musclePartIDs.get(i), tmp);
        }
        createListView();



        listAdapter = new ExpandableExercisesListAdapter(getApplicationContext(), musclePartsForLV, exercises);
        expListView.setAdapter(listAdapter);
        expListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                if(clickMode == returnExAfterClickTAG) {
                    Muscle m = musclePartsForLV.get(groupPosition);
                    ExerciseInfo e = exercises.get(m.getId()).get(childPosition);
                    Intent result = new Intent();
                    result.putExtra("exercise", e);
                    setResult(Activity.RESULT_OK, result);
                    finish();
                }
                else if (clickMode == askMaxWeightAfterClickTAG){
                    Muscle m = musclePartsForLV.get(groupPosition);
                    if (exercises == null) {
                        Log.d("exercies", "ex jest nullem");
                    }
                    else{
                        if(exercises.get(m.getId())==null){
                            Log.d("exercies", "ex jest nullem to nastepne " + groupPosition + " " + childPosition);
                        }
                    }
                    selectedEx = exercises.get(m.getId()).get(childPosition);
                    if(selectedEx.isCompound()) {
                        GetNumberDialog dialog = new GetNumberDialog();
                        Bundle bundle = new Bundle();
                        bundle.putInt("mode", GetNumberDialog.ASK_ABOUT_1RM);
                        bundle.putString("alert", "Musisz podac swój ciezar 1RM");
                        dialog.setArguments(bundle);
                        dialog.show(getSupportFragmentManager(), "getNbTAG");
                    }
                    else {
                        //liczba serii
                        GetNumberDialog dialog = new GetNumberDialog();
                        Bundle bundle = new Bundle();
                        bundle.putInt("mode", GetNumberDialog.ASK_ABOUT_SERIES);
                        dialog.setArguments(bundle);
                        dialog.show(getSupportFragmentManager(), "getNbTAG");
                    }
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
    public void setNbOfSeries(int series) {
        WorkoutExerciseInfo w = new WorkoutExerciseInfo(selectedEx);
        w.setNumberOfSeries(series);
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

    void createListView(){
        for(int i=0; i<allExercises.size(); i++){
            ExerciseInfo e = allExercises.get(i);
            List<ExerciseInfo> tmp;
            if(exercises.containsKey(e.getMuscleParts())){
                tmp = exercises.get(e.getMuscleParts());
                tmp.add(e);
                exercises.put(e.getMuscleParts(),tmp);
            }

        }
    }
}
