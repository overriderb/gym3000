package org.gym.activity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;
import org.gym.listView.DragNDropListView;
import org.gym.adapter.SettingsArrayAdapter;
import org.gym.domain.Program;
import org.gym.repository.DatabaseHelper;
import org.gym.repository.ProgramRepository;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by anni0913 on 11.12.2014.
 */
public class MenuSettingsActivity extends Activity {

    private DatabaseHelper databaseHelper;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_settings_layout);

        databaseHelper = new DatabaseHelper(this);
        ProgramRepository programRepository =  databaseHelper.getProgramRepository();
        List<Program> programsList = programRepository.findAllProgramsList();



        ArrayList<String> resultList = new ArrayList<String>();
        for(Program item: programsList){
            resultList.add(item.getName());
        }

        SettingsArrayAdapter adapter = new SettingsArrayAdapter(this, R.layout.text_view, resultList);
        DragNDropListView listView = (DragNDropListView) findViewById(R.id.menu_settings_dnd_view);

        listView.setCheeseList(resultList);
        listView.setAdapter(adapter);
        listView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
    }

    @Override
    public void onStart() {
        super.onStart();
    }


}

