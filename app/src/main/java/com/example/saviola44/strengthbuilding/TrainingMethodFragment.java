package com.example.saviola44.strengthbuilding;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.saviola44.strengthbuilding.Activities.AddTrainingsActivity;
import com.example.saviola44.strengthbuilding.TrainingMethods.RussianPowerliftingTraining;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by saviola44 on 23.05.16.
 */
public class TrainingMethodFragment extends Fragment{
    List<String> trainingMethods;
    ListView listView;
    public static final int strength =1;
    public static final int mass=2;
    public static final int custom=3;
    private int current=1;
    //dla Tab View wymagany jest domyślny konstruktor
    public TrainingMethodFragment() {
    }


    public void setMethod(int method){
        current = method;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(savedInstanceState!=null){
            current = savedInstanceState.getInt("current");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.training_method_fragm_layout, container, false);


        return v;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        listView = (ListView) getView().findViewById(R.id.selectMethodLV);
        StrengthBuilderApp app = StrengthBuilderApp.getInstance(getContext());
        switch (current){
            case strength:
                trainingMethods = app.getStrengthTrainings();
                break;
            case mass:
                trainingMethods = app.getMassTrainings();
                break;
            case custom:
                trainingMethods=new ArrayList<>();//app.getMassTrainings();
                break;
        }
        ListAdapter adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1, trainingMethods);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(current==strength){
                    switch (position){//treningi na sile

                        case 0: //ruskich
                            RussianPowerliftingTraining.generateTraining(getActivity());
                            break;
                        case 1: //animal
                            goToAddTrainingActivity(Constants.TrainingFromAnimalpak);
                            break;
                        case 2: //Rippetoe
                            Toast.makeText(getActivity(), "Funkcjonalnośc jeszcze nie dodana",Toast.LENGTH_LONG).show();
                            break;
                        case 3: //fbw na sile
                            goToAddTrainingActivity(Constants.StrengthFBW);
                            break;
                        case 4: //PPL na sile
                            goToAddTrainingActivity(Constants.StrengthPPL);
                            break;
                    }
                }
                else if(current==mass){
                    switch (position) {
                        case 0: //Split
                           //goToAddTrainingActivity(Constants.Split);
                            Toast.makeText(getActivity(), "Funkcjonalność jeszcze nie dodana", Toast.LENGTH_LONG).show();

                            break;
                        case 1: //FBW
                            goToAddTrainingActivity(Constants.MassFBW);
                            break;
                        case 2: //PPL
                            goToAddTrainingActivity(Constants.MassPPL);
                            break;
                    }

                }
            }
        });
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("current", current);
    }
    private void goToAddTrainingActivity(int TAG){
        Intent intent = new Intent(getContext(), AddTrainingsActivity.class);
        intent.putExtra(AddTrainingsActivity.TRAINING_TAG, TAG);
        startActivity(intent);
        getActivity().finish();
    }
    private void generateRussianTraining(){

    }
}
