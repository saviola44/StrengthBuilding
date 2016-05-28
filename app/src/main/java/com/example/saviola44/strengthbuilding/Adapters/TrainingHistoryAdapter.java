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
 * Created by saviola44 on 28.05.16.
 */
public class TrainingHistoryAdapter extends ArrayAdapter {
    List<WorkoutExercise> exercises;
    Context context;
    public TrainingHistoryAdapter(Context context, int resource, List<WorkoutExercise> exercises) {
        super(context, resource);
        this.exercises = exercises;
        this.context = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        DataHandler handler;
        if(convertView==null){
            LayoutInflater inflater = (LayoutInflater)getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(R.layout.training_history_row_layout, parent, false);
            handler = new DataHandler();
            handler.nameTV = (TextView) row.findViewById(R.id.doTrainingExNameTV);
            handler.repWeightTV = (TextView) row.findViewById(R.id.TrainingExTV);
            handler.comment = (TextView) row.findViewById(R.id.show_comment_label);
            row.setTag(handler);
        }
        else{
            handler = (DataHandler) row.getTag();
        }
        WorkoutExercise we = exercises.get(position);
        handler.nameTV.setText(we.getExercise().getNazwa());
        String s= "";
        if(we.getWeight()!=0){
            s+=we.getWeight()+ "kg";
        }
        if(we.getReps()>0){
            if(s!=null) s+=" x ";
            s+=we.getReps() + "powt";
        }
        handler.repWeightTV.setText(s);
        if(we.getComment()!=null && !we.getComment().equals("")){
            handler.comment.setText("kliknij aby zobaczyc komentarz");
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
        TextView comment;
        ImageView editIV;
    }
}
