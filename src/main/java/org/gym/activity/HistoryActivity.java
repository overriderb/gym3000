package org.gym.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import org.gym.adapter.HistoryPagerAdapter;

/**
 * Created by anni0913 on 08.07.2014.
 */
public class HistoryActivity extends FragmentActivity {

    HistoryPagerAdapter historyPagerAdapter;
    ViewPager viewPager;
    public final static String CURRENT_ITEM = "org.gym.activity.HistoryActivity.CURRENT_ITEM";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.history_layout);
        historyPagerAdapter = new HistoryPagerAdapter(getSupportFragmentManager());
        viewPager = (ViewPager) findViewById(R.id.historyPager);
        viewPager.setAdapter(historyPagerAdapter);
        Intent intent = getIntent();
        viewPager.setCurrentItem(intent.getIntExtra(ProgramActivity.CURRENT_ITEM, 0));
        overridePendingTransition(R.anim.push_down_in, R.anim.push_down_out);
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public boolean onCreateOptionsMenu (Menu menu){       //TODO need to discuss what options do we need in options menu
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.history_layout_actions, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected (MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_workout:
                startProgram();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void startProgram(){
        Intent intent = new Intent(this, ProgramActivity.class);
        intent.putExtra(CURRENT_ITEM, viewPager.getCurrentItem());
        startActivity(intent);
    }

}
