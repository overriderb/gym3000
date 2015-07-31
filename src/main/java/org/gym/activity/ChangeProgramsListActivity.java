package org.gym.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import org.gym.domain.Program;
import org.gym.repository.DatabaseHelper;
import org.gym.repository.ProgramRepository;

import java.util.ArrayList;
import java.util.List;

/**
 * Activity for changing order in list of programs, add program and change program
 */
public class ChangeProgramsListActivity extends Activity {

    private DatabaseHelper databaseHelper;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.change_programs_layout);

        databaseHelper = new DatabaseHelper(this);
        ProgramRepository programRepository =  databaseHelper.getProgramRepository();
        List<Program> programsList = programRepository.findAllProgramsList();

        ArrayList<String> resultList = new ArrayList<String>();
        for(Program item: programsList){
            resultList.add(item.getName());
        }

        ListView listView = (ListView)findViewById(R.id.change_programs_list_view);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.change_item_name_text_view, resultList);
        listView.setAdapter(adapter);
    }

    /**
     * onClick is in xml file
     */
    public void startChangeSingleProgramActivity(View view){
        Intent intent = new Intent(this, ChangeSingleProgramActivity.class);
        startActivity(intent);
    }

    @Override
    public void onStart() {
        super.onStart();
    }


}

