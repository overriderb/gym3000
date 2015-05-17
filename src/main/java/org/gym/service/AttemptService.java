package org.gym.service;

import android.content.Context;
import org.gym.domain.Attempt;
import org.gym.repository.DatabaseHelper;

/**
 * Service for processing logic about attempt entity
 */
public class AttemptService {

    public Long persistAttempt(Context context, Long exerciseId, int count, String weight, Long excerciseTypeId, String comment) {
        DatabaseHelper databaseHelper = new DatabaseHelper(context);

        Attempt attempt = new Attempt();
        attempt.setExerciseId(exerciseId);
        attempt.setCount(count);
        attempt.setWeight(weight);
        attempt.setExerciseId(excerciseTypeId);
        attempt.setComment(comment);

        return databaseHelper.getAttemptRepository().storeAttempt(attempt);
    }
}
