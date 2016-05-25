package com.example.saviola44.strengthbuilding.Activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.saviola44.strengthbuilding.AddNewTrainingDialog;
import com.example.saviola44.strengthbuilding.Model.ExerciseInfo;
import com.example.saviola44.strengthbuilding.Model.WorkoutExerciseInfo;
import com.example.saviola44.strengthbuilding.R;
import com.example.saviola44.strengthbuilding.SetMaxWeightDialog;
import com.example.saviola44.strengthbuilding.Training;

import java.util.List;

/**
 * Created by saviola44 on 24.05.16.
 */
public class AddTrainingActivity extends AppCompatActivity {
    TextView trainingNameTV;
    ImageView addNewExerciseIV;
    ListView exercisesLV;
    Training training;
    ListAdapter adapter;



    private int asking;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_training_activity);

        trainingNameTV = (TextView) findViewById(R.id.TrainingNameLabelTV);
        exercisesLV = (ListView) findViewById(R.id.trainingExercisesLV);
        addNewExerciseIV = (ImageView) findViewById(R.id.addTrainigIV);

        training = getIntent().getParcelableExtra("training");
        asking = getIntent().getIntExtra("mode", ShowAllExercisesActivity.returnExAfterClickTAG);

        trainingNameTV.setText(training.getTrainingLabel());
        adapter = new ArrayAdapter<WorkoutExerciseInfo>(getApplicationContext(), android.R.layout.simple_list_item_1, training.getExercises());
        exercisesLV.setAdapter(adapter);

        addNewExerciseIV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ShowAllExercisesActivity.class);
                intent.putExtra("mode", asking);
                startActivityForResult(intent, asking);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(resultCode == Activity.RESULT_OK){
            if(requestCode==asking) {
                WorkoutExerciseInfo w = data.getParcelableExtra("exercise");
                exercisesLV.setAdapter(adapter);
                Toast.makeText(getApplicationContext(), "udało sie: " + w.getMaxWeight(), Toast.LENGTH_LONG).show();
                training.getExercises().add(w);
            }
        }
    }
}
