package com.example.saviola44.strengthbuilding.Activities;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.example.saviola44.strengthbuilding.Adapters.ViewPagerAdapter;
import com.example.saviola44.strengthbuilding.R;
import com.example.saviola44.strengthbuilding.StrengthBuilderApp;
import com.example.saviola44.strengthbuilding.TrainingMethodFragment;

/**
 * Created by saviola44 on 23.05.16.
 */
public class SelectTrainingMethodActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.traning_method_activity);

        viewPager = (ViewPager) findViewById(R.id.viewpager);
        setupViewPager(viewPager);

        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
    }
    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        StrengthBuilderApp app = StrengthBuilderApp.getInstance(getApplicationContext());
        TrainingMethodFragment fragm1 = new TrainingMethodFragment();
        fragm1.setMethod(TrainingMethodFragment.strength);
        TrainingMethodFragment fragm2 = new TrainingMethodFragment();
        fragm2.setMethod(TrainingMethodFragment.mass);
        TrainingMethodFragment fragm3 = new TrainingMethodFragment();
        fragm3.setMethod(TrainingMethodFragment.custom);
        adapter.addFragment(fragm1, "Treningi na siłe");
        adapter.addFragment(fragm2, "Treningi na mase");
        adapter.addFragment(fragm3, "Własny");
        viewPager.setAdapter(adapter);
    }
}
