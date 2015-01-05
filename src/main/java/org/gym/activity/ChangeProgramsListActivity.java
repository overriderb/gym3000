package org.gym.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import org.gym.listView.DragNDropListView;
import org.gym.adapter.SettingsArrayAdapter;
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
        /*for(Program item: programsList){
            resultList.add(item.getName());
        }*/
        resultList.add("One");
        resultList.add("Two");
        resultList.add("Two");
        resultList.add("Three");
        resultList.add("Four");
        resultList.add("Five");
        resultList.add("Six");
        resultList.add("Seven");
        resultList.add("Eight");
        resultList.add("Nine");
        resultList.add("Ten");

        SettingsArrayAdapter adapter = new SettingsArrayAdapter(this, R.layout.dnd_text_view, resultList);
        DragNDropListView listView = (DragNDropListView) findViewById(R.id.change_programs_dnd_view);

        listView.setCheeseList(resultList);
        listView.setAdapter(adapter);
        listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
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

