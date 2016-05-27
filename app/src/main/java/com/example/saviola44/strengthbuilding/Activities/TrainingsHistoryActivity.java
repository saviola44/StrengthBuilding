package com.example.saviola44.strengthbuilding.Activities;

import android.app.ListActivity;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.saviola44.strengthbuilding.Adapters.AddedTrainingsAdapter;
import com.example.saviola44.strengthbuilding.Database.DAO.DAO;
import com.example.saviola44.strengthbuilding.Database.DAO.HistoryDAO;
import com.example.saviola44.strengthbuilding.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by saviola44 on 28.05.16.
 */
public class TrainingsHistoryActivity extends ListActivity {
    List<String> dates;
    ArrayAdapter<String> adapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.training_history_activity_layout);
        dates = getIntent().getStringArrayListExtra("history");

        adapter = new ArrayAdapter<String>(getApplicationContext(),
                R.layout.added_trainings_row_layout, R.id.trainingNameTV, dates);
        setListAdapter(adapter);
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
    }
}
