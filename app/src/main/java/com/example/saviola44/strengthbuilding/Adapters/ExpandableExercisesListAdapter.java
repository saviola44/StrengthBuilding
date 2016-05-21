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
    private Context _context;
    private List<Muscle> _listDataHeader; // header titles
    // child data in format of header title, child title
    private HashMap<Long, List<Exercise>> _listDataChild;
    public ExpandableExercisesListAdapter(Context context, List<Muscle> listDataHeader,
                                 HashMap<Long, List<Exercise>> listChildData) {
        this._context = context;
        this._listDataHeader = listDataHeader;
        this._listDataChild = listChildData;
    }

    @Override
    public int getGroupCount() {
        return this._listDataHeader.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        //pobieramy wybrany element z Listy, ten element jest naszym kluczem w hashMapie
        // i pobieramy wartosc dla dla tego klucza która jest Lista i  zwracamy jej rozmiAR
        return this._listDataChild.get(this._listDataHeader.get(groupPosition).getId())
                .size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return _listDataHeader.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return this._listDataChild.get(this._listDataHeader.get(groupPosition).getId())
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
        Muscle headerTitle = (Muscle) getGroup(groupPosition);
        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this._context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.muscle_parts_group, null);
        }

        TextView lblListHeader = (TextView) convertView
                .findViewById(R.id.lblListHeader);
        lblListHeader.setTypeface(null, Typeface.BOLD);
        lblListHeader.setText(headerTitle.getName());

        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        final Exercise childText = (Exercise) getChild(groupPosition, childPosition);

        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this._context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.exercises_row_child_layout, null);
        }

        TextView txtListChild = (TextView) convertView
                .findViewById(R.id.lblListItem);

        txtListChild.setText(childText.getNazwa());
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}
