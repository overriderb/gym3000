package org.gym.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.*;
import android.widget.Button;
import android.widget.LinearLayout;

import com.sun.xml.internal.bind.annotation.OverrideAnnotationOf;

import org.gym.cache.CurrentProgramCache;
import org.gym.domain.Program;
import org.gym.domain.Workout;
import org.gym.logging.Logger;
import org.gym.service.ProgramService;
import org.gym.service.WorkoutService;

import java.util.List;

/**
 * Main activity. It shows the list of programs stored in DB.
 */
public class MenuActivity extends Activity {

    private ProgramService programService;
    private WorkoutService workoutService;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        programService = new ProgramService();
        workoutService = new WorkoutService();

        setContentView(R.layout.menu_layout);
        createMenuButtons();
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onPause() {
        super.onPause();
        overridePendingTransition(R.anim.right_in, R.anim.left_out);
        Logger.info("onPause()", MenuActivity.class);
    }

    @Override
    public boolean onCreateOptionsMenu (Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_layout_actions, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected (MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_action_settings:
                startSettingsMenuActivity();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void startActivity(View view){
        Intent intent = new Intent(this, ProgramActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.right_in, R.anim.left_out);
    }

    private void startSettingsMenuActivity(){
        Intent intent = new Intent(this, SettingsMenuActivity.class);
        startActivity(intent);
    }

    private void createMenuButtons(){
        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.menu_linear_layout);

        List<Program> programsList = programService.getPrograms(this);

        for(final Program program : programsList){
            Button button = new Button(this);
            button.setText(program.getName());
            button.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    fillCurrentProgramCache(program);
                    startActivity(view);
                }
            });
            linearLayout.addView(button);
        }
    }

    private void fillCurrentProgramCache(Program program){
        CurrentProgramCache cache = CurrentProgramCache.getInstance();
        List<Workout> workoutList = workoutService.getWorkouts(this, program.getId());
        cache.setValues(program.getId(), program.getName(), program.getDescription(), workoutList);
    }
}