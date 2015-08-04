package org.gym.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import org.gym.cache.CurrentProgramCache;
import org.gym.domain.Workout;


import java.util.ArrayList;
import java.util.List;

/**
 * Activity for changing order in list of workouts, add workout and change workout.
 * It opens current list of workouts which has one parent program.
 */
public class ChangeWorkoutsListActivity extends Activity {

    CurrentProgramCache cache;

    public ChangeWorkoutsListActivity(){
       cache = CurrentProgramCache.getInstance();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.change_workouts_layout);


        List<Workout> workoutList = cache.getWorkoutList();
        ArrayList<String> resultList = new ArrayList<String>();
        for(Workout item: workoutList){
            resultList.add(item.getName());
        }

        ListView listView = (ListView)findViewById(R.id.change_workouts_list_view);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.change_item_name_text_view, resultList);
        listView.setAdapter(adapter);
    }

    @Override
    public void onStart() {
        super.onStart();
        int activity = getIntent().getIntExtra(getString(R.string.activity_number), 0);
        switch (activity) {
            case R.integer.settings_program_activity:
                overridePendingTransition(R.anim.left_slide_1, R.anim.left_slide_2);
                break;
            /*case R.integer.change_single_workout_activity:
                overridePendingTransition(R.anim.right_slide_1, R.anim.right_slide_2);
                break;*/
            default:
                overridePendingTransition(R.anim.right_slide_1, R.anim.right_slide_2);
                break;
        }
    }


    //onClick is in xml file
    public void startChangeSingleWorkoutActivity(View view){
        Intent intent = new Intent(this, ChangeSingleWorkoutActivity.class);
        intent.putExtra(getString(R.string.activity_number), R.integer.change_workouts_list_activity);
        startActivity(intent);
    }
}
