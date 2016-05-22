package com.example.saviola44.strengthbuilding.Adapters;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.example.saviola44.strengthbuilding.Model.Exercise;
import com.example.saviola44.strengthbuilding.Model.Muscle;
import com.example.saviola44.strengthbuilding.R;

import java.util.HashMap;
import java.util.List;

/**
 * Created by saviola44 on 22.05.16.
 */
public class ExpandableExercisesListAdapter extends BaseExpandableListAdapter {
    private Context context;
    private List<Muscle> muscleParts; // nagłowki dla expandable list View
    private HashMap<Long, List<Exercise>> exercises; //podelementy ^ nagłówków
    public ExpandableExercisesListAdapter(Context context, List<Muscle> muscleParts,
                                 HashMap<Long, List<Exercise>> exercises) {
        this.context = context;
        this.muscleParts = muscleParts;
        this.exercises = exercises;
    }

    @Override
    public int getGroupCount() {
        return muscleParts.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        //ile jest cwiczen dla danej parti miesniowej, kluczem w hashmapie jest id parti miesniowej
        return exercises.get(muscleParts.get(groupPosition).getId())
                .size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return muscleParts.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return exercises.get(muscleParts.get(groupPosition).getId())
                .get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        Muscle headerMuscle = (Muscle) getGroup(groupPosition);
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) this.context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.muscle_parts_group, null);
        }

        TextView header = (TextView) convertView
                .findViewById(R.id.musclePartHeaderTV);
        header.setTypeface(null, Typeface.BOLD);
        header.setText(headerMuscle.getName());

        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        final Exercise childText = (Exercise) getChild(groupPosition, childPosition);

        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this.context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.exercises_row_child_layout, null);
        }

        TextView txtListChild = (TextView) convertView
                .findViewById(R.id.exerciseChildTV);

        txtListChild.setText(childText.getNazwa());
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}
