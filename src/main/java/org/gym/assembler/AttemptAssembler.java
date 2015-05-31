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
        return domainToModel(attemptEntity, true);
    }

    public List<Attempt> domainListToModelList(List<AttemptEntity> attemptEntities) {
        return domainListToModelList(attemptEntities, true);
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

    Attempt domainToModel(AttemptEntity attemptEntity, boolean withDependencies) {
        Attempt attempt = new Attempt();
        attempt.setId(attemptEntity.getId());
        attempt.setWeight(attemptEntity.getWeight());
        attempt.setCount(attemptEntity.getCount());
        attempt.setType(Attempt.Type.valueOf(attemptEntity.getType()));
        attempt.setComment(attemptEntity.getComment());
        // Need for correct filling field with cyclic dependencies
        if (withDependencies) {
            Exercise exercise = exerciseAssembler.domainToModel(exerciseRepository.find(attemptEntity.getExerciseId()));
            attempt.setExercise(exercise);
        }

        return attempt;
    }

    List<Attempt> domainListToModelList(List<AttemptEntity> attemptEntities, boolean withDependencies) {
        List<Attempt> attempts = new LinkedList<>();
        for (AttemptEntity attemptEntity : attemptEntities) {
            attempts.add(domainToModel(attemptEntity, withDependencies));
        }

        return attempts;
    }

    private void initFields() {
        this.exerciseAssembler = ExerciseAssembler.getInstance();
        this.exerciseRepository = ExerciseRepository.getInstance();
    }
}
