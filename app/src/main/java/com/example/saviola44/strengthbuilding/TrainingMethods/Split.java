package com.example.saviola44.strengthbuilding.TrainingMethods;

import com.example.saviola44.strengthbuilding.Constants;

/**
 * Created by saviola44 on 25.05.16.
 */
public class Split implements TrainingMethod {
    private static final int TAG = Constants.Split;
    private static final String label = "Split";
    @Override
    public int getTrainingTag() {
        return TAG;
    }

    @Override
    public String toString() {
        return label;
    }
}
