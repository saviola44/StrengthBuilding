package com.example.saviola44.strengthbuilding;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Switch;

import com.example.saviola44.strengthbuilding.Activities.AddTrainingActivity;

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
        StrengthBuilderApp app = StrengthBuilderApp.getInstance();
        switch (current){
            case strength:
                trainingMethods = app.getStrengthTrainings();
                break;
            case mass:
                trainingMethods = app.getMassTrainings();
                break;
            case custom:
                trainingMethods=app.getMassTrainings();
                break;
        }
        if(app==null) Log.d("elo", "jest nullem");
        else Log.d("elo", "nie jest nullem");
        ListAdapter adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1, trainingMethods);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(current==strength){
                    switch (position){
                        case 0:
                            //wygeneruj trening
                            getActivity().finish();
                            break;
                        default:
                            Intent intent = new Intent(getContext(), AddTrainingActivity.class);
                            startActivity(intent);
                            getActivity().finish();
                    }
                }
                else{
                    Intent intent = new Intent(getContext(), AddTrainingActivity.class);
                    startActivity(intent);
                    getActivity().finish();
                }

            }
        });
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("current", current);
    }
}