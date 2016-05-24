package com.example.saviola44.strengthbuilding.TrainingMethods;

import com.example.saviola44.strengthbuilding.Constants;

/**
 * Created by saviola44 on 24.05.16.
 */
public class MassPPL extends PPL {
    private static final int TAG = Constants.MassPPL;
    private static final String label = "PPL";
    @Override
    public int getTrainingTag() {
        return TAG;
    }

    @Override
    public String toString() {
        return label;
    }
}
