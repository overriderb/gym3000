package org.gym.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
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
    //View programPagerAdapterRootView;


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
        programPagerAdapter = new ProgramPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        viewPager = (ViewPager) findViewById(R.id.programPager);
        viewPager.setAdapter(programPagerAdapter);

    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public boolean onCreateOptionsMenu (Menu menu){       //TODO need to discuss what options we need in options menu
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
        startActivity(intent);
    }

    public void closePictureAndDescription(View view){
        System.out.print(view.getId());

        //programPagerAdapterRootView = programPagerAdapter.getCurrentFragment().getView();


         //View rootView = inflater.inflate(R.layout.program_pages, container, false);


        /*FrameLayout frameLayout = (FrameLayout)programPagerAdapterRootView.findViewById(R.id.absolute_layout);
        ViewGroup.LayoutParams layoutParams = frameLayout.getLayoutParams();
        layoutParams.height = 50;*/
    }

}