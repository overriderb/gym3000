package org.gym.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import org.gym.adapter.HistoryPagerAdapter;

/**
 * Activity shows exercises and attempts which are binded to current workouts list and single program
 */
public class HistoryActivity extends FragmentActivity {

    public final static String CURRENT_ITEM = "org.gym.activity.HistoryActivity.CURRENT_ITEM";

    private HistoryPagerAdapter historyPagerAdapter;
    private ViewPager viewPager;
    //private ProgressBar progressBar;
    private int currentItem;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        fillParams();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.history_layout);
        historyPagerAdapter = new HistoryPagerAdapter(getSupportFragmentManager());
        viewPager = (ViewPager) findViewById(R.id.historyPager);
        viewPager.setAdapter(historyPagerAdapter);
        viewPager.setCurrentItem(currentItem);
        /*progressBar = (ProgressBar)findViewById(R.id.historyProgressBar);
        progressBar.setProgress(60);*/
    }

   /* @Override
    public void onStart() {
        super.onStart();
        int activity = getIntent().getIntExtra(getString(R.string.activity_number), 0);
        switch (activity) {
            case R.integer.program_activity:
                overridePendingTransition(R.anim.slide_down_1, R.anim.slide_down_2);
                break;
            default:
                break;
        }
    }

    @Override
    public void onPause(){
        super.onPause();
        overridePendingTransition(R.anim.right_slide_1, R.anim.right_slide_2);
    }*/

    @Override
    public boolean onCreateOptionsMenu (Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.history_layout_actions, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected (MenuItem item) {
        switch (item.getItemId()) {
            case R.id.history_action_settings:
                startSettingsProgramActivity();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }



    //it is called from xml
    public void startProgram(View view){
        Intent intent = new Intent(this, ProgramActivity.class);
        intent.putExtra(CURRENT_ITEM, viewPager.getCurrentItem());
        intent.putExtra(getString(R.string.activity_number), R.integer.history_activity);
        startActivity(intent);
    }

    private void startSettingsProgramActivity(){
        Intent intent = new Intent(this, SettingsProgramActivity.class);
        intent.putExtra(getString(R.string.activity_number), R.integer.program_activity);
        startActivity(intent);
    }

    private void fillParams(){
        Intent intent = getIntent();
        currentItem = intent.getIntExtra(ProgramActivity.CURRENT_ITEM, 0);
    }
}
