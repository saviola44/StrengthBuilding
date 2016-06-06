package com.example.saviola44.strengthbuilding;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.Switch;
import android.widget.Toast;

import com.example.saviola44.strengthbuilding.Database.DAO.ExerciseDAO;
import com.example.saviola44.strengthbuilding.Model.ExerciseInfo;
import com.example.saviola44.strengthbuilding.Model.WorkoutExercise;
import com.example.saviola44.strengthbuilding.Model.WorkoutExerciseInfo;
import com.example.saviola44.strengthbuilding.TrainingMethods.MassFBW;
import com.example.saviola44.strengthbuilding.TrainingMethods.MassPPL;
import com.example.saviola44.strengthbuilding.TrainingMethods.RippetoeTraining;
import com.example.saviola44.strengthbuilding.TrainingMethods.RussianPowerliftingTraining;
import com.example.saviola44.strengthbuilding.TrainingMethods.Split;
import com.example.saviola44.strengthbuilding.TrainingMethods.StrengthAnimalpakTraining;
import com.example.saviola44.strengthbuilding.TrainingMethods.StrengthFBW;
import com.example.saviola44.strengthbuilding.TrainingMethods.StrengthPPL;
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

    public static void createStrengthBuilderApp(Context context){
        app = new StrengthBuilderApp();
        app.initializeStrengthTrainings();
        app.initializeMassTrainings();
        SharedPreferences prefs = context.getSharedPreferences(Constants.SHARED_PREFS, Context.MODE_PRIVATE);
        boolean isPlanCreated = prefs.getBoolean(Constants.IS_PLAN_CREATED, false);
        if(isPlanCreated){
            Log.d("isPlanCreated", "true");
            app.parseTrainingPlan(context);
        }
        else{
            Log.d("isplanCreated", "false");
        }
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

    public static StrengthBuilderApp getInstance(Context context){
        if(app!=null){
            return app;
        }
        else{
            createStrengthBuilderApp(context);
            return app;
        }
    }

    public List<String> getStrengthTrainings() {
        return strengthTrainings;
    }

    public List<String> getMassTrainings() {
        return massTrainings;
    }

    public void saveTrainingPlan(TrainingPlan plan,Context context){
        currentTraining = 0;
        this.plan = plan;
        saveTrainingPlan(context);
    }

    public List<WorkoutExercise> getNextTraining(){
        if (plan!=null){
            return plan.getTrainingMethod().getWorkoutExercises(plan, currentTraining);
        }
        return null;
    }
    public int getCurrentTraining() {
        return currentTraining;
    }

    public void finishCurrentTraining() {
        currentTraining++;
    }

    public void saveTrainingPlan(Context context){
        SharedPreferences prefs = context.getSharedPreferences(Constants.SHARED_PREFS, Context.MODE_PRIVATE);
        currentTraining = 0;
        JSONObject trainingPlanObj = new JSONObject();
        try {
            trainingPlanObj.put("currentTraining", currentTraining);
            trainingPlanObj.put("method", plan.getTrainingMethod().getTrainingTag());
            JSONArray trainingsArray = new JSONArray();
            for(int i=0; i<plan.getTrainings().size(); i++){
                Training t= plan.getTrainings().get(i);
                JSONObject trainingObj = new JSONObject();
                trainingObj.put("name", t.getTrainingLabel());
                trainingObj.put("desc", t.getTrainingDescription());
                JSONArray exercisesArray = new JSONArray();
                for(int j=0; j<t.getExercises().size(); j++){
                    JSONObject exerciseObj = new JSONObject();
                    WorkoutExerciseInfo wei = t.getExercises().get(j);
                    exerciseObj.put("exId", wei.getExercise().getId());
                    exerciseObj.put("maxWeight", wei.getMaxWeight());
                    exerciseObj.put("series", wei.getNumberOfSeries());
                    exercisesArray.put(exerciseObj);
                }
                trainingObj.put("exercises", exercisesArray);
                trainingsArray.put(trainingObj);
            }
            trainingPlanObj.put("trainings", trainingsArray);
        } catch (JSONException e) {
            e.printStackTrace();
            Toast.makeText(context.getApplicationContext(), "Wystapiły błedy podczas " +
                    "zapisu planu treningowego", Toast.LENGTH_LONG).show();
        }
        prefs.edit().putString("trainigPlan", trainingPlanObj.toString()).commit();
        prefs.edit().putBoolean(Constants.IS_PLAN_CREATED, true).commit();
    }

    public void parseTrainingPlan(Context context){
        SharedPreferences prefs = context.getSharedPreferences(Constants.SHARED_PREFS, Context.MODE_PRIVATE);
        String trainingPlanStr= prefs.getString("trainigPlan", null);
        plan = new TrainingPlan();
        Log.d("elo", "jestem, tworze plan treningowy");
        if(trainingPlanStr!=null){
            try {
                JSONObject trainingPlanObj = new JSONObject(trainingPlanStr);
                currentTraining = trainingPlanObj.getInt("currentTraining");
                int trainingMethodTag = trainingPlanObj.getInt("method");
                createMethodFromTag(trainingMethodTag);
                JSONArray trainingsArray = trainingPlanObj.getJSONArray("trainings");
                for(int i=0; i< trainingsArray.length(); i++){
                    JSONObject trainingObj = trainingsArray.getJSONObject(i);
                    Training training = new Training();

                    String name = trainingObj.getString("name");
                    String desc = trainingObj.getString("desc");
                    training.setTrainingLabel(name);
                    training.setTrainingDescription(desc);

                    JSONArray exercisesArray = trainingObj.getJSONArray("exercises");
                    for(int j=0; j<exercisesArray.length(); j++){
                        JSONObject exerciseObj = exercisesArray.getJSONObject(j);
                        long id = exerciseObj.getLong("exId");
                        double maxWeight = exerciseObj.getDouble("maxWeight");
                        int series = exerciseObj.getInt("series");
                        ExerciseDAO exerciseDAO = new ExerciseDAO(context);
                        ExerciseInfo ei = exerciseDAO.getElementById(id);
                        WorkoutExerciseInfo wei = new WorkoutExerciseInfo(series, ei, maxWeight);
                        training.getExercises().add(wei);
                    }
                    plan.getTrainings().add(training);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
    void createMethodFromTag(int TAG){
        switch (TAG){
            case Constants.TrainingFromAnimalpak :
                plan.setTrainingMethod(new StrengthAnimalpakTraining());
                break;
            case Constants.StrengthFBW :
                plan.setTrainingMethod(new StrengthFBW());
                break;
            case Constants.StrengthPPL:
                plan.setTrainingMethod(new StrengthPPL());
                break;
            case Constants.Split:
                plan.setTrainingMethod(new Split());
                break;
            case Constants.RussianPowerliftingTraining:
                plan.setTrainingMethod(new RussianPowerliftingTraining());
                break;
            case Constants.RippetoeTraining:
                plan.setTrainingMethod(new RippetoeTraining());
                break;
            case Constants.MassPPL:
                plan.setTrainingMethod(new MassPPL());
                break;
            case Constants.MassFBW:
                plan.setTrainingMethod(new MassFBW());
                break;
        }
    }
}
