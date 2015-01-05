package org.gym.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

/**
 * Settings activity for MenuActivity.
 */
public class SettingsMenuActivity extends Activity {


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings_menu_layout);
    }

    /**
     * onClick was setted in xml
     */
    public void startChangePrograms(View view){
        Intent intent = new Intent(this, ChangeProgramsListActivity.class);
        startActivity(intent);
    }

}
