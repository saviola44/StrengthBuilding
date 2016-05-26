package com.example.saviola44.strengthbuilding.TrainingMethods;

import com.example.saviola44.strengthbuilding.Activities.AddTrainingActivity;
import com.example.saviola44.strengthbuilding.Activities.MainActivity;
import com.example.saviola44.strengthbuilding.Activities.ShowAllExercisesActivity;
import com.example.saviola44.strengthbuilding.Constants;
import com.example.saviola44.strengthbuilding.Model.ExerciseInfo;
import com.example.saviola44.strengthbuilding.Model.WorkoutExercise;
import com.example.saviola44.strengthbuilding.Model.WorkoutExerciseInfo;
import com.example.saviola44.strengthbuilding.StrengthBuilderApp;
import com.example.saviola44.strengthbuilding.Training;
import com.example.saviola44.strengthbuilding.TrainingMethods.TrainingMethod;
import com.example.saviola44.strengthbuilding.TrainingPlan;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by saviola44 on 24.05.16.
 */
public class StrengthAnimalpakTraining implements TrainingMethod {
    private static final int TAG = Constants.TrainingFromAnimalpak;
    private static final String label = "Siłowy program z forum animalpak wg R. Słodkiewicza";
    static int[] weightPercentage = {
            80,80, 80, 80, 80, 80, 80, 80, 80
    };
    static int[] weightSeries = {
            6,6,6,6,6,6,6,6,6
    };
    static int[] reps = {
        2,3,2, 4,2,5, 2, 6, 2
    };


    @Override
    public int getTrainingTag() {
        return TAG;
    }

    //zmien nazwe na mode
    @Override
    public int askAboutTAG() {
        return ShowAllExercisesActivity.askMaxWeightAfterClickTAG;
    }

    @Override
    public int nbOfSeriesForStrengthEx() {
        return 6;
    }

    @Override
    public List<WorkoutExercise> getWorkoutExercises(TrainingPlan plan, int trainingNb) {
        List<Training> trainings = plan.getTrainings();
        //np 4treningi size=4
        //np cur = 5 done = 0,1,2,3,4
        //Indextren = cur-(cur/size)*size
        int x = trainingNb/trainings.size();
        x*=trainings.size();
        int trenIndex = (trainingNb-x);
        Training training = trainings.get(trenIndex);
        List<WorkoutExerciseInfo> exInfo = training.getExercises();
        List<WorkoutExercise> preparedTrenEx = new ArrayList<>();
        for(int i=0; i<exInfo.size(); i++){
            WorkoutExerciseInfo wei = exInfo.get(i);
            for(int j=0; j<wei.getNumberOfSeries(); j++){
                if(wei.getExercise().isCompound()){
                    WorkoutExercise we = new WorkoutExercise(wei.getExercise());
                    we.setWeight((int) (wei.getMaxWeight()*weightPercentage[trainingNb])/100);
                    we.setReps(reps[trainingNb]);
                    preparedTrenEx.add(we);
                }
                else {
                    WorkoutExercise we = new WorkoutExercise(wei.getExercise());
                    we.setWeight(0);
                    we.setReps(10);
                    preparedTrenEx.add(we);
                }
            }
        }
        return preparedTrenEx;

    }

    @Override
    public String toString() {
        return label;
    }
}
