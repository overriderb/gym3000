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

    //onClick was setted in xml
    public void startChangeWorkoutsActivity(View view){
        Intent intent = new Intent(this, ChangeWorkoutsListActivity.class);
        startActivity(intent);
    }
}
