package org.gym.activity;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import org.gym.cache.CurrentProgramCache;
import org.gym.domain.Workout;
import org.gym.logging.Logger;
import org.gym.repository.DatabaseHelper;

/**
 * Activity for changing and adding workout
 */
public class ChangeSingleWorkoutActivity extends Activity {

    DatabaseHelper databaseHelper;
    CurrentProgramCache cache;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.change_single_workout_layout);
        databaseHelper = new DatabaseHelper(this);
        cache = CurrentProgramCache.getInstance();
    }

    @Override
    public void onStart() {
        super.onStart();
        int activity = getIntent().getIntExtra(getString(R.string.activity_number), 0);
        switch (activity) {
            case R.integer.change_workouts_list_activity:
                overridePendingTransition(R.anim.left_slide_1, R.anim.left_slide_2);
                break;
            default:
                break;
        }
    }

    @Override
    public void onPause(){
        super.onPause();
        overridePendingTransition(R.anim.right_slide_1, R.anim.right_slide_2);

    }

    public void saveWorkout(View view){

        EditText title = (EditText)findViewById(R.id.change_single_workout_title_edit);
        EditText description = (EditText)findViewById(R.id.change_single_workout_description_edit);

        Logger.info("Title :" + title.getText().toString() + " Descr : " + description.getText().toString(), ChangeSingleWorkoutActivity.class);

        Workout workout = new Workout(cache.getId(), title.getText().toString(), description.getText().toString(), 0, 12);
        cache.setWorkout(workout);


        Intent intent = new Intent(this, ProgramActivity.class);
        intent.putExtra(getString(R.string.activity_number), R.integer.change_single_workout_activity);
        startActivity(intent);
    }
}
