package com.example.saviola44.strengthbuilding.TrainingMethods;

import com.example.saviola44.strengthbuilding.Model.WorkoutExercise;
import com.example.saviola44.strengthbuilding.Model.WorkoutExerciseInfo;
import com.example.saviola44.strengthbuilding.Model.Training;
import com.example.saviola44.strengthbuilding.Model.TrainingPlan;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by saviola44 on 24.05.16.
 */
public abstract class PPL implements TrainingMethod {
    @Override
    public List<WorkoutExercise> getWorkoutExercises(TrainingPlan plan, int trainingNb) {
        Training t = plan.getTrainings().get(trainingNb%3);
        List<WorkoutExercise> exerciseList = new ArrayList<>();
        List<WorkoutExerciseInfo> weiList = t.getExercises();
        for(int i=0; i<weiList.size(); i++){
            WorkoutExerciseInfo wei = weiList.get(i);
            if(wei.getExercise().isCompound()){
                for(int j=0; j<nbOfSeriesForStrengthEx(); j++){
                    WorkoutExercise we = new WorkoutExercise(wei.getExercise());
                    we.setReps(getStrengthExReps(trainingNb));
                    we.setWeight((int)getSeriesWeight(trainingNb, j, wei.getMaxWeight()));
                    exerciseList.add(we);
                }
            }
            else{
                for(int j=0; j<wei.getNumberOfSeries(); j++){

                    WorkoutExercise we = new WorkoutExercise(wei.getExercise());
                    we.setReps(getPumpExReps());
                    exerciseList.add(we);
                }
            }
        }
        return exerciseList;
    }
    //szablon metody
    public abstract int getPumpExReps();
    public abstract int getStrengthExReps(int trainingNb);
    public abstract double getSeriesWeight(int trainingNb, int seriesNb, double max);

}
