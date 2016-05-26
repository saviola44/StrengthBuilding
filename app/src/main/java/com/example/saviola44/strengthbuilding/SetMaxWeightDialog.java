package com.example.saviola44.strengthbuilding;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.widget.EditText;
import android.widget.Toast;

import com.example.saviola44.strengthbuilding.Model.WorkoutExerciseInfo;

/**
 * Created by saviola44 on 25.05.16.
 */
public class SetMaxWeightDialog extends DialogFragment {
    EditText maxWeightET;
    SetMaxWeight handler;
    public interface SetMaxWeight{
         void setMaxWeight(double weight);
        void setNbOfSeries(int series);
         void cancel();
    }


    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder createProjectAlert = new AlertDialog.Builder(getActivity());
        String title = getArguments().getString("title");
        //final String hint = getArguments().getString("hint");
        final String alert = getArguments().getString("alert");
        createProjectAlert.setTitle(title);
        LayoutInflater inflater = getActivity().getLayoutInflater();
        createProjectAlert.setView(inflater.inflate(R.layout.set_max_weight_dialog_layout, null))
                .setPositiveButton("Dodaj", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        maxWeightET = (EditText) getDialog().findViewById(R.id.maxWeightET);
                        //maxWeightET.setHint("elo");
                        if (maxWeightET != null) {
                            String weightStr = maxWeightET.getText().toString();
                            if (weightStr != null && !weightStr.equals("")) {
                                double max = Double.parseDouble(maxWeightET.getText().toString());

                                handler = (SetMaxWeight) getActivity();
                                handler.setMaxWeight(Double.parseDouble(weightStr));
                            } else {
                                Toast.makeText(getContext(), alert, Toast.LENGTH_LONG).show();
                            }
                        } else {
                            Log.d("dialog", "nie znaleziono widoku edit text playlist_name");
                        }
                    }
                })
                .setNegativeButton("Anuluj", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        handler = (SetMaxWeight) getActivity();
                        handler.cancel();
                        return;
                    }
                });



        return createProjectAlert.create();


    }
}
