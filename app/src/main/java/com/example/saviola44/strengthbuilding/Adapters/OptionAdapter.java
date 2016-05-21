package com.example.saviola44.strengthbuilding.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.saviola44.strengthbuilding.Model.Option;
import com.example.saviola44.strengthbuilding.R;

import java.util.List;

/**
 * Created by saviola44 on 21.05.16.
 */
public class OptionAdapter extends ArrayAdapter {
    List<Option> options;
    public OptionAdapter(Context context, int resource, List<Option> options) {
        super(context, resource);
        this.options = options;
    }

    @Override
    public Object getItem(int position) {
        return options.get(position);
    }

    @Override
    public int getCount() {
        return options.size();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        DataHandler handler;
        if(convertView==null){
            LayoutInflater inflater = (LayoutInflater)getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(R.layout.activity_main_row_layout, parent, false);
            handler = new DataHandler();
            handler.optionImage = (ImageView) row.findViewById(R.id.mainOptionImage);
            handler.optionName = (TextView) row.findViewById(R.id.mainOptionTV);
            row.setTag(handler);
        }
        else{
            handler = (DataHandler) row.getTag();
        }
        Option option = (Option) getItem(position);
        int id = getContext().getResources().getIdentifier(option.imageName, "drawable", getContext().getPackageName());

        handler.optionImage.setImageResource(id);
        handler.optionName.setText(option.textOption);
        return row;
    }

    static class DataHandler{
        ImageView optionImage;
        TextView optionName;
    }
}
