package org.gym.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import org.gym.adapter.HistoryPagerAdapter;
import org.gym.repository.DatabaseHelper;
import org.gym.domain.Workout;

import java.util.List;

/**
 * Created by anni0913 on 08.07.2014.
 */
public class HistoryActivity extends FragmentActivity {

    public final static String CURRENT_ITEM = "org.gym.activity.HistoryActivity.CURRENT_ITEM";
    public final static String SELECTED_PROGRAM_ID = "org.gym.activity.HistoryActivity.SELECTED_PROGRAM_ID";

    private HistoryPagerAdapter historyPagerAdapter;
    private ViewPager viewPager;
    private int currentItem;
    private long selectedProgramId;
    private List<Workout> listOfWorkouts;
    private DatabaseHelper databaseHelper;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        fillParams();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.history_layout);
        historyPagerAdapter = new HistoryPagerAdapter(getSupportFragmentManager(), listOfWorkouts);
        viewPager = (ViewPager) findViewById(R.id.historyPager);
        viewPager.setAdapter(historyPagerAdapter);
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

    private void startProgram(){
        Intent intent = new Intent(this, ProgramActivity.class);
        intent.putExtra(CURRENT_ITEM, viewPager.getCurrentItem());
        intent.putExtra(SELECTED_PROGRAM_ID, selectedProgramId);
        startActivity(intent);
    }

    private void fillParams(){
        databaseHelper = new DatabaseHelper(this);
        Intent intent = getIntent();
        currentItem = intent.getIntExtra(ProgramActivity.CURRENT_ITEM, 0);
        selectedProgramId = intent.getLongExtra(ProgramActivity.SELECTED_PROGRAM_ID, 0);
        listOfWorkouts = databaseHelper.getWorkoutRepository().findWorkoutsListByParentId(selectedProgramId);
    }
}
