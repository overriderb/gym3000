package org.gym.activity;

import android.app.Activity;
import android.os.Bundle;

/**
 * Activity for changing and adding workout
 */
public class ChangeSingleWorkoutActivity extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.change_single_workout_layout);
    }
}
