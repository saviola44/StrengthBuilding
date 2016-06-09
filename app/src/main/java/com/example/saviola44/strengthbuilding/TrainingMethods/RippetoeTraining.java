package com.example.saviola44.strengthbuilding.TrainingMethods;

import com.example.saviola44.strengthbuilding.Activities.ShowAllExercisesActivity;
import com.example.saviola44.strengthbuilding.Constants;
import com.example.saviola44.strengthbuilding.Model.WorkoutExercise;
import com.example.saviola44.strengthbuilding.Model.TrainingPlan;

import java.util.List;

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
    public int nbOfSeriesForStrengthEx() {
        return 5;
    }

    @Override
    public List<WorkoutExercise> getWorkoutExercises(TrainingPlan plan,int trainingNb) {
        return null;
    }

    @Override
    public String toString() {
        return label;
    }
}
