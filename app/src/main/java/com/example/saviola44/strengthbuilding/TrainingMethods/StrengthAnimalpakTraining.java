package com.example.saviola44.strengthbuilding.TrainingMethods;

import com.example.saviola44.strengthbuilding.Activities.AddTrainingActivity;
import com.example.saviola44.strengthbuilding.Activities.ShowAllExercisesActivity;
import com.example.saviola44.strengthbuilding.Constants;
import com.example.saviola44.strengthbuilding.TrainingMethods.TrainingMethod;

/**
 * Created by saviola44 on 24.05.16.
 */
public class StrengthAnimalpakTraining implements TrainingMethod {
    private static final int TAG = Constants.TrainingFromAnimalpak;
    private static final String label = "Siłowy program z forum animalpak wg R. Słodkiewicza";
    @Override
    public int getTrainingTag() {
        return TAG;
    }

    @Override
    public int askAboutTAG() {
        return ShowAllExercisesActivity.askMaxWeightAfterClickTAG;
    }

    @Override
    public String toString() {
        return label;
    }
}
