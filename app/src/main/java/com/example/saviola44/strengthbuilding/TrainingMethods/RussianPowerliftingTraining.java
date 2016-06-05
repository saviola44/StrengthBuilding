package com.example.saviola44.strengthbuilding.TrainingMethods;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.text.style.TtsSpan;

import com.example.saviola44.strengthbuilding.Activities.AddTrainingActivity;
import com.example.saviola44.strengthbuilding.Activities.ShowAllExercisesActivity;
import com.example.saviola44.strengthbuilding.Constants;
import com.example.saviola44.strengthbuilding.Database.DAO.DAO;
import com.example.saviola44.strengthbuilding.Database.DAO.ExerciseDAO;
import com.example.saviola44.strengthbuilding.Dialogs.GetMain1RMDialog;
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
    int[] series = new int[]{6,6,6,6,5,4,3,2,1};
    int[]reps = new int[]{3,4,5,6,5,4,3,2,1};

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
        int week = trainingNb/3;
        Training t = plan.getTrainings().get(0);
        List<WorkoutExercise> exList = new ArrayList<>();
        switch (day){
            case 0:{
                WorkoutExerciseInfo wei1 = t.getExercises().get(0);
                WorkoutExerciseInfo wei2 = t.getExercises().get(1);
                for(int i=0; i<series[week]; i++){
                    WorkoutExercise we = new WorkoutExercise(wei1.getExercise());
                    we.setReps(reps[week]);
                    we.setWeight((int) (wei1.getMaxWeight() * weightPercentage[week]));
                    exList.add(we);
                }
                for(int i=0; i<6; i++){
                    WorkoutExercise we = new WorkoutExercise(wei2.getExercise());
                    we.setReps(2);
                    we.setWeight((int) (wei2.getMaxWeight() * weightPercentage[0]));
                    exList.add(we);
                }
                break;
            }
            case 1:{
                WorkoutExerciseInfo wei = t.getExercises().get(0);
                for(int i=0; i<series[week]; i++){
                    WorkoutExercise we = new WorkoutExercise(wei.getExercise());
                    we.setReps(reps[week]);
                    we.setWeight((int) (wei.getMaxWeight() * weightPercentage[week]));
                    exList.add(we);
                }
                break;
            }
            case 2:{
                WorkoutExerciseInfo wei1 = t.getExercises().get(0);
                WorkoutExerciseInfo wei2 = t.getExercises().get(1);
                for(int i=0; i<series[week]; i++){
                    WorkoutExercise we = new WorkoutExercise(wei1.getExercise());
                    we.setReps(reps[week]);
                    we.setWeight((int)(wei1.getMaxWeight()*weightPercentage[week]));
                    exList.add(we);
                }
                for(int i=0; i<6; i++){
                    WorkoutExercise we = new WorkoutExercise(wei2.getExercise());
                    we.setReps(2);
                    we.setWeight((int) (wei2.getMaxWeight() * weightPercentage[0]));
                    exList.add(we);
                }
                break;
            }
        }
        return exList;
    }

    @Override
    public String toString() {
        return label;
    }


    public static void generateTraining(FragmentActivity fragment){
        GetMain1RMDialog dialog = new GetMain1RMDialog();
        dialog.show(fragment.getSupportFragmentManager(), "createGet1RMDialog");

    }

    public static void generateTrainingPlan(Context context, double squat, double bench, double deadlift){
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

        mondayEx.add(new WorkoutExerciseInfo(6,dao.getElementById(1),bench));//bench press
        mondayEx.add(new WorkoutExerciseInfo(6,dao.getElementById(13),squat)); //squat
        wednesdayEx.add(new WorkoutExerciseInfo(6,dao.getElementById(23),deadlift)); //deadlift
        fridayEx.add(new WorkoutExerciseInfo(6,dao.getElementById(13),squat));
        fridayEx.add(new WorkoutExerciseInfo(6,dao.getElementById(1),bench));

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
        ((FragmentActivity) context).finish();
    }
}
