package org.gym.activity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import org.gym.adapter.HistoryListItemAdapter;
import org.gym.adapter.MenuSettingsListItemAdapter;
import org.gym.domain.Exercise;
import org.gym.domain.Program;
import org.gym.repository.DatabaseHelper;
import org.gym.repository.ProgramRepository;

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

        ListView listView = (ListView) findViewById(R.id.menu_settings_list_view);

        ArrayAdapter<Program> menuSettingsAdapter = new MenuSettingsListItemAdapter(this, R.layout.settings_list_item_layout, programsList);
        listView.setAdapter(menuSettingsAdapter);
    }

    @Override
    public void onStart() {
        super.onStart();
    }


}

