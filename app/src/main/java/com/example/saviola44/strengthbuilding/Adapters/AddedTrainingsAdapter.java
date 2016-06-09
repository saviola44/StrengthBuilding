package com.example.saviola44.strengthbuilding.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.saviola44.strengthbuilding.R;
import com.example.saviola44.strengthbuilding.Model.Training;

import java.util.List;

/**
 * Created by saviola44 on 25.05.16.
 */
public class AddedTrainingsAdapter extends ArrayAdapter {
    List<Training> trainings;

    public AddedTrainingsAdapter(Context context, int resource, List<Training> trainings) {
        super(context, resource);
        this.trainings = trainings;
    }
    @Override
    public Object getItem(int position) {
        return trainings.get(position);
    }

    @Override
    public int getCount() {
        return trainings.size();
    }

    public void addtraining(Training t){
        trainings.add(t);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        DataHandler handler;
        if(convertView==null){
            LayoutInflater inflater = (LayoutInflater)getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(R.layout.added_trainings_row_layout, parent, false);
            handler = new DataHandler();
            handler.trainingNameTV = (TextView) row.findViewById(R.id.trainingNameTV);
            handler.trainingDescTV = (TextView) row.findViewById(R.id.trainingDescTV);
            row.setTag(handler);
        }
        else{
            handler = (DataHandler) row.getTag();
        }
        Training training = (Training) getItem(position);
        handler.trainingNameTV.setText(training.getTrainingLabel());
        handler.trainingDescTV.setText(training.getTrainingDescription());
        return row;
    }

    static class DataHandler{
        TextView trainingNameTV;
        TextView trainingDescTV;
    }
}
