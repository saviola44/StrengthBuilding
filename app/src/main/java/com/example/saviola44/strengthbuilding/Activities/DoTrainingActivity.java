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
import com.example.saviola44.strengthbuilding.Database.DAO.DAO;
import com.example.saviola44.strengthbuilding.Database.DAO.ExHistDAO;
import com.example.saviola44.strengthbuilding.Database.DAO.HistoryDAO;
import com.example.saviola44.strengthbuilding.Database.MusclePart;
import com.example.saviola44.strengthbuilding.Dialogs.EditSeriesDialog;
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
public class DoTrainingActivity extends AppCompatActivity implements EditSeriesDialog.EditSeries{
    ListView exercisesLV;
    List<WorkoutExercise> exercises;
    List<Boolean> doneEx;
    StrengthBuilderApp app;
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
        exercises = app.getNextTraining();
        doneEx = new ArrayList<>();
        for(int i=0; i<exercises.size(); i++){
            doneEx.add(false);
        }
        adapter = new DoTrainingAdapter(this, R.layout.added_trainings_row_layout,
                exercises, doneEx);
        exercisesLV.setAdapter(adapter);
        exercisesLV.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
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
                ArrayList<Integer> musclesIds = new ArrayList<>();
                for(int i=1; i<7; i++){
                    musclesIds.add(i);
                }
                intent.putIntegerArrayListExtra("muscleIDs",musclesIds);
                startActivityForResult(intent, 1);
            }
        });
        endTrainingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DAO dao = new ExHistDAO(getApplicationContext());
                dao.saveElement(exercises);
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

    @Override
    public void editReps(int pos, int reps) {
        exercises.get(pos).setReps(reps);
    }

    @Override
    public void editWeight(int pos, double weight) {
        exercises.get(pos).setWeight((int)weight);
    }

    @Override
    public void editComment(int pos, String comment) {
        exercises.get(pos).setComment(comment);
    }

    @Override
    public void deleteSeries(int pos) {
        doneEx.remove(pos);
        exercises.remove(pos);
        adapter.notifyDataSetChanged();
    }
}
