package com.example.saviola44.strengthbuilding.Dialogs;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.saviola44.strengthbuilding.Database.DAO.ExerciseDAO;
import com.example.saviola44.strengthbuilding.Model.ExerciseInfo;
import com.example.saviola44.strengthbuilding.R;

import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by saviola44 on 2016-06-10.
 */
public class AddNewExDialog extends DialogFragment implements AdapterView.OnItemSelectedListener {
    EditText exName;
    Spinner workedMuscle;
    RadioGroup isCompoundRG;
    AddExercise handler;
    public boolean isCompound = true;
    long muscleLong=1;
    static ArrayList<String> muscleParts = new ArrayList<>();
    static {
        muscleParts.add("Klata");
        muscleParts.add("Nogi");
        muscleParts.add("Plecy");
        muscleParts.add("Biceps");
        muscleParts.add("Triceps");
        muscleParts.add("Barki");
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        muscleLong = (long) (position+1);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }



    public interface AddExercise{
        void addExercise(ExerciseInfo e);
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder createProjectAlert = new AlertDialog.Builder(getActivity());
        createProjectAlert.setTitle("Nowy trening");
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View v = inflater.inflate(R.layout.add_new_ex_layout, null);
        exName = (EditText) v.findViewById(R.id.ex_name);
        workedMuscle = (Spinner)v.findViewById(R.id.workedMuscleSpinner);
        isCompoundRG = (RadioGroup) v.findViewById(R.id.ex_type_RG);

        workedMuscle.setOnItemSelectedListener(AddNewExDialog.this);
        isCompoundRG.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if(checkedId==R.id.isolate){
                    isCompound=false;
                }
                else {
                    isCompound=true;
                }
            }
        });


        ArrayAdapter<String> spinAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, muscleParts);
        spinAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        workedMuscle.setAdapter(spinAdapter);
        workedMuscle.setSelection(0);

        createProjectAlert.setView(v)
                .setPositiveButton("Dodaj", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        handler = (AddExercise) getActivity();
                        if(exName!=null && exName.getText().toString()!=null && !exName.getText().toString().equals("")){
                            String name = exName.getText().toString();
                            Calendar cal = Calendar.getInstance();
                            ExerciseInfo ex = new ExerciseInfo(cal.getTimeInMillis(), name, muscleLong, isCompound);
                            ExerciseDAO dao = new ExerciseDAO(getContext());
                            dao.saveElement(ex);
                            handler.addExercise(ex);
                        }
                        else {
                            Toast.makeText(getActivity(), "Nazwa cwiczenia nie moze byc pusta", Toast.LENGTH_LONG).show();
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
