package org.gym.assembler;

import org.gym.domain.AttemptEntity;
import org.gym.domain.ExerciseEntity;
import org.gym.domain.ExerciseTypeEntity;
import org.gym.domain.WorkoutEntity;
import org.gym.model.Attempt;
import org.gym.model.Exercise;
import org.gym.repository.AttemptRepository;
import org.gym.repository.ExerciseTypeRepository;
import org.gym.repository.WorkoutRepository;

import java.util.LinkedList;
import java.util.List;

/**
 * Assemble exercise domain entities to model entities and vice versa
 */
public class ExerciseAssembler {

    private static ExerciseAssembler instance;

    private ExerciseTypeRepository exerciseTypeRepository;
    private ExerciseTypeAssembler exerciseTypeAssembler;
    private WorkoutRepository workoutRepository;
    private WorkoutAssembler workoutAssembler;
    private AttemptRepository attemptRepository;
    private AttemptAssembler attemptAssembler;

    private ExerciseAssembler() {}

    public static ExerciseAssembler getInstance() {
        if (instance == null) {
            instance = new ExerciseAssembler();
            instance.initFields();
        }
        return instance;
    }

    public Exercise domainToModel(ExerciseEntity exerciseEntity) {
        return domainToModel(exerciseEntity, true);
    }

    public List<Exercise> domainListToModelList(List<ExerciseEntity> exerciseEntities) {
        return domainListToModelList(exerciseEntities, true);
    }

    public ExerciseEntity modelToDomain(Exercise exercise) {
        ExerciseEntity exerciseEntity = new ExerciseEntity();
        exerciseEntity.setId(exercise.getId());
        exerciseEntity.setLevel(exercise.getLevel().name());
        exerciseEntity.setExerciseTypeId(exercise.getExerciseType().getId());
        exerciseEntity.setWorkoutId(exercise.getWorkout().getId());

        return exerciseEntity;
    }

    Exercise domainToModel(ExerciseEntity exerciseEntity, boolean withDependencies) {
        Exercise exercise = new Exercise();
        exercise.setId(exerciseEntity.getId());
        exercise.setLevel(Exercise.Level.valueOf(exerciseEntity.getLevel()));
        ExerciseTypeEntity exerciseTypeEntity = exerciseTypeRepository.find(exerciseEntity.getExerciseTypeId());
        exercise.setExerciseType(exerciseTypeAssembler.domainToModel(exerciseTypeEntity));
        List<AttemptEntity> attemptEntities = attemptRepository.findByExerciseId(exerciseEntity.getId());
        List<Attempt> attempts = attemptAssembler.domainListToModelList(attemptEntities, false);
        exercise.setAttempts(fillAttempts(attempts, exercise));
        // Need for correct filling field with cyclic dependencies
        if (withDependencies) {
            WorkoutEntity workoutEntity = workoutRepository.find(exerciseEntity.getWorkoutId());
            exercise.setWorkout(workoutAssembler.domainToModel(workoutEntity));
        }

        return exercise;
    }

    List<Exercise> domainListToModelList(List<ExerciseEntity> exerciseEntities, boolean withDependencies) {
        List<Exercise> exercises = new LinkedList<>();
        for (ExerciseEntity exerciseEntity : exerciseEntities) {
            exercises.add(domainToModel(exerciseEntity, withDependencies));
        }

        return exercises;
    }

    private List<Attempt> fillAttempts(List<Attempt> attempts, Exercise exercise) {
        for (Attempt attempt : attempts) {
            attempt.setExercise(exercise);
        }

        return attempts;
    }

    private void initFields() {
        exerciseTypeRepository = ExerciseTypeRepository.getInstance();
        exerciseTypeAssembler = ExerciseTypeAssembler.getInstance();
        workoutRepository = WorkoutRepository.getInstance();
        workoutAssembler = WorkoutAssembler.getInstance();
        attemptRepository = AttemptRepository.getInstance();
        attemptAssembler = AttemptAssembler.getInstance();
    }
}
