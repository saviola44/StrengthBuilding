package com.example.saviola44.strengthbuilding.Activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.saviola44.strengthbuilding.Model.WorkoutExerciseInfo;
import com.example.saviola44.strengthbuilding.R;
import com.example.saviola44.strengthbuilding.StrengthBuilderApp;
import com.example.saviola44.strengthbuilding.Model.Training;

/**
 * Created by saviola44 on 27.05.16.
 */
public class DisplayTrainingActivity extends AppCompatActivity {
    TextView trainingNameTV;
    ListView exercisesLV;
    Training training;
    ListAdapter adapter;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.display_selected_training_layout);

        trainingNameTV = (TextView) findViewById(R.id.TrainingNameLabelTV);
        exercisesLV = (ListView) findViewById(R.id.trainingExercisesLV);

        int pos = getIntent().getIntExtra("pos", 0);

        StrengthBuilderApp app = StrengthBuilderApp.getInstance(getApplicationContext());
        training = app.getPlan().getTrainings().get(pos);

        trainingNameTV.setText(training.getTrainingLabel());
        adapter = new ArrayAdapter<WorkoutExerciseInfo>(getApplicationContext(), android.R.layout.simple_list_item_1, training.getExercises());
        exercisesLV.setAdapter(adapter);
    }
}
