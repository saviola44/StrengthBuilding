package com.example.saviola44.strengthbuilding.TrainingMethods;

import com.example.saviola44.strengthbuilding.Activities.AddTrainingActivity;
import com.example.saviola44.strengthbuilding.Activities.ShowAllExercisesActivity;
import com.example.saviola44.strengthbuilding.Constants;
import com.example.saviola44.strengthbuilding.Model.WorkoutExercise;
import com.example.saviola44.strengthbuilding.TrainingPlan;

import java.util.List;

/**
 * Created by saviola44 on 24.05.16.
 */
public class StrengthPPL extends PPL {
    private static final int TAG = Constants.StrengthPPL;
    private static final String label = "PPL na siłe";
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
        return 4;
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
