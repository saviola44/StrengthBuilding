package com.example.saviola44.strengthbuilding.Activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.saviola44.strengthbuilding.Constants;
import com.example.saviola44.strengthbuilding.R;
import com.example.saviola44.strengthbuilding.TrainingMethods.MassFBW;
import com.example.saviola44.strengthbuilding.TrainingMethods.MassPPL;
import com.example.saviola44.strengthbuilding.TrainingMethods.RippetoeTraining;
import com.example.saviola44.strengthbuilding.TrainingMethods.RussianPowerliftingTraining;
import com.example.saviola44.strengthbuilding.TrainingMethods.Split;
import com.example.saviola44.strengthbuilding.TrainingMethods.StrengthAnimalpakTraining;
import com.example.saviola44.strengthbuilding.TrainingMethods.StrengthFBW;
import com.example.saviola44.strengthbuilding.TrainingMethods.StrengthPPL;
import com.example.saviola44.strengthbuilding.TrainingMethods.TrainingMethod;

/**
 * Created by saviola44 on 24.05.16.
 */
public class AddTrainingsActivity extends AppCompatActivity {
    ListView trainingsLV;
    TextView trainingMathodTV;
    TrainingMethod trainingMethod;
    ImageView addTrainingIV;
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
        if(trainingMethod!=null) {
            trainingMathodTV.setText(trainingMethod.toString());
        }

        addTrainingIV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

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
}
