package com.example.saviola44.strengthbuilding.TrainingMethods;

import com.example.saviola44.strengthbuilding.Activities.ShowAllExercisesActivity;
import com.example.saviola44.strengthbuilding.Constants;

/**
 * Created by saviola44 on 24.05.16.
 * wykorzystuje metode prgresji liniowej dla 10 poowtórzen
 */


public class MassFBW extends FBW {
    private static final int TAG = Constants.MassFBW;
    private static final String label = "FBW";
    double[] weightPercentages = new double[]{0.75, 0.775, 0.8, 0.825, 0.85,
            0.8, 0.825, 0.85, 0.875, 0.9,
            0.85, 0.875, 0.9, 0.925, 0.95};
    int[] reps = new int[]{10, 10, 10, 10, 10,
            8,8,8,8,8, 6,6,6,6,6};


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
        return 3;
    }

    @Override
    public int getStrengthExReps(int trainingNb) {
        return reps[trainingNb/3];
    }


    @Override
    public double getSeriesWeight(int trainingNb, int series,  double max) {
        return weightPercentages[trainingNb/3]*max;
    }

    @Override
    public int getPumpExReps() {
        return 10;
    }


    @Override
    public String toString() {
        return label;
    }
}
