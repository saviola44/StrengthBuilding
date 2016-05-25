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
import android.widget.TextView;

/**
 * Created by saviola44 on 25.05.16.
 */
public class AddNewTrainingDialog extends DialogFragment {
    EditText trainingNameET;
    EditText trainingDescET;
    AddTrainingInterface handler;
    public interface AddTrainingInterface{
        void addTraining(Training t);
    }



    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder createProjectAlert = new AlertDialog.Builder(getActivity());
        createProjectAlert.setTitle("Nowy trening");

        LayoutInflater inflater = getActivity().getLayoutInflater();

        createProjectAlert.setView(inflater.inflate(R.layout.create_new_training_dialog_layout, null))
                .setPositiveButton("Dodaj", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        trainingNameET = (EditText) getDialog().findViewById(R.id.trainingNameET);
                        trainingDescET = (EditText) getDialog().findViewById(R.id.trainingDescET);
                        if(trainingNameET!=null && trainingDescET!=null){
                            if(trainingNameET.getText().toString()!=null){
                                Training training = new Training();
                                training.setTrainingLabel(trainingNameET.getText().toString());
                                if(trainingDescET.getText().toString()!=null){
                                    training.setTrainingDescription(trainingDescET.getText().toString());
                                }
                                else {
                                    training.setTrainingDescription("Brak opisu");
                                }
                                handler = (AddTrainingInterface) getActivity();
                                handler.addTraining(training);
                            }

                        }else{
                            //Toast.makeText(getContext(), "edittext jest nulem", Toast.LENGTH_LONG).show();
                            Log.d("dialog", "nie znaleziono widoku edit text playlist_name");
                        }
                    }
                })
                .setNegativeButton("Anuluj", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        return;
                    }
                });

        return createProjectAlert.create();


    }
}
