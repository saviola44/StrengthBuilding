package com.example.saviola44.strengthbuilding.Activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.saviola44.strengthbuilding.Adapters.OptionAdapter;
import com.example.saviola44.strengthbuilding.Constants;
import com.example.saviola44.strengthbuilding.Database.DAO.DAO;
import com.example.saviola44.strengthbuilding.Database.DAO.ExerciseDAO;
import com.example.saviola44.strengthbuilding.Database.DAO.HistoryDAO;
import com.example.saviola44.strengthbuilding.Model.ExerciseInfo;
import com.example.saviola44.strengthbuilding.Model.Option;
import com.example.saviola44.strengthbuilding.ParseJSONExercises;
import com.example.saviola44.strengthbuilding.R;
import com.example.saviola44.strengthbuilding.StrengthBuilderApp;

import java.lang.reflect.Array;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public static List<Option> options;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ifFirstRun();
        createOptions();
        ListView listViewOptions = (ListView) findViewById(R.id.mainOptionLV);
        OptionAdapter optionAdapter = new OptionAdapter(getApplicationContext(),
                R.layout.activity_main_row_layout, options);
        listViewOptions.setAdapter(optionAdapter);
        listViewOptions.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0: {
                        goToTrainingsHistory();
                        break;
                    }
                    case 1: {
                        goToDisplayTrainingPlan();
                        break;
                    }
                    case 2: {
                        goToSelectMethodActivity();
                        break;
                    }
                    case 3: {
                        goToDoTrainingActivity();
                        break;
                    }
                    case 5: {
                        goToShowExActivity();
                        break;
                    }
                }
            }
        });

    }
    private void createOptions(){
        options = new ArrayList<>();
        options.add(new Option("history", "Historia Treningów"));
        options.add(new Option("your_training", "Twój Trening"));
        options.add(new Option("compose_training", "Skomponuj plan treningowy"));
        options.add(new Option("do_training", "Rób Trening"));
        options.add(new Option("wilks", "Oblicz swój wynik Wilks"));
        options.add(new Option("exercises", "Baza ćwiczeń"));
        options.add(new Option("about", "O aplikacji"));
    }

    private void ifFirstRun(){
        SharedPreferences prefs = getSharedPreferences(Constants.SHARED_PREFS, Context.MODE_PRIVATE);
        boolean firstRun = prefs.getBoolean("firstRun", true);
        if(firstRun){
            Log.d("firstRun", "inside if clausule in methos ifFirstRun");
            new ParseJSONExercises(getApplicationContext()).execute();
            prefs.edit().putBoolean("firstRun", false).commit();
        }
    }
    private void goToShowExActivity(){
        Intent intent = new Intent(getApplicationContext(), ShowAllExercisesActivity.class);
        startActivity(intent);
    }
    private void goToSelectMethodActivity(){
        Intent intent = new Intent(getApplicationContext(), SelectTrainingMethodActivity.class);
        startActivity(intent);
    }
    public void goToDisplayTrainingPlan(){
        StrengthBuilderApp app = StrengthBuilderApp.getInstance(getApplicationContext());
        if(app.getPlan()!=null){
            Intent intent = new Intent(getApplicationContext(), DisplayTrainingPlanActivity.class);
            startActivity(intent);
        }
    }
    public void goToDoTrainingActivity(){
        StrengthBuilderApp app = StrengthBuilderApp.getInstance(getApplicationContext());
        if(app.getPlan()!=null){
            Intent intent = new Intent(getApplicationContext(), DoTrainingActivity.class);
            startActivity(intent);
        }
    }
    void goToTrainingsHistory(){
        DAO dao = new HistoryDAO(getApplicationContext());
        List<Long> trainingsHist = dao.getAllElements();
        if(trainingsHist.size()<1){
            Toast.makeText(getApplicationContext(), "Nie wykonałeś jeszcze żadnego treningu", Toast.LENGTH_LONG).show();
        }
        else {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Calendar cal = Calendar.getInstance();
            ArrayList<String> datesString = new ArrayList<>();

            for(int i = 0; i < trainingsHist.size(); i++){
                cal.setTimeInMillis(trainingsHist.get(i));
                Date date = cal.getTime();
                String dateString = dateFormat.format(date);
                datesString.add(dateString);
            }
            Intent intent = new Intent(getApplicationContext(), TrainingsHistoryActivity.class);
            intent.putStringArrayListExtra("history", datesString);
            startActivity(intent);
        }
    }
}
