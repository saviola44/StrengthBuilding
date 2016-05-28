package com.example.saviola44.strengthbuilding.Activities;

import android.app.ListActivity;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ListView;

import com.example.saviola44.strengthbuilding.Adapters.TrainingHistoryAdapter;
import com.example.saviola44.strengthbuilding.Database.DAO.DAO;
import com.example.saviola44.strengthbuilding.Database.DAO.ExHistDAO;
import com.example.saviola44.strengthbuilding.Dialogs.CommentInfoDialog;
import com.example.saviola44.strengthbuilding.Model.WorkoutExercise;
import com.example.saviola44.strengthbuilding.R;

import java.util.List;

/**
 * Created by saviola44 on 28.05.16.
 */
public class ShowWorkoutTrainingActivity extends ListActivity {
    ListView listView;
    List<WorkoutExercise> exercises;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.training_history_activity_layout);
        long date = getIntent().getLongExtra("date", 0L);
        DAO dao = new ExHistDAO(getApplicationContext());
        exercises = (List<WorkoutExercise>) dao.getElementById(date);
        TrainingHistoryAdapter adapter = new TrainingHistoryAdapter(getApplicationContext(),
                R.layout.training_history_row_layout, exercises);
        setListAdapter(adapter);


    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        String com = exercises.get(position).getComment();
        if(com!=null && !com.equals("")){
            CommentInfoDialog dialog = new CommentInfoDialog();
            Bundle bundle = new Bundle();
            bundle.putString("com", com);
            dialog.setArguments(bundle);
            dialog.show(getFragmentManager(), "Show comment");
        }
    }
}
