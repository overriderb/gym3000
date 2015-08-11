package org.gym.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import org.gym.cache.CurrentProgramCache;
import org.gym.model.ExerciseType;


import java.util.ArrayList;
import java.util.List;

/**
 * Activity for changing order in list of workouts, add workout and change workout.
 * It opens current list of workouts which has one parent program.
 */
public class ChangeExerciseTypeListActivity extends Activity {

    CurrentProgramCache cache;

    public ChangeExerciseTypeListActivity(){
       cache = CurrentProgramCache.getInstance();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.change_exercise_types_layout);

        List<ExerciseType> exerciseTypes = cache.getExerciseTypes();
        ArrayList<String> resultList = new ArrayList<String>();
        for(ExerciseType item: exerciseTypes){
            resultList.add(item.getName());
        }

        ListView listView = (ListView)findViewById(R.id.change_exercise_types_list_view);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.change_item_name_text_view, resultList);
        listView.setAdapter(adapter);
    }

    /**
     * onClick is in xml file
     */
    public void startChangeSingleExerciseTypeActivity(View view){
        Intent intent = new Intent(this, ChangeSingleExerciseTypeActivity.class);
        startActivity(intent);
    }

    @Override
    public void onStart() {
        super.onStart();
    }
}
