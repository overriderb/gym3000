package org.gym.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import org.gym.cache.CurrentProgramCache;
import org.gym.logging.Logger;
import org.gym.model.ExerciseType;

/**
 * Activity for changing and adding exercise type
 */
public class ChangeSingleExerciseTypeActivity extends Activity {

    CurrentProgramCache cache;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.change_single_exercise_type_layout);
        cache = CurrentProgramCache.getInstance();

    }

    public void saveExerciseType(View view) {
        EditText title = (EditText)findViewById(R.id.change_single_exercise_type_title_edit);
        EditText description = (EditText)findViewById(R.id.change_single_exercise_type_description_edit);

        Logger.info("Title :" + title.getText().toString() + " Descr : " + description.getText().toString(), ChangeSingleExerciseTypeActivity.class);

        ExerciseType exerciseType = new ExerciseType();
        exerciseType.setId(cache.getId());
        exerciseType.setName(title.getText().toString());
        exerciseType.setDescription(description.getText().toString());
        //TODO: Remove magic numbers!
        exerciseType.setOrderNumber(0);
        exerciseType.setPictureId(12);

        cache.addExerciseType(exerciseType);

        Intent intent = new Intent(this, ProgramActivity.class);
        startActivity(intent);
    }
}
