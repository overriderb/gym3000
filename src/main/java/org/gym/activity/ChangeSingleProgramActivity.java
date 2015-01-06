package org.gym.activity;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import org.gym.cache.CurrentProgramCache;
import org.gym.domain.Program;
import org.gym.logging.Logger;
import org.gym.repository.DatabaseHelper;

/**
 * Activity for changing and adding program
 */
public class ChangeSingleProgramActivity extends Activity {

    private DatabaseHelper databaseHelper;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.change_single_program_layout);
        databaseHelper = new DatabaseHelper(this);
    }

    public void saveProgram(View view){
        EditText title = (EditText)findViewById(R.id.change_single_program_title_edit);
        EditText description = (EditText)findViewById(R.id.change_single_program_description_edit);

        Cursor allPrograms = databaseHelper.getProgramRepository().getAllProgramsCursor();
        allPrograms.moveToLast();
        Logger.info("Last program with highest order_number: " + allPrograms.getString(1) + " " + allPrograms.getString(3), ChangeSingleProgramActivity.class);
        Program program = new Program(title.getText().toString(), description.getText().toString(), /*Integer.parseInt(allPrograms.getString(3)) + 1*/ 1);
        Logger.info("Save program : " + program.getName() + "\n" + program.getDescription() + "\n" + program.getOrderNumber(), ChangeSingleProgramActivity.class);
        //databaseHelper.getProgramRepository().storeProgram(program);
        Intent intent = new Intent(this, MenuActivity.class);
        startActivity(intent);
    }
}
