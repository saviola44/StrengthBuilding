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

import com.example.saviola44.strengthbuilding.Model.WorkoutExerciseInfo;
import com.example.saviola44.strengthbuilding.R;
import com.example.saviola44.strengthbuilding.Training;

/**
 * Created by saviola44 on 24.05.16.
 *
 */
public class AddTrainingActivity extends AppCompatActivity {
    TextView trainingNameTV;
    ImageView addNewExerciseIV;
    ImageView doneAddExIV;
    ListView exercisesLV;
    Training training;
    ListAdapter adapter;

    private int asking; //w zależnosci od tej zmiennej jesli bedziemy wybierac cwiczenia to
                        //bedziemy sie pytac o 1RM lub nie,
    private int complexSeries; //liczba serii dla cwiczen wielostawowych - jest rozna w zaleznosci od treningu
                                //mozna by to odczytywac z klas treningu ale malo wydajne wydaje sie przesylanie
                                //calej klasy zwlaszcza ze trzeba by implementowac jeszcze Parcelable

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_training_activity);

        trainingNameTV = (TextView) findViewById(R.id.TrainingNameLabelTV);
        exercisesLV = (ListView) findViewById(R.id.trainingExercisesLV);
        addNewExerciseIV = (ImageView) findViewById(R.id.addTrainigIV);
        doneAddExIV = (ImageView) findViewById(R.id.AddExerciseDoneIV);

        training = getIntent().getParcelableExtra("training");
        asking = getIntent().getIntExtra("mode", ShowAllExercisesActivity.returnExAfterClickTAG);
        complexSeries = getIntent().getIntExtra("complexSeries",4);

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
        doneAddExIV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent result = new Intent();
                result.putExtra("training", training);
                setResult(Activity.RESULT_OK, result);
                finish();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(resultCode == Activity.RESULT_OK){
            if(requestCode==asking) {
                WorkoutExerciseInfo w = data.getParcelableExtra("exercise");
                if(asking==ShowAllExercisesActivity.askMaxWeightAfterClickTAG){
                    w.setNumberOfSeries(complexSeries);
                }
                exercisesLV.setAdapter(adapter);
                training.getExercises().add(w);
            }
        }
    }
}
