package com.example.saviola44.strengthbuilding.TrainingMethods;

import com.example.saviola44.strengthbuilding.Constants;

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
    public String toString() {
        return label;
    }
}
