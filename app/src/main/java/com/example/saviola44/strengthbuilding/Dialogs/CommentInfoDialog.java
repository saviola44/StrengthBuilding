package com.example.saviola44.strengthbuilding.Dialogs;

import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.saviola44.strengthbuilding.R;
import com.example.saviola44.strengthbuilding.Training;

/**
 * Created by saviola44 on 28.05.16.
 */
public class CommentInfoDialog extends DialogFragment {



    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        TextView comment;
        AlertDialog.Builder createProjectAlert = new AlertDialog.Builder(getActivity());
        createProjectAlert.setTitle("Komentarz");
        String com = (String) getArguments().get("com");

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View v = inflater.inflate(R.layout.comment_info_dialog_layout, null);
        comment = (TextView) v.findViewById(R.id.dialog_comment_info);
        comment.setText(com);
        createProjectAlert.setView(v)
                .setPositiveButton("Powrót", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                    }
                });

        return createProjectAlert.create();


    }
}
