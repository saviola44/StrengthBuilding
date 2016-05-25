package com.example.saviola44.strengthbuilding.TrainingMethods;

import com.example.saviola44.strengthbuilding.Activities.AddTrainingActivity;
import com.example.saviola44.strengthbuilding.Activities.ShowAllExercisesActivity;
import com.example.saviola44.strengthbuilding.Constants;

/**
 * Created by saviola44 on 24.05.16.
 */
public class RippetoeTraining implements TrainingMethod {
    private static final int TAG = Constants.RippetoeTraining;
    private static final String label = "Trening Rippetoe";
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
