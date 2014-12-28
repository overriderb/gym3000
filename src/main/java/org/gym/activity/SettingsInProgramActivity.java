package org.gym.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

/**
 * Created by AndreyNick on 28.12.2014.
 */
public class SettingsInProgramActivity extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings_program_layout);

        Switch s = (Switch) findViewById(R.id.settings_program_switch_hide_description);
        if (s != null) {
            s.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if(isChecked){
                        TextView textView = (TextView) findViewById(R.id.workout_descr);
                        textView.setHeight(0);
                    }else{
                        TextView textView = (TextView) findViewById(R.id.workout_descr);
                        textView.setHeight(300);
                    }
                }
            });
        }
    }

    //onClick was setted in xml
    public void startChangeProgram(View view){
        Intent intent = new Intent(this, ChangeProgramActivity.class);
        startActivity(intent);
    }

    public void onToggleHidePicture(View view){
        boolean on = ((Switch) view).isChecked();
        /*if (on) {
            FrameLayout frameLayout = (FrameLayout) findViewById(R.id.absolute_layout);
            frameLayout.setMinimumHeight(30);

        } else {
            FrameLayout frameLayout = (FrameLayout) findViewById(R.id.absolute_layout);
            frameLayout.setMinimumHeight(180);
        }*/
    }
    public void onToggleHideDescription(View view){
        boolean on = ((Switch) view).isChecked();
        /*if (on) {
            TextView textView = (TextView) findViewById(R.id.workout_descr);
            textView.setHeight(0);
        } else {
            TextView textView = (TextView) findViewById(R.id.workout_descr);
            textView.setHeight(300);
        }*/
    }
}
