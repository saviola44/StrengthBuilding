package com.example.saviola44.strengthbuilding.TrainingMethods;

import android.content.Context;

import com.example.saviola44.strengthbuilding.Activities.AddTrainingActivity;
import com.example.saviola44.strengthbuilding.Activities.ShowAllExercisesActivity;
import com.example.saviola44.strengthbuilding.Constants;
import com.example.saviola44.strengthbuilding.Database.DAO.DAO;
import com.example.saviola44.strengthbuilding.Database.DAO.ExerciseDAO;
import com.example.saviola44.strengthbuilding.Model.ExerciseInfo;
import com.example.saviola44.strengthbuilding.Model.WorkoutExercise;
import com.example.saviola44.strengthbuilding.Model.WorkoutExerciseInfo;
import com.example.saviola44.strengthbuilding.StrengthBuilderApp;
import com.example.saviola44.strengthbuilding.Training;
import com.example.saviola44.strengthbuilding.TrainingPlan;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by saviola44 on 24.05.16.
 */
public class RussianPowerliftingTraining implements TrainingMethod {
    private static final int TAG = Constants.RussianPowerliftingTraining;
    private static final String label = "Trening rosyjskich ciężarowców";


    double[] weightPercentage = new double[]{0.80,0.80, 0.80, 0.80, 0.85, 0.90, 0.95, 1.00, 1.05};

    @Override
    public int getTrainingTag() {
        return TAG;
    }

    @Override
    public int askAboutTAG() {
        return ShowAllExercisesActivity.askMaxWeightAfterClickTAG;
    }

    @Override
    public int nbOfSeriesForStrengthEx() {
        return 6;
    }

    @Override
    public List<WorkoutExercise> getWorkoutExercises(TrainingPlan plan,int trainingNb) {
        int day = trainingNb%3;
        switch (day){
            case 0:{

            }
            case 1:{
                break;
            }
            case 2:{
                break;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return label;
    }

    public static void generateTraining(Context context){
        //generujemy plan treningowy, na niego sklada sie metoda treningowa(strategia) oraz 3 treningi
        TrainingPlan trainingPlan = new TrainingPlan();
        TrainingMethod trainingMethod = new RussianPowerliftingTraining();
        //trenujemy 3 dni w tygodniu
        List<Training> trainings = new ArrayList<>();

        trainingPlan.setTrainingMethod(trainingMethod);
        DAO<ExerciseInfo> dao = new ExerciseDAO(context);

        ArrayList<WorkoutExerciseInfo> mondayEx = new ArrayList<>();
        ArrayList<WorkoutExerciseInfo> wednesdayEx = new ArrayList<>();
        ArrayList<WorkoutExerciseInfo> fridayEx = new ArrayList<>();

        mondayEx.add(new WorkoutExerciseInfo(dao.getElementById(1)));//bench press
        mondayEx.add(new WorkoutExerciseInfo(dao.getElementById(13))); //squat
        wednesdayEx.add(new WorkoutExerciseInfo(dao.getElementById(23))); //deadlift
        fridayEx.add(new WorkoutExerciseInfo(dao.getElementById(13)));
        fridayEx.add(new WorkoutExerciseInfo(dao.getElementById(1)));

        Training monday = new Training();
        Training wednesday = new Training();
        Training friday = new Training();

        monday.setTrainingLabel("Trening 1");
        monday.setTrainingDescription("Klatka ciężko,, przysiad lekko");
        monday.setExercises(mondayEx);
        wednesday.setTrainingLabel("Trening 2");
        wednesday.setTrainingDescription("Ciężkie ciągi");
        wednesday.setExercises(wednesdayEx);
        friday.setTrainingLabel("Trening 3");
        friday.setTrainingDescription("Ciężko siady, klatka lekko");
        friday.setExercises(fridayEx);

        trainings.add(monday);
        trainings.add(wednesday);
        trainings.add(friday);

        trainingPlan.setTrainings(trainings);

        StrengthBuilderApp app = StrengthBuilderApp.getInstance(context);
        app.saveTrainingPlan(trainingPlan, context);
    }
}
