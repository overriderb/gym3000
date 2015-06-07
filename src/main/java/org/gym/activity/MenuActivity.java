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

import java.util.List;

/**
 * TODO: Add class description
 */
public class MenuActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
        cache.setValues(program.getName(), program.getDescription(), program.getExerciseTypes());
    }
}