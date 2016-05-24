package com.example.saviola44.strengthbuilding;

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
    private StrengthBuilderApp(){

    }

    public static void createStrengthBuilderApp(){
        app = new StrengthBuilderApp();
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
}
