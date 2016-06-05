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

import com.example.saviola44.strengthbuilding.R;
import com.example.saviola44.strengthbuilding.StrengthBuilderApp;
import com.example.saviola44.strengthbuilding.TrainingMethods.RussianPowerliftingTraining;

/**
 * Created by saviola44 on 31.05.16.
 */
public class GetMain1RMDialog extends DialogFragment {
    EditText squatET;
    EditText benchET;
    EditText dlET;


    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder createProjectAlert = new AlertDialog.Builder(getActivity());
        createProjectAlert.setTitle("Podaj swoje maksy");

        LayoutInflater inflater = getActivity().getLayoutInflater();
        createProjectAlert.setView(inflater.inflate(R.layout.get_max_weight_dialog_layout, null))
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        squatET = (EditText) getDialog().findViewById(R.id.squat_max_ET);
                        benchET = (EditText) getDialog().findViewById(R.id.wl_max_ET);
                        dlET = (EditText) getDialog().findViewById(R.id.dl_max_ET);
                        double squat=0;
                        double bench = 0;
                        double deadlift = 0;
                        if (squatET != null && benchET != null && dlET != null) {
                            String squat1RM = squatET.getText().toString();
                            String bench1RM = benchET.getText().toString();
                            String deadlift1RM = dlET.getText().toString();

                            if (squat1RM != null && bench1RM!= null && deadlift1RM!= null &&
                                    !squat1RM.equals("") && !bench1RM.equals("") && !deadlift1RM.equals("")) {
                                squat = Double.parseDouble(squat1RM);
                                bench = Double.parseDouble(bench1RM);
                                deadlift = Double.parseDouble(deadlift1RM);
                                RussianPowerliftingTraining.generateTrainingPlan(getContext(), squat, bench, deadlift);
                                getActivity().finish();
                            } else {
                                Toast.makeText(getContext(), "Pola nie mogą byc puste", Toast.LENGTH_LONG).show();
                            }
                        } else {
                            Log.d("dialog", "nie znaleziono widoku edit text playlist_name");
                        }
                    }
                })
                .setNegativeButton("Anuluj", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        getActivity().finish();
                        return;
                    }
                });



        return createProjectAlert.create();


    }
}
