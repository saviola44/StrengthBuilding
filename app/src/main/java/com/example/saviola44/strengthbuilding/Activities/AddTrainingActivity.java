package com.example.saviola44.strengthbuilding.Activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.example.saviola44.strengthbuilding.R;

/**
 * Created by saviola44 on 24.05.16.
 */
public class AddTrainingActivity extends AppCompatActivity {
    ListView trainingsLV;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_training_activity_layout);
        trainingsLV = (ListView) findViewById(R.id.trainingsLV);
    }
}
