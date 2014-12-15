package org.gym.activity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;
import org.gym.listView.DragNDropListView;
import org.gym.adapter.SettingsArrayAdapter;
import org.gym.cache.CurrentProgramCache;
import org.gym.domain.Workout;


import java.util.ArrayList;
import java.util.List;

/**
 * Created by anni0913 on 11.12.2014.
 */
public class ProgramSettingsActivity extends Activity {

    CurrentProgramCache cache;

    public ProgramSettingsActivity(){
       cache = CurrentProgramCache.getInstance();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.program_settings_layout);


        List<Workout> workoutList = cache.getWorkoutList();
        ArrayList<String> resultList = new ArrayList<String>();
        for(Workout item: workoutList){
            resultList.add(item.getName());
        }

        SettingsArrayAdapter adapter = new SettingsArrayAdapter(this, R.layout.text_view, resultList);
        DragNDropListView listView = (DragNDropListView) findViewById(R.id.program_settings_dnd_view);

        listView.setCheeseList(resultList);
        listView.setAdapter(adapter);
        listView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);


    }

    @Override
    public void onStart() {
        super.onStart();
    }
}
