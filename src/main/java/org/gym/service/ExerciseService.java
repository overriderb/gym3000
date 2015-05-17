package org.gym.service;

import android.content.Context;
import org.gym.domain.Exercise;
import org.gym.repository.DatabaseHelper;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Service for processing logic about exercise entity
 */
public class ExerciseService {

    public static final String GYM_DATE_FORMAT = "dd.MM.yyyy";

    public Long persistExercise(Context context, Long workoutId, Long exerciseTypeId) {
        DatabaseHelper databaseHelper = new DatabaseHelper(context);

        Exercise exercise = new Exercise();
        exercise.setWorkoutId(workoutId);
        exercise.setExerciseTypeId(exerciseTypeId);

        return databaseHelper.getExerciseRepository().store(exercise);
    }

    private String getCurrentDate() {
        SimpleDateFormat dateFormat = new SimpleDateFormat(GYM_DATE_FORMAT);
        return dateFormat.format(Calendar.getInstance().getTime());
    }
}
