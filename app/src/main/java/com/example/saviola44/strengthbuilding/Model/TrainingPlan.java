package com.example.saviola44.strengthbuilding.Model;

import com.example.saviola44.strengthbuilding.TrainingMethods.TrainingMethod;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by saviola44 on 24.05.16.
 */
public class TrainingPlan {
    TrainingMethod trainingMethod; //strategia
    List<Training> trainings; //trenigi np 3-5 rozne robione cyklicznie co tydzien

    public TrainingPlan() {
        trainings = new ArrayList<>();
    }

    public TrainingMethod getTrainingMethod() {
        return trainingMethod;
    }

    public void setTrainingMethod(TrainingMethod trainingMethod) {
        this.trainingMethod = trainingMethod;
    }



    public List<Training> getTrainings() {
        return trainings;
    }

    public void setTrainings(List<Training> trainings) {
        this.trainings = trainings;
    }
}
