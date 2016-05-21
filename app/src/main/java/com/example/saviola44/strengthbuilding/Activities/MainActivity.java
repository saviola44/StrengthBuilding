package com.example.saviola44.strengthbuilding.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.saviola44.strengthbuilding.Adapters.OptionAdapter;
import com.example.saviola44.strengthbuilding.Model.Option;
import com.example.saviola44.strengthbuilding.R;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public static List<Option> options;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        createOptions();
        ListView listViewOptions = (ListView) findViewById(R.id.mainOptionLV);

        OptionAdapter optionAdapter = new OptionAdapter(getApplicationContext(),
                R.layout.activity_main_row_layout, options);
        listViewOptions.setAdapter(optionAdapter);
        listViewOptions.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

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
        options.add(new Option("about", "O aplikacji"));
    }
}
