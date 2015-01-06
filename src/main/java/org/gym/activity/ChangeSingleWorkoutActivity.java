package org.gym.activity;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import org.gym.cache.CurrentProgramCache;
import org.gym.repository.DatabaseHelper;

/**
 * Activity for changing and adding workout
 */
public class ChangeSingleWorkoutActivity extends Activity {

    DatabaseHelper databaseHelper;
    CurrentProgramCache cache;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.change_single_workout_layout);
        databaseHelper = new DatabaseHelper(this);
        cache = CurrentProgramCache.getInstance();
    }

    public void saveWorkout(View view){
        EditText title = (EditText)view.findViewById(R.id.change_single_workout_title_edit);
        EditText description = (EditText)view.findViewById(R.id.change_single_workout_description_edit);

        Cursor allPrograms = databaseHelper.getProgramRepository().getAllProgramsCursor();
        //allPrograms.moveToLast();
        //Logger.info("Last program with highest order_number: " + allPrograms.getString(1) + " " + allPrograms.getString(3), ChangeSingleProgramActivity.class);
        //Program program = new Program(title.getText().toString(), description.getText().toString(), /*Integer.parseInt(allPrograms.getString(3)) + 1*/ 1);
        //Logger.info("Save program : " + program.getName() + "\n" + program.getDescription() + "\n" + program.getOrderNumber(), ChangeSingleProgramActivity.class);
        //databaseHelper.getProgramRepository().storeProgram(program);
        Intent intent = new Intent(this, ProgramActivity.class);
        startActivity(intent);
    }
}
