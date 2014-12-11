package org.gym.activity;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import org.gym.adapter.ProgramPagerAdapter;
import org.gym.repository.DatabaseHelper;
import org.gym.domain.Workout;

import java.util.List;


/**
 * TODO: Add class description
 */
public class ProgramActivity extends FragmentActivity {

    public final static String CURRENT_ITEM = "org.gym.activity.ProgramActivity.CURRENT_ITEM";

    private ProgramPagerAdapter programPagerAdapter;
    private ViewPager viewPager;
    private int currentItem;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        fillParams();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.program_layout);
        programPagerAdapter = new ProgramPagerAdapter(getSupportFragmentManager());
        viewPager = (ViewPager) findViewById(R.id.programPager);
        viewPager.setAdapter(programPagerAdapter);
        viewPager.setCurrentItem(currentItem);
        overridePendingTransition(R.anim.push_down_in, R.anim.push_down_out);
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public boolean onCreateOptionsMenu (Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.program_layout_actions, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected (MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_history:
                startHistory();
                return true;
            case R.id.action_program_settings:
                startSettings();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void startHistory(){
        Intent intent = new Intent(this, HistoryActivity.class);
        intent.putExtra(CURRENT_ITEM, viewPager.getCurrentItem());
        startActivity(intent);
    }

    private void startSettings(){
        Intent intent = new Intent(this, ProgramSettingsActivity.class);
        startActivity(intent);
    }

    private void fillParams(){
        Intent intent = getIntent();
        currentItem = intent.getIntExtra(HistoryActivity.CURRENT_ITEM, 0);
    }
}