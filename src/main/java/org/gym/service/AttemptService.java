package org.gym.service;

import android.content.Context;
import org.gym.domain.AttemptEntity;
import org.gym.repository.AttemptRepository;

/**
 * Service for processing logic about attempt entity
 */
public class AttemptService {

    private static AttemptService instance;

    private AttemptRepository attemptRepository;

    private AttemptService() {}

    public static AttemptService getInstance() {
        if (instance == null) {
            instance = new AttemptService();
            instance.initFields();
        }
        return instance;
    }

    public Long save(Long exerciseId, int count, String weight, Long excerciseTypeId, String comment) {
        AttemptEntity attemptEntity = new AttemptEntity();
        attemptEntity.setExerciseId(exerciseId);
        attemptEntity.setCount(count);
        attemptEntity.setWeight(weight);
        attemptEntity.setExerciseId(excerciseTypeId);
        attemptEntity.setComment(comment);

        return attemptRepository.store(attemptEntity);
    }

    private void initFields() {
        attemptRepository = AttemptRepository.getInstance();
    }
}
