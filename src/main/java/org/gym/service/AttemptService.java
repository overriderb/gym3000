package org.gym.service;

import android.content.Context;
import org.gym.domain.Attempt;
import org.gym.repository.DatabaseHelper;

import java.util.List;

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

    public List<Attempt> getAttempts(Context context, Long exerciseId){
        DatabaseHelper databaseHelper = new DatabaseHelper(context);
        return databaseHelper.getAttemptRepository().findAttemptListByParentId(exerciseId);
    }
}
