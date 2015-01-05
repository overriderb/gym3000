package org.gym.service;

import android.content.Context;
import org.gym.domain.Attempt;
import org.gym.repository.DatabaseHelper;

/**
 * Service for processing logic about attempt entity
 */
public class AttemptService {

    public Long persistAttempt(Context context, Long exerciseId, int count, String weight) {
        DatabaseHelper databaseHelper = new DatabaseHelper(context);

        Attempt attempt = new Attempt();
        attempt.setParentId(exerciseId);
        attempt.setCount(count);
        attempt.setWeight(weight);

        return databaseHelper.getAttemptRepository().storeAttempt(attempt);
    }
}
