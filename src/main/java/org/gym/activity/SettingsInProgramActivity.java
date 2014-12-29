package org.gym.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import org.gym.helper.SharedPreferencesHelper;
import org.gym.logging.Logger;

/**
 * Created by AndreyNick on 28.12.2014.
 */
public class SettingsInProgramActivity extends Activity {

    private Switch hidePictureSwitch;
    private Switch hideDescriptionSwitch;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings_program_layout);
    }

    //onClick was setted in xml
    public void startChangeProgram(View view){
        Intent intent = new Intent(this, ChangeProgramActivity.class);
        startActivity(intent);
    }

}
