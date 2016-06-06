package com.example.saviola44.strengthbuilding.Adapters;

import android.app.FragmentManager;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.saviola44.strengthbuilding.Activities.DoTrainingActivity;
import com.example.saviola44.strengthbuilding.Dialogs.AddNewTrainingDialog;
import com.example.saviola44.strengthbuilding.Dialogs.EditSeriesDialog;
import com.example.saviola44.strengthbuilding.Model.Option;
import com.example.saviola44.strengthbuilding.Model.WorkoutExercise;
import com.example.saviola44.strengthbuilding.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by saviola44 on 26.05.16.
 */
public class DoTrainingAdapter extends ArrayAdapter {
    List<WorkoutExercise> exercises;
    List<Boolean> doneEx;
    Context context;
    public DoTrainingAdapter(Context context, int resource, List<WorkoutExercise> exercises,  List<Boolean> doneEx) {
        super(context, resource);
        this.context = context;
        this.exercises = exercises;
        this.doneEx = doneEx;
    }

    public void setDoneEx(int position){
        doneEx.set(position, true);
    }
    public void addExercise(WorkoutExercise we){
        doneEx.add(false);
        exercises.add(we);
    }
    @Override
    public View getView(final int position, final View convertView, ViewGroup parent) {
        View row = convertView;

        DataHandler handler;
        //if(convertView==null){ //nie mozna skorzystac z tej optymalizacji bo gubi kolory przy scrollowaniu
            LayoutInflater inflater = (LayoutInflater)getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(R.layout.do_training_row_layout, parent, false);
        if(doneEx.get(position)){
            row.setBackgroundColor(Color.rgb(56, 175, 68));

        }
            handler = new DataHandler();
            handler.nameTV = (TextView) row.findViewById(R.id.doTrainingExNameTV);
            handler.repWeightTV = (TextView) row.findViewById(R.id.doTrainingExTV);
            handler.editIV = (ImageView) row.findViewById(R.id.editWorkoutIV);

            handler.editIV.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //Toast.makeText(convertView.getContext(), "bedzie", Toast.LENGTH_LONG).show();
                    EditSeriesDialog dialog = new EditSeriesDialog();
                    Bundle bundle = new Bundle();
                    bundle.putInt("pos", position);
                    bundle.putDouble("weight", (double)exercises.get(position).getWeight());
                    bundle.putInt("reps", exercises.get(position).getReps());
                    if(exercises.get(position).getComment()!=null){
                        bundle.putString("comment", exercises.get(position).getComment());
                    }
                    dialog.setArguments(bundle);
                    dialog.show(((AppCompatActivity)context).getFragmentManager(), "Edytuj Serię");
                }
            });

            row.setTag(handler);
       // }
        //else{
           // handler = (DataHandler) row.getTag();
      //  }
        WorkoutExercise we = (WorkoutExercise) getItem(position);
        handler.nameTV.setText(we.getExercise().getNazwa());
        if(we.getWeight()==0) {
            handler.repWeightTV.setText(we.getReps() + "powt");
        }
        else{
            handler.repWeightTV.setText(we.getWeight() + "kg x " + we.getReps() + "powt");
        }
        return row;
    }

    @Override
    public Object getItem(int position) {
        return exercises.get(position);
    }

    @Override
    public int getCount() {
        return exercises.size();
    }

    static class DataHandler{
        TextView nameTV;
        TextView repWeightTV;
        ImageView editIV;
    }
}
