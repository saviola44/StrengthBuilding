package com.example.saviola44.strengthbuilding.Dialogs;

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
import com.example.saviola44.strengthbuilding.R;

/**
 * Created by saviola44 on 25.05.16.
 */
public class GetNumberDialog extends DialogFragment {
    EditText numberET;
    SetMaxWeightOrSeries handler;
    public static final int ASK_ABOUT_1RM = 1;
    public static final int ASK_ABOUT_SERIES = 2;
    int askMode = 1;

    public interface SetMaxWeightOrSeries{
        void setMaxWeight(double weight);
        void setNbOfSeries(int series);
        void cancel();
    }


    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder createProjectAlert = new AlertDialog.Builder(getActivity());
        final int askMode = getArguments().getInt("mode");

        //final String alert = getArguments().getString("alert");
        if(askMode==ASK_ABOUT_SERIES){
            createProjectAlert.setTitle("Wprowadź liczbe serii");
        }
        else{
            createProjectAlert.setTitle("Podaj swój ciężar 1RM");
        }

        LayoutInflater inflater = getActivity().getLayoutInflater();
        createProjectAlert.setView(inflater.inflate(R.layout.set_get_number_dialog_layout, null))
                .setPositiveButton("Dodaj", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        numberET = (EditText) getDialog().findViewById(R.id.maxWeightET);
                        if (numberET != null) {
                            String numberStr = numberET.getText().toString();
                            if (numberStr != null && !numberStr.equals("")) {
                                double nb = Double.parseDouble(numberStr);
                                handler = (SetMaxWeightOrSeries) getActivity();
                                if(askMode==ASK_ABOUT_1RM){
                                    handler.setMaxWeight(nb);
                                }
                                else{
                                    handler.setNbOfSeries((int)nb);
                                }

                            } else {
                                if(askMode==ASK_ABOUT_1RM){
                                    Toast.makeText(getContext(), "Musisz podac swój ciezar 1RM", Toast.LENGTH_LONG).show();
                                }
                                else{
                                    Toast.makeText(getContext(), "niepoprawne dane", Toast.LENGTH_LONG).show();
                                }
                            }
                        } else {
                            Log.d("dialog", "nie znaleziono widoku edit text playlist_name");
                        }
                    }
                })
                .setNegativeButton("Anuluj", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        handler = (SetMaxWeightOrSeries) getActivity();
                        handler.cancel();
                        return;
                    }
                });
        return createProjectAlert.create();
    }
}
