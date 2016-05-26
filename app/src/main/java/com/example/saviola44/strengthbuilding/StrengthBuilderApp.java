package com.example.saviola44.strengthbuilding;

import com.example.saviola44.strengthbuilding.Model.WorkoutExercise;
import com.example.saviola44.strengthbuilding.TrainingMethods.TrainingMethod;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by saviola44 on 23.05.16.
 */
public class StrengthBuilderApp {
    private static StrengthBuilderApp app = null;
    private List<String> strengthTrainings;
    private List<String> massTrainings;
    TrainingPlan plan;
    private int currentTraining; //ile zostalo juz zrobionych treningow danego planu treningowego
    private StrengthBuilderApp(){

    }

    public TrainingPlan getPlan() {
        return plan;
    }

    public static void createStrengthBuilderApp(){
        app = new StrengthBuilderApp();
        //odczytaj plan
        //todoo
        app.initializeStrengthTrainings();
        app.initializeMassTrainings();
    }

    private void initializeStrengthTrainings(){
        strengthTrainings = new ArrayList<>();
        strengthTrainings.add("Trening rosyjskich ciężarowców");
        strengthTrainings.add("siłowy program z forum animalpak wg R. Słodkiewicza");
        strengthTrainings.add("Trening Rippetoe");
        strengthTrainings.add("FBW na siłe");
        strengthTrainings.add("PPL na siłe");
    }

    private void initializeMassTrainings(){
        massTrainings = new ArrayList<>();
        massTrainings.add("Split");
        massTrainings.add("FBW");
        massTrainings.add("PPL");
    }

    public static StrengthBuilderApp getInstance(){
        if(app!=null){
            return app;
        }
        else{
            createStrengthBuilderApp();
            return app;
        }
    }

    public List<String> getStrengthTrainings() {
        return strengthTrainings;
    }

    public List<String> getMassTrainings() {
        return massTrainings;
    }

    public void saveTrainingPlan(TrainingPlan plan){
        //zapisz w bazie danych
        currentTraining = 0;
        this.plan = plan;
    }

    public List<WorkoutExercise> getNextTraining(){
        if (plan!=null){
            return plan.getTrainingMethod().getWorkoutExercises(plan);
        }
        return null;
    }
    public int getCurrentTraining() {
        return currentTraining;
    }

    public void setCurrentTraining(int currentTraining) {
        this.currentTraining = currentTraining;
    }
}
