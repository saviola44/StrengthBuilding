package com.example.saviola44.strengthbuilding.Activities;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.example.saviola44.strengthbuilding.Adapters.DoTrainingAdapter;
import com.example.saviola44.strengthbuilding.Model.ExerciseInfo;
import com.example.saviola44.strengthbuilding.Model.WorkoutExercise;
import com.example.saviola44.strengthbuilding.Model.WorkoutExerciseInfo;
import com.example.saviola44.strengthbuilding.R;
import com.example.saviola44.strengthbuilding.StrengthBuilderApp;
import com.example.saviola44.strengthbuilding.TrainingMethods.TrainingMethod;
import com.example.saviola44.strengthbuilding.TrainingPlan;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by saviola44 on 26.05.16.
 */
public class DoTrainingActivity extends AppCompatActivity {
    ListView exercisesLV;
    List<WorkoutExercise> exercises;
    List<Boolean> doneEx;
    StrengthBuilderApp app;
    TrainingPlan trainingPlan;
    DoTrainingAdapter adapter;
    Button addExButton;
    Button endTrainingButton;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.do_training_activity_layout);
        exercisesLV = (ListView) findViewById(R.id.doTrainingExLV);
        addExButton = (Button) findViewById(R.id.addExerciseButton);
        endTrainingButton = (Button) findViewById(R.id.endTraingButton);

        app = StrengthBuilderApp.getInstance(getApplicationContext());
        trainingPlan = app.getPlan();
        exercises = app.getNextTraining();
        doneEx = new ArrayList<>();
        for(int i=0; i<exercises.size(); i++){
            doneEx.add(false);
        }
        adapter = new DoTrainingAdapter(getApplicationContext(), R.layout.added_trainings_row_layout,
                exercises);
        exercisesLV.setAdapter(adapter);
        exercisesLV.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.d("elo", "position= " + position);
                //view.setBackgroundColor(Color.rgb(56,175,68));
                doneEx.set(position, true);
                adapter.setDoneEx(position);
                adapter.notifyDataSetChanged();
            }
        });

        addExButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ShowAllExercisesActivity.class);
                intent.putExtra("mode", ShowAllExercisesActivity.returnExAfterClickTAG);
                startActivityForResult(intent, 1);
            }
        });
        endTrainingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //zapisz trening w historii
                StrengthBuilderApp.getInstance(getApplicationContext()).finishCurrentTraining();
                finish();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(resultCode==RESULT_OK){
            ExerciseInfo ei = data.getParcelableExtra("exercise");
            WorkoutExercise we = new WorkoutExercise(ei);
            //exercises.add(we);
            doneEx.add(false);
            adapter.addExercise(we);
            adapter.notifyDataSetChanged();
        }
    }
}
