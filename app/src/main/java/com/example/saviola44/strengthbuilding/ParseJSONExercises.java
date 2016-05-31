package com.example.saviola44.strengthbuilding;

import android.content.Context;
import android.content.res.AssetManager;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.example.saviola44.strengthbuilding.Database.DAO.ExerciseDAO;
import com.example.saviola44.strengthbuilding.Database.MusclePart;
import com.example.saviola44.strengthbuilding.Model.ExerciseInfo;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Iterator;

/**
 * Created by saviola44 on 21.05.16.
 */
public class ParseJSONExercises extends AsyncTask<Void, Void, Void> {
    Context context;
    public String jsonFile;

    public ParseJSONExercises(Context context) {
        this.context = context;
    }

    @Override
    protected Void doInBackground(Void... params) {
        AssetManager manager = context.getAssets();
        try{
            Log.d("doInBackgroundmethod", "poarsuje JSON'a ");
            InputStream input = manager.open("Exercises.json");
            BufferedReader reader = new BufferedReader(new InputStreamReader(input));
            StringBuilder sB = new StringBuilder();
            String line = reader.readLine();
            while (line!=null){
                sB.append(line);
                line = reader.readLine();
            }
            jsonFile = sB.toString();
            parseJSON();

        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(context, "Błąd podczas czytania pliku z baza cwiczen", Toast.LENGTH_LONG).show();
        }
        return null;
    }
    private void parseJSON(){
        try {
            JSONObject jsonObject = new JSONObject(jsonFile);
            JSONObject muscleParts  = jsonObject.getJSONObject("muscleparts");
            parseAndCreateMusclePartsInDB(muscleParts);
            JSONArray exercises = jsonObject.getJSONArray("exercises");
            parseAndCreateExercisesInDB(exercises);

        } catch (JSONException e) {
            e.printStackTrace();
            Toast.makeText(context, "Bład podczas przetwarzania bazy cwiczen", Toast.LENGTH_LONG).show();
        }
    }
    private void parseAndCreateMusclePartsInDB(JSONObject jsonObject){
        Iterator<String> keys = jsonObject.keys();
        MusclePart mp = new MusclePart(context);
        while(keys.hasNext()){
            try {
                String musclePart = keys.next();
                long id = jsonObject.getLong(musclePart);
                Log.d("insert", musclePart + " " + id);
                mp.save(id, musclePart);

            } catch (JSONException e) {
                e.printStackTrace();
                Toast.makeText(context, "Bład podczas przetwarzania pliku z Baza cwiczen", Toast.LENGTH_LONG).show();
            }
        }
    }

    private void parseAndCreateExercisesInDB(JSONArray jsonArray){
        ExerciseDAO exerciseDAO = new ExerciseDAO(context);
        for(int i=0; i<jsonArray.length(); i++){
            try {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                long id = jsonObject.getLong("id");
                String nazwa = jsonObject.getString("nazwa");
                long workedMuscle = jsonObject.getLong("cwiczona_partia");
                boolean isCompund = jsonObject.getBoolean("wielostawowe");
                ExerciseInfo exercise = new ExerciseInfo(id,nazwa, workedMuscle, isCompund);
                Log.d("insert", nazwa + " " + workedMuscle + " " + isCompund);
                exerciseDAO.saveElement(exercise);


            } catch (JSONException e) {
                e.printStackTrace();
                Toast.makeText(context, "Bład podczas przetwarzania pliku z Baza cwiczen", Toast.LENGTH_LONG).show();
            }
        }
    }
}
