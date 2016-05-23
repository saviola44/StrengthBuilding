package com.example.saviola44.strengthbuilding.Activities;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.example.saviola44.strengthbuilding.Adapters.ViewPagerAdapter;
import com.example.saviola44.strengthbuilding.R;
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
        adapter.addFragment(new TrainingMethodFragment(), "Treningi na siłe");
        adapter.addFragment(new TrainingMethodFragment(), "Treningi na mase");
        adapter.addFragment(new TrainingMethodFragment(), "Własny");
        viewPager.setAdapter(adapter);
    }
}
