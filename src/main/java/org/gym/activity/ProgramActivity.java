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
import org.gym.adapter.ProgramPagerAdapter;
import org.gym.logging.Logger;


/**
 * Shows workouts list which has single parent program.
 */
public class ProgramActivity extends FragmentActivity {

    public final static String CURRENT_ITEM = "org.gym.activity.ProgramActivity.CURRENT_ITEM";
    public final static int ACTIVITY = 2;

    private ViewPager viewPager;
    private ProgressBar progressBar;
    private int currentItem;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        fillParams();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.program_layout);
        ProgramPagerAdapter programPagerAdapter = new ProgramPagerAdapter(getSupportFragmentManager());
        viewPager = (ViewPager) findViewById(R.id.program_pager);
        viewPager.setAdapter(programPagerAdapter);
        viewPager.setCurrentItem(currentItem);
        progressBar = (ProgressBar)findViewById(R.id.programProgressBar);
        progressBar.setMax(100);
        progressBar.setProgress(75);
    }

    @Override
    public void onStart() {
        super.onStart();
        int activity = getIntent().getIntExtra("activity", 0);
        Logger.debug("onStart() int activity: " + activity, ProgramActivity.class);
        switch (activity) {
            case MenuActivity.ACTIVITY:
                Logger.debug("onStart() from activity = MenuActivity.ACTIVITY", ProgramActivity.class);
                overridePendingTransition(R.anim.left_slide_1, R.anim.left_slide_2);
                break;
            case HistoryActivity.ACTIVITY:
                Logger.debug("onStart() from activity = HistoryActivity.ACTIVITY", ProgramActivity.class);
                overridePendingTransition(R.anim.buttom_out, R.anim.top_in);
                break;
            default:
                break;
        }
    }

   /* @Override
    public void onPause() {
        super.onPause();
        Logger.debug("Android PROGRAM pause button clicked", ProgramActivity.class);

    }*/

    @Override
    public boolean onCreateOptionsMenu (Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.program_layout_actions, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected (MenuItem item) {
        switch (item.getItemId()) {
            case R.id.program_action_settings:
                startSettingsProgramActivity();
                return true;
            /*case android.R.id.home:
                finish();
                overridePendingTransition(R.anim.left_slide_1, R.anim.left_slide_2);
                Logger.debug("Android PROGRAM home button clicked", ProgramActivity.class);
                return true;*/
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    //it is called from xml
    public void startHistory(View view){
        Intent intent = new Intent(this, HistoryActivity.class);
        intent.putExtra(CURRENT_ITEM, viewPager.getCurrentItem());
        intent.putExtra("activity", ProgramActivity.ACTIVITY);
        startActivity(intent);
        //overridePendingTransition(R.anim.buttom_out, R.anim.top_in);
    }

    private void startSettingsProgramActivity(){
        Intent intent = new Intent(this, SettingsProgramActivity.class);
        startActivity(intent);
    }

    private void fillParams(){
        Intent intent = getIntent();
        currentItem = intent.getIntExtra(HistoryActivity.CURRENT_ITEM, 0);
    }
}