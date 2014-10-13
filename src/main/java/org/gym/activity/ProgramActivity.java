package org.gym.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import org.gym.adapter.ProgramPagerAdapter;



/**
 * TODO: Add class description
 */
public class ProgramActivity extends FragmentActivity {

    ProgramPagerAdapter programPagerAdapter;
    ViewPager viewPager;
    public final static String CURRENT_ITEM = "org.gym.activity.ProgramActivity.CURRENT_ITEM";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.program_layout);

        programPagerAdapter = new ProgramPagerAdapter(getSupportFragmentManager());

        viewPager = (ViewPager) findViewById(R.id.programPager);
        viewPager.setAdapter(programPagerAdapter);
        Intent intent = getIntent();
        viewPager.setCurrentItem(intent.getIntExtra(HistoryActivity.CURRENT_ITEM, 0));
        overridePendingTransition(R.anim.push_down_in, R.anim.push_down_out);
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public boolean onCreateOptionsMenu (Menu menu){       //TODO need to discuss what options do we need in options menu
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
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void startHistory(){
        Intent intent = new Intent(this, HistoryActivity.class);
        intent.putExtra(CURRENT_ITEM, viewPager.getCurrentItem());
        startActivity(intent);
    }
}