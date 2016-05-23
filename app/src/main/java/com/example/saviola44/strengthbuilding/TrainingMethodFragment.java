package com.example.saviola44.strengthbuilding;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by saviola44 on 23.05.16.
 */
public class TrainingMethodFragment extends Fragment{
    //dla Tab View wymagany jest domyślny konstruktor
    public TrainingMethodFragment() {
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.training_method_fragm_layout, container, false);
    }

}
