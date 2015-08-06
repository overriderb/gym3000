package org.gym.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.*;
import android.widget.Button;
import android.widget.LinearLayout;
import org.gym.cache.CurrentProgramCache;
import org.gym.model.Program;
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
//        databaseHelper = new DatabaseHelper(this);

//        programService = new ProgramService();
//        workoutService = new WorkoutService();
        
        setContentView(R.layout.menu_layout);
        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.menu_linear_layout);
        List<Program> programs = ProgramService.getInstance().findAll();
        for(final Program program : programs) {
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

//        createMenuButtons();
    }

    @Override
    public void onStart() {
        super.onStart();
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
            case R.id.action_settings:
                startSettingsMenuActivity();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void startActivity(View view){
        Intent intent = new Intent(this, ProgramActivity.class);
        startActivity(intent);
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
        cache.setValues(program.getName(), program.getDescription(), program.getExerciseTypes());
//        List<Workout> workoutList = workoutService.getWorkouts(this, program.getId());
//        cache.setValues(program.getId(), program.getName(), program.getDescription(), workoutList);
    }
}