package org.gym.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
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



    @Override
    public boolean onCreateOptionsMenu (Menu menu){       //TODO need to discuss what options we need in options menu
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_layout_actions, menu);
        return super.onCreateOptionsMenu(menu);
    }

    public void startActivity(View view){
        Intent intent = new Intent(this, ProgramActivity.class);
        startActivity(intent);
    }

    /*@Override                                                //TODO Use it if need
    public boolean onOptionsItemSelected (MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_add_program:
                //start smth
                return true;
            case R.id.action_add_workout:
                //start smth
                return true;
            case R.id.action_settings:
                //start smth
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }*/

}