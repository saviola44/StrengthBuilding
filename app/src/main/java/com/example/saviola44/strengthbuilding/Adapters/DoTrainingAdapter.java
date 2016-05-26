package com.example.saviola44.strengthbuilding.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.saviola44.strengthbuilding.Model.Option;
import com.example.saviola44.strengthbuilding.Model.WorkoutExercise;
import com.example.saviola44.strengthbuilding.R;

import java.util.List;

/**
 * Created by saviola44 on 26.05.16.
 */
public class DoTrainingAdapter extends ArrayAdapter {
    List<WorkoutExercise> exercises;
    public DoTrainingAdapter(Context context, int resource, List<WorkoutExercise> exercises) {
        super(context, resource);
        this.exercises = exercises;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        DataHandler handler;
        if(convertView==null){
            LayoutInflater inflater = (LayoutInflater)getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(R.layout.do_training_row_layout, parent, false);
            handler = new DataHandler();
            handler.nameTV = (TextView) row.findViewById(R.id.doTrainingExNameTV);
            handler.repWeightTV = (TextView) row.findViewById(R.id.doTrainingExTV);
            handler.editIV = (ImageView) row.findViewById(R.id.editWorkoutIV);
            handler.acceptIV = (ImageView) row.findViewById(R.id.acceptWorkoutIV);
            row.setTag(handler);
        }
        else{
            handler = (DataHandler) row.getTag();
        }
        WorkoutExercise we = (WorkoutExercise) getItem(position);
        handler.nameTV.setText(we.getExercise().getNazwa());
        handler.repWeightTV.setText(we.getWeight() + "kg x " + we.getReps() + "powt");
        //handler.optionImage.setImageResource(id);
        //handler.optionName.setText(option.textOption);
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
        ImageView acceptIV;
    }
}
