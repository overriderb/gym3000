package org.gym.service;

import org.gym.assembler.AttemptAssembler;
import org.gym.model.Attempt;
import org.gym.model.Exercise;
import org.gym.repository.AttemptRepository;

import java.util.List;

/**
 * Service for processing logic about attempt entity
 */
public class AttemptService {

    private static AttemptService instance;

    private AttemptRepository attemptRepository;
    private AttemptAssembler attemptAssembler;

    private AttemptService() {}

    public static AttemptService getInstance() {
        if (instance == null) {
            instance = new AttemptService();
            instance.initFields();
        }
        return instance;
    }

    public Long save(Exercise exercise, int count, String weight, String comment) {
        Attempt attempt = new Attempt();
        attempt.setExercise(exercise);
        attempt.setCount(count);
        attempt.setWeight(weight);
        attempt.setComment(comment);

        return save(attempt);
    }

    public Long save(Attempt attempt) {
        return attemptRepository.store(attemptAssembler.modelToDomain(attempt));
    }

    public List<Attempt> findByExercise(Exercise exercise) {
        return attemptAssembler.domainListToModelList(attemptRepository.findByExerciseId(exercise.getId()));
    }

    private void initFields() {
        attemptRepository = AttemptRepository.getInstance();
        attemptAssembler = AttemptAssembler.getInstance();
    }

}
