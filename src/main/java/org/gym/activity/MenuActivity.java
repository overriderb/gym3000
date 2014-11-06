package org.gym.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.*;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import org.gym.Factory;
import org.gym.activity.R;
import org.gym.object.Program;

import java.util.List;

/**
 * TODO: Add class description
 */
public class MenuActivity extends Activity {

    public final static String SELECTED_PROGRAM_ID = "org.gym.activity.MenuActivity.SELECTED_PROGRAM_ID";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_layout);
        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.menu_linear_layout);
        List<Program> programsList = Factory.getAllProgramsFromDb();
        for(final Program program : programsList){
            Button button = new Button(this);
            button.setText(program.getName());
            button.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    sendProgramToActivity(view, program.getId());
                    //startActivity(view);
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

    public void sendProgramToActivity(View view, int programId){
        Intent intent = new Intent(this, ProgramActivity.class);
        intent.putExtra(SELECTED_PROGRAM_ID, programId);
        startActivity(intent);
        //TODO: Maybe instead of sending program ID and getting workoutsList on every changing of activity
        //TODO: we will create some singletone of current Program and Workouts in it?

    }
}