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

    public Long persistExercise(Context context, Long workoutId, String exerciseType) {
        DatabaseHelper databaseHelper = new DatabaseHelper(context);

        Exercise exercise = new Exercise();
        exercise.setDate(getCurrentDate());
        exercise.setParentId(workoutId);

        for (Exercise.TYPE type: Exercise.TYPE.values()) {
            if (type.name().equals(exerciseType)) {
                exercise.setType(type);
            }
        }

        return databaseHelper.getExerciseRepository().storeExercise(exercise);
    }

    private String getCurrentDate() {
        SimpleDateFormat dateFormat = new SimpleDateFormat(GYM_DATE_FORMAT);
        return dateFormat.format(Calendar.getInstance().getTime());
    }
}
