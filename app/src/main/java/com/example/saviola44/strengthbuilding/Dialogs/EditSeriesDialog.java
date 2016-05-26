package com.example.saviola44.strengthbuilding.Dialogs;

import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import com.example.saviola44.strengthbuilding.R;
import com.example.saviola44.strengthbuilding.Training;

/**
 * Created by saviola44 on 26.05.16.
 */
public class EditSeriesDialog extends DialogFragment {
    EditText editReps;
    EditText editWeight;
    EditText comment;

    public interface EditSeries{
        void editReps(int pos, int reps);
        void editWeight(int pos, double weight);
        void editComment(int pos, String comment);
        void deleteSeries(int pos);
    }


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder createProjectAlert = new AlertDialog.Builder(getActivity());
        createProjectAlert.setTitle("Edytuj serie");
        LayoutInflater inflater = getActivity().getLayoutInflater();
        final int pos = getArguments().getInt("pos");
        double weight = getArguments().getDouble("weight");
        int reps = getArguments().getInt("reps");
        final String com = getArguments().getString("comment", null);

        View v = inflater.inflate(R.layout.edit_series_dialog_layout, null);
        editWeight = ((EditText)v.findViewById(R.id.edit_weight_ET));
        editReps = ((EditText)v.findViewById(R.id.edit_reps_ET));
        comment = ((EditText)v.findViewById(R.id.commentET));

        editWeight.setHint(""+weight);
        editReps.setHint("" + reps);
        if(com!=null){
            comment.setHint(com);
            comment.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    comment.setText(com);

                }
            });
        }
        createProjectAlert.setView(v)
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int id) {
                editReps = (EditText) getDialog().findViewById(R.id.edit_reps_ET);
                editWeight = (EditText) getDialog().findViewById(R.id.edit_weight_ET);
                comment = (EditText) getDialog().findViewById(R.id.commentET);
                EditSeries handler = (EditSeries) getActivity();
                if (editReps != null && editWeight != null && comment != null) {
                    if (editReps.getText().toString() != null && !editReps.getText().toString().equals("")) {
                        handler.editReps(pos, Integer.parseInt(editReps.getText().toString()));
                    }
                    if (editWeight.getText().toString() != null && !editWeight.getText().toString().equals("")) {
                        handler.editWeight(pos, Double.parseDouble(editWeight.getText().toString()));
                    }
                    if (comment.getText().toString() != null && !comment.getText().toString().equals("")) {
                        handler.editComment(pos, comment.getText().toString());
                    }
                } else {
                    //Toast.makeText(getContext(), "edittext jest nulem", Toast.LENGTH_LONG).show();
                    Log.d("dialog", "nie znaleziono widoków edit text");
                }
            }
        })
                .setNegativeButton("Anuluj", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        return;
                    }
                })
                .setNeutralButton("Usuń serie", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        EditSeries handler = (EditSeries) getActivity();
                        handler.deleteSeries(pos);
                    }
                });

        return createProjectAlert.create();
    }


}
