package com.example.saviola44.strengthbuilding.Activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.saviola44.strengthbuilding.Adapters.AddedTrainingsAdapter;
import com.example.saviola44.strengthbuilding.AddNewTrainingDialog;
import com.example.saviola44.strengthbuilding.Constants;
import com.example.saviola44.strengthbuilding.R;
import com.example.saviola44.strengthbuilding.StrengthBuilderApp;
import com.example.saviola44.strengthbuilding.Training;
import com.example.saviola44.strengthbuilding.TrainingMethods.MassFBW;
import com.example.saviola44.strengthbuilding.TrainingMethods.MassPPL;
import com.example.saviola44.strengthbuilding.TrainingMethods.RippetoeTraining;
import com.example.saviola44.strengthbuilding.TrainingMethods.RussianPowerliftingTraining;
import com.example.saviola44.strengthbuilding.TrainingMethods.Split;
import com.example.saviola44.strengthbuilding.TrainingMethods.StrengthAnimalpakTraining;
import com.example.saviola44.strengthbuilding.TrainingMethods.StrengthFBW;
import com.example.saviola44.strengthbuilding.TrainingMethods.StrengthPPL;
import com.example.saviola44.strengthbuilding.TrainingMethods.TrainingMethod;
import com.example.saviola44.strengthbuilding.TrainingPlan;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by saviola44 on 24.05.16.
 */
public class AddTrainingsActivity extends AppCompatActivity
        implements AddNewTrainingDialog.AddTrainingInterface {
    private AddNewTrainingDialog addNewTrainingDialog = new AddNewTrainingDialog();

    List<Training> trainings;
    AddedTrainingsAdapter adapter; //adapter dla ponizszej listy
    ListView trainingsLV; //wyswietla liste treningow
    TextView trainingMathodTV;
    TrainingMethod trainingMethod; //wybrana metoda treningowa - przesylana w intencji
    ImageView addTrainingIV; //przycisk dodawania treninfu w postaci obrazka
    ImageView doneTrainingIV;
    public static final String TRAINING_TAG = "metoda";
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_trainings_activity_layout);
        int TAG = getIntent().getIntExtra(TRAINING_TAG, 1);
        setTrainingMethod(TAG);
        trainingsLV = (ListView) findViewById(R.id.trainingsLV);
        trainingMathodTV = (TextView) findViewById(R.id.trainingMethodTV);
        addTrainingIV = (ImageView) findViewById(R.id.addTrainigIV);
        doneTrainingIV = (ImageView) findViewById(R.id.doneTrainingIV);

        trainings = new ArrayList<>();
        adapter = new AddedTrainingsAdapter(getApplicationContext(),
                R.layout.added_trainings_row_layout, trainings);
        trainingsLV.setAdapter(adapter);
        if(trainingMethod!=null) {
            trainingMathodTV.setText(trainingMethod.toString());
        }

        trainingsLV.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Training t = trainings.get(position);
                Intent intent = new Intent(getApplicationContext(), AddTrainingActivity.class);
                intent.putExtra("training", t);
                intent.putExtra("mode", trainingMethod.askAboutTAG());
                intent.putExtra("complexSeries", trainingMethod.nbOfSeriesForStrengthEx());
                startActivityForResult(intent, position);
            }
        });
        addTrainingIV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addNewTrainingDialog.show(getSupportFragmentManager(), "createTrainingTAG");
            }
        });

        doneTrainingIV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean ok = true;
                if(trainings.size()==0){
                    Toast.makeText(getApplicationContext(), "Plan treningowy musi posiadac conajmniej " +
                            "jeden trening", Toast.LENGTH_LONG).show();
                    ok=false;
                }
                else {
                    for(int i=0; i<trainings.size(); i++){
                        if(trainings.get(i).getExercises().size()==0){
                            Toast.makeText(getApplicationContext(), "Trening " +
                                    trainings.get(i).getTrainingLabel() + " nie zawiera cwiczen!", Toast.LENGTH_LONG).show();
                            ok=false;
                        }
                    }
                }
                if(ok){
                    Toast.makeText(getApplicationContext(), "Generuje plan Treningowy", Toast.LENGTH_LONG).show();
                    TrainingPlan plan = new TrainingPlan();
                    plan.setTrainingMethod(trainingMethod);
                    plan.setTrainings(trainings);
                    StrengthBuilderApp app = StrengthBuilderApp.getInstance(getApplicationContext());
                    app.saveTrainingPlan(plan, getApplicationContext());
                    finish();
                }
            }
        });
    }

    private void setTrainingMethod(int TAG){
        switch (TAG){
            case Constants.TrainingFromAnimalpak:
                trainingMethod = new StrengthAnimalpakTraining();
                break;
            case Constants.MassFBW:
                trainingMethod = new MassFBW();
                break;
            case Constants.MassPPL:
                trainingMethod = new MassPPL();
                break;
            case Constants.RippetoeTraining:
                trainingMethod = new RippetoeTraining();
                break;
            case Constants.RussianPowerliftingTraining:
                trainingMethod = new RussianPowerliftingTraining();
                break;
            case Constants.Split:
                trainingMethod = new Split();
                break;
            case Constants.StrengthFBW:
                trainingMethod = new StrengthFBW();
                break;
            case Constants.StrengthPPL:
                trainingMethod = new StrengthPPL();
        }
    }

    @Override
    public void addTraining(Training t) {
        trainings.add(t);
        //adapter.addtraining(t);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(resultCode == Activity.RESULT_OK){
            Training t = data.getParcelableExtra("training");
            trainings.set(requestCode, t);
        }
    }
}
