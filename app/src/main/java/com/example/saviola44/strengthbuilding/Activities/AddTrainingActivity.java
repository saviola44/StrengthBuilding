package com.example.saviola44.strengthbuilding.Activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.saviola44.strengthbuilding.Model.Exercise;
import com.example.saviola44.strengthbuilding.R;
import com.example.saviola44.strengthbuilding.Training;

import java.util.List;

/**
 * Created by saviola44 on 24.05.16.
 */
public class AddTrainingActivity extends AppCompatActivity {
    TextView trainingNameTV;
    ImageView addNewExerciseIV;
    ListView exercisesLV;
    List<Exercise> exercises;
    Training training;
    ListAdapter adapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_training_activity);
        trainingNameTV = (TextView) findViewById(R.id.TrainingNameLabelTV);
        exercisesLV = (ListView) findViewById(R.id.trainingExercisesLV);
        training = getIntent().getParcelableExtra("training");
        addNewExerciseIV = (ImageView) findViewById(R.id.addTrainigIV);
        trainingNameTV.setText(training.getTrainingLabel());
        exercises = training.getExercises();
        adapter = new ArrayAdapter<Exercise>(getApplicationContext(), android.R.layout.simple_list_item_1, exercises);
        exercisesLV.setAdapter(adapter);

        addNewExerciseIV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ShowAllExercisesActivity.class);
                intent.putExtra("TAG", ShowAllExercisesActivity.returnExAfterClickTAG);
                startActivityForResult(intent, 1);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(resultCode == Activity.RESULT_OK){
            Exercise e = data.getParcelableExtra("exercise");
            exercises.add(e);
            exercisesLV.setAdapter(adapter);
        }
    }
}
