package com.example.saviola44.strengthbuilding.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.saviola44.strengthbuilding.Adapters.AddedTrainingsAdapter;
import com.example.saviola44.strengthbuilding.R;
import com.example.saviola44.strengthbuilding.StrengthBuilderApp;
import com.example.saviola44.strengthbuilding.Training;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by saviola44 on 27.05.16.
 */
public class DisplayTrainingPlanActivity extends AppCompatActivity {
    TextView trainingMathodTV;
    List<Training> trainings;
    AddedTrainingsAdapter adapter; //adapter dla ponizszej listy
    ListView trainingsLV; //wyswietla liste treningow

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.display_cur_plan_layout);

        trainingsLV = (ListView) findViewById(R.id.trainingsLV);
        trainingMathodTV = (TextView) findViewById(R.id.trainingMethodTV);

        StrengthBuilderApp app = StrengthBuilderApp.getInstance(getApplicationContext());
        trainings = app.getPlan().getTrainings();
        trainingMathodTV.setText(app.getPlan().getTrainingMethod().toString());
        adapter = new AddedTrainingsAdapter(getApplicationContext(),
                R.layout.added_trainings_row_layout, trainings);
        trainingsLV.setAdapter(adapter);

        trainingsLV.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(), DisplayTrainingActivity.class);
                intent.putExtra("pos", position);
                startActivity(intent);
            }
        });

    }
}
