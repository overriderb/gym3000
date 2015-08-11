package org.gym.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

/**
 * Settings activity for ProgramActivity.
 */
public class SettingsProgramActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings_program_layout);
    }

    @Override
    public void onStart() {
        super.onStart();
        int activity = getIntent().getIntExtra(getString(R.string.activity_number), 0);
        switch (activity) {
            case R.integer.program_activity:
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

    //onClick was setted in xml
    public void startChangeWorkoutsActivity(View view){
        Intent intent = new Intent(this, ChangeWorkoutsListActivity.class);
        intent.putExtra(getString(R.string.activity_number), R.integer.settings_program_activity);
        startActivity(intent);
    }
}
