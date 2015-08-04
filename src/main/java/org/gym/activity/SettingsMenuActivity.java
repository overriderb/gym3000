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

    @Override
    public void onStart() {
        super.onStart();
        int activity = getIntent().getIntExtra(getString(R.string.activity_number), 0);
        switch (activity) {
            case R.integer.menu_activity:
                overridePendingTransition(R.anim.left_slide_1, R.anim.left_slide_2);
                break;
            /*case R.integer.change_programs_list_activity:
                overridePendingTransition(R.anim.right_slide_1, R.anim.right_slide_2);
                break;*/
            default:
                overridePendingTransition(R.anim.right_slide_1, R.anim.right_slide_2);
                break;
        }
    }

    //onClick was setted in xml
    public void startChangePrograms(View view){
        Intent intent = new Intent(this, ChangeProgramsListActivity.class);
        intent.putExtra(getString(R.string.activity_number), R.integer.settings_menu_activity);
        startActivity(intent);
    }

}
