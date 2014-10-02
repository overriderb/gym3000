package org.gym.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import org.gym.adapter.HistoryPagerAdapter;
import org.gym.adapter.ProgramPagerAdapter;

/**
 * Created by anni0913 on 08.07.2014.
 */
public class HistoryActivity extends FragmentActivity {

    HistoryPagerAdapter historyPagerAdapter;
    ViewPager viewPager;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.history_layout);
        historyPagerAdapter = new HistoryPagerAdapter(getSupportFragmentManager());
        viewPager = (ViewPager) findViewById(R.id.historyPager);
        viewPager.setAdapter(historyPagerAdapter);
    }

    @Override
    public void onStart() {
        super.onStart();
    }
}
