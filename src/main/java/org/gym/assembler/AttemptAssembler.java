package org.gym.assembler;

import org.gym.domain.AttemptEntity;
import org.gym.model.Attempt;
import org.gym.model.Exercise;
import org.gym.repository.ExerciseRepository;

import java.util.LinkedList;
import java.util.List;

/**
 * TODO: Add comment
 */
public class AttemptAssembler {

    private static AttemptAssembler instance;

    private ExerciseRepository exerciseRepository;
    private ExerciseAssembler exerciseAssembler;

    private AttemptAssembler() {}

    public static AttemptAssembler getInstance() {
        if (instance == null) {
            instance = new AttemptAssembler();
            instance.initFields();
        }
        return instance;
    }

    public Attempt domainToModel(AttemptEntity attemptEntity) {
        Attempt attempt = new Attempt();
        attempt.setId(attemptEntity.getId());
        Exercise exercise = exerciseAssembler.domainToModel(exerciseRepository.find(attemptEntity.getExerciseId()));
        attempt.setExercise(exercise);
        attempt.setWeight(attemptEntity.getWeight());
        attempt.setCount(attemptEntity.getCount());
        attempt.setType(Attempt.Type.valueOf(attemptEntity.getType()));
        attempt.setComment(attemptEntity.getComment());

        return attempt;
    }

    public List<Attempt> domainListToModelList(List<AttemptEntity> attemptEntities) {
        List<Attempt> attempts = new LinkedList<>();
        for (AttemptEntity attemptEntity : attemptEntities) {
            attempts.add(domainToModel(attemptEntity));
        }

        return attempts;
    }

    public AttemptEntity modelToDomain(Attempt attempt) {
        AttemptEntity attemptEntity = new AttemptEntity();
        attemptEntity.setId(attempt.getId());
        attemptEntity.setExerciseId(attempt.getExercise().getId());
        attemptEntity.setWeight(attempt.getWeight());
        attemptEntity.setCount(attempt.getCount());
        attemptEntity.setType(attempt.getType().name());
        attemptEntity.setComment(attempt.getComment());

        return attemptEntity;
    }

    private void initFields() {
        this.exerciseAssembler = ExerciseAssembler.getInstance();
        this.exerciseRepository = ExerciseRepository.getInstance();
    }
}
