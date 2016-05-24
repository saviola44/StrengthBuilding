package com.example.saviola44.strengthbuilding;

import com.example.saviola44.strengthbuilding.Model.Exercise;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by saviola44 on 24.05.16.
 */
//reprezentuje pojedynczy trening, normalny plan treningowy składa sie z kilku np 3-5 takich treninow
public class Training {
    String trainingLabel;
    String trainingDescription;
    List<Exercise> exercises;
    List<Integer> exercisesSeries;

    public Training() {
        exercises = new ArrayList<>();
        exercisesSeries = new ArrayList<>();
    }

    public String getTrainingLabel() {
        return trainingLabel;
    }

    public String getTrainingDescription() {
        return trainingDescription;
    }

    public List<Exercise> getExercises() {
        return exercises;
    }

    public List<Integer> getExercisesSeries() {
        return exercisesSeries;
    }
    //toodoo

}
