package com.example.saviola44.strengthbuilding.Activities;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.saviola44.strengthbuilding.Model.Exercise;
import com.example.saviola44.strengthbuilding.R;
import com.example.saviola44.strengthbuilding.Training;

import java.util.List;

/**
 * Created by saviola44 on 24.05.16.
 */
public class AddTrainingActivity extends AppCompatActivity {
    String trainingLabel;
    TextView trainingNameTV;
    ListView exercisesLV;
    List<Exercise> exercises;
    Training training;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_training_activity);
        trainingNameTV = (TextView) findViewById(R.id.TrainingNameLabelTV);
        exercisesLV = (ListView) findViewById(R.id.trainingExercisesLV);
        training = getIntent().getParcelableExtra("training");
        exercises = training.getExercises();
        ListAdapter adapter = new ArrayAdapter<Exercise>(getApplicationContext(), android.R.layout.simple_list_item_1, exercises);
        exercisesLV.setAdapter(adapter);
    }
}
