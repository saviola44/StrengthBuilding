package com.example.saviola44.strengthbuilding.Activities;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.example.saviola44.strengthbuilding.Adapters.DoTrainingAdapter;
import com.example.saviola44.strengthbuilding.Model.WorkoutExercise;
import com.example.saviola44.strengthbuilding.Model.WorkoutExerciseInfo;
import com.example.saviola44.strengthbuilding.R;
import com.example.saviola44.strengthbuilding.StrengthBuilderApp;
import com.example.saviola44.strengthbuilding.TrainingMethods.TrainingMethod;
import com.example.saviola44.strengthbuilding.TrainingPlan;

import java.util.List;

/**
 * Created by saviola44 on 26.05.16.
 */
public class DoTrainingActivity extends AppCompatActivity {
    ListView exercisesLV;
    List<WorkoutExercise> exercises;
    StrengthBuilderApp app;
    TrainingPlan trainingPlan;
    DoTrainingAdapter adapter;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.do_training_activity_layout);
        exercisesLV = (ListView) findViewById(R.id.doTrainingExLV);
        app = StrengthBuilderApp.getInstance();
        trainingPlan = app.getPlan();
        exercises = app.getNextTraining();
        adapter = new DoTrainingAdapter(getApplicationContext(), R.layout.added_trainings_row_layout,
                exercises);
        exercisesLV.setAdapter(adapter);


    }
}
