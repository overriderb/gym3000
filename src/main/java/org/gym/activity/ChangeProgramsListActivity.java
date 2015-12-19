package org.gym.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import org.gym.model.Program;
import org.gym.service.ProgramService;

import java.util.ArrayList;
import java.util.List;

/**
 * Activity for changing order in list of programs, add program and change program
 */
public class ChangeProgramsListActivity extends Activity {

    private ProgramService programService = ProgramService.getInstance();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.change_programs_layout);

        List<Program> programsList = programService.findAll();

        ArrayList<String> resultList = new ArrayList<String>();
        for(Program item: programsList){
            resultList.add(item.getName());
        }

        ListView listView = (ListView)findViewById(R.id.change_programs_list_view);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.change_item_name_text_view, resultList);
        listView.setAdapter(adapter);
    }

    @Override
    public void onStart() {
        super.onStart();
        int activity = getIntent().getIntExtra(getString(R.string.activity_number), 0);
        switch (activity) {
            case R.integer.settings_menu_activity:
                overridePendingTransition(R.anim.left_slide_1, R.anim.left_slide_2);
                break;
            default:
                overridePendingTransition(R.anim.right_slide_1, R.anim.right_slide_2);
                break;
        }
    }

    @Override
    public void onPause(){
        super.onPause();
        overridePendingTransition(R.anim.right_slide_1, R.anim.right_slide_2);
    }

    //onClick is in xml file
    public void startChangeSingleProgramActivity(View view){
        Intent intent = new Intent(this, ChangeSingleProgramActivity.class);
        intent.putExtra(getString(R.string.activity_number), R.integer.change_programs_list_activity);
        startActivity(intent);
    }
}

