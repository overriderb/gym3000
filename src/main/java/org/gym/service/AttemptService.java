package org.gym.service;

import org.gym.domain.AttemptEntity;
import org.gym.repository.AttemptRepository;

import java.util.List;

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

    public Long save(Long exerciseId, int count, String weight, Long exerciseTypeId, String comment) {
        AttemptEntity attemptEntity = new AttemptEntity();
        attemptEntity.setExerciseId(exerciseId);
        attemptEntity.setCount(count);
        attemptEntity.setWeight(weight);
        attemptEntity.setExerciseId(exerciseTypeId);
        attemptEntity.setComment(comment);

        return attemptRepository.store(attemptEntity);
    }

    private void initFields() {
        attemptRepository = AttemptRepository.getInstance();
    }

    public List<Attempt> getAttempts(Context context, Long exerciseId){
        DatabaseHelper databaseHelper = new DatabaseHelper(context);
        return databaseHelper.getAttemptRepository().findAttemptListByParentId(exerciseId);
    }
}
