package org.gym.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import org.gym.adapter.ProgramPagerAdapter;
import org.gym.activity.R;

/**
 * TODO: Add class description
 */
public class ProgramActivity extends FragmentActivity {

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections.
     */
    ProgramPagerAdapter programPagerAdapter;

    /**
     * The {@link android.support.v4.view.ViewPager} that will host the section contents.
     */
    ViewPager viewPager;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.program_layout);

        // Create the adapter that will return a fragment for each of the three
        // primary sections of the app.
        //programPagerAdapter = new ProgramPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        //viewPager = (ViewPager) findViewById(R.id.programPager);
        //viewPager.setAdapter(programPagerAdapter);
    }

    @Override
    public void onStart() {
        super.onStart();
    }
}