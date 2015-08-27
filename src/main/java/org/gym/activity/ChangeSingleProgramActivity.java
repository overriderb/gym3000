package org.gym.activity;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import org.gym.logging.Logger;
import org.gym.model.Program;
import org.gym.service.ProgramService;

/**
 * Activity for changing and adding program
 */
public class ChangeSingleProgramActivity extends Activity {

    private ProgramService programService = ProgramService.getInstance();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.change_single_program_layout);
    }

    public void saveProgram(View view){
        EditText title = (EditText)findViewById(R.id.change_single_program_title_edit);
        EditText description = (EditText)findViewById(R.id.change_single_program_description_edit);

        Logger.info("Save program : " + title.getText().toString() + "\n" + description.getText().toString() + "\n" + 1, ChangeSingleProgramActivity.class);
        programService.save(title.getText().toString(), description.getText().toString(), 1);
        Intent intent = new Intent(this, MenuActivity.class);
        startActivity(intent);
    }
}
