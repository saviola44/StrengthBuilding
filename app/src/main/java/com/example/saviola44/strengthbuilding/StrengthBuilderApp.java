package com.example.saviola44.strengthbuilding;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.example.saviola44.strengthbuilding.Model.WorkoutExercise;
import com.example.saviola44.strengthbuilding.Model.WorkoutExerciseInfo;
import com.example.saviola44.strengthbuilding.TrainingMethods.TrainingMethod;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

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
    /*
    do zapamietania
    -currenttraining //shared prefs
    -trziningplan
            -getTrainingTAG id metody treningowej //shared prefs
            -lista trenignow Training //DB id
                -nazwa DB String
                -opis DB String
                -workoutexercisesinfo lista
                    -ExerciseInfo exercise; //id
                    int numberOfSeries;
                     double maxWeight;
     */

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

    public void saveTrainingPlan(Context context){
        SharedPreferences prefs = context.getSharedPreferences(Constants.SHARED_PREFS, Context.MODE_PRIVATE);
        currentTraining = 0;
        prefs.edit().putInt("currentTraining", currentTraining);
        prefs.edit().putInt("method", plan.getTrainingMethod().getTrainingTag());
        JSONObject trainingsObj = new JSONObject();
        try {

            JSONArray trainingsArray = new JSONArray();
            for(int i=0; i<plan.getTrainings().size(); i++){
                JSONObject trainingObj = new JSONObject();
                Training t= plan.getTrainings().get(i);
                for(int j=0; j<t.getExercises().size(); i++){
                    WorkoutExerciseInfo wei = t.getExercises().get(j);
                    trainingObj.put("exId", wei.getExercise().getId());
                    trainingObj.put("maxWeight", wei.getMaxWeight());
                    trainingObj.put("series", wei.getNumberOfSeries());
                }
                trainingsArray.put(trainingObj);
            }
            trainingsObj.put("trainings", trainingsArray);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        prefs.edit().putString("trainigs", trainingsObj.toString()).commit();
    }
}
