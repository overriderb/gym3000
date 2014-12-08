package org.gym.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.*;
import android.widget.Button;
import android.widget.LinearLayout;
import org.gym.cache.CurrentProgramCache;
import org.gym.repository.DatabaseHelper;
import org.gym.repository.ProgramAdapter;
import org.gym.repository.WorkoutAdapter;
import org.gym.domain.Program;
import org.gym.domain.Workout;

import java.util.List;

/**
 * TODO: Add class description
 */
public class MenuActivity extends Activity {

    private DatabaseHelper databaseHelper;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        databaseHelper = new DatabaseHelper(this);
        setContentView(R.layout.menu_layout);
        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.menu_linear_layout);
        ProgramRepository programRepository =  databaseHelper.getProgramRepository();
        List<Program> programsList = programRepository.findAllProgramsList();
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

    public void startActivity(View view){
        Intent intent = new Intent(this, ProgramActivity.class);
        startActivity(intent);
    }

    public void fillCurrentProgramCache(Program program){
        CurrentProgramCache cache = CurrentProgramCache.getInstance();
        WorkoutAdapter workoutAdapter = databaseHelper.getWorkoutAdapter();
        List<Workout> workoutList = workoutAdapter.getWorkoutsListByParentId(program.getId());
        cache.setValues(program.getName(), program.getDescription(), workoutList);
    }
}