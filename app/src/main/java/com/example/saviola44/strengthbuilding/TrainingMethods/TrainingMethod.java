package com.example.saviola44.strengthbuilding.TrainingMethods;

import com.example.saviola44.strengthbuilding.Model.WorkoutExercise;
import com.example.saviola44.strengthbuilding.Model.TrainingPlan;

import java.util.List;

/**
 * Created by saviola44 on 24.05.16.
 */
public interface TrainingMethod {
    int getTrainingTag(); // id treningu pobierany z Constants
    int askAboutTAG(); //Jak wyswietlac cwiczenia w metodzie ShowAllExercisesActivity
    int nbOfSeriesForStrengthEx();
    List<WorkoutExercise> getWorkoutExercises(TrainingPlan plan,int trainingNb);
}
