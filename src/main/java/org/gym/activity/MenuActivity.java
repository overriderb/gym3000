package org.gym.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import org.gym.activity.R;

/**
 * TODO: Add class description
 */
public class MenuActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_layout);
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    public void startActivity(View view){
        Intent intent = new Intent(this, ProgramActivity.class);
        startActivity(intent);
    }

}