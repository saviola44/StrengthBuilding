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
    double[] weightPercentages = new double[]{
            0.55, 0.60, 0.55, 0.60, 0.65,
            0.60, 0.65, 0.70, 0.65, 0.70,
            0.75, 0.70, 0.75, 0.80, 0.75, 0.80
    }; //ciezary poczatkowe dla 1 serii



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
    public String toString() {
        return label;
    }



    @Override
    public int getPumpExReps() {
        return 8;
    }

    @Override
    public int getStrengthExReps(int trainingNb) {
        return 5;
    }

    @Override
    public double getSeriesWeight(int trainingNb, int seriesNb, double max) {
        return (weightPercentages[trainingNb/3]+(0.05*((double)seriesNb)))*max;
    }
}
