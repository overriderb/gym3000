package org.gym.assembler;

import org.gym.domain.AttemptEntity;
import org.gym.domain.ExerciseEntity;
import org.gym.domain.ExerciseTypeEntity;
import org.gym.domain.WorkoutEntity;
import org.gym.model.Exercise;
import org.gym.repository.AttemptRepository;
import org.gym.repository.ExerciseTypeRepository;
import org.gym.repository.WorkoutRepository;

import java.util.LinkedList;
import java.util.List;

/**
 * TODO: Add comment
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
        Exercise exercise = new Exercise();
        exercise.setId(exerciseEntity.getId());
        ExerciseTypeEntity exerciseTypeEntity = exerciseTypeRepository.find(exerciseEntity.getExerciseTypeId());
        exercise.setExerciseType(exerciseTypeAssembler.domainToModel(exerciseTypeEntity));
        WorkoutEntity workoutEntity = workoutRepository.find(exerciseEntity.getWorkoutId());
        exercise.setWorkout(workoutAssembler.domainToModel(workoutEntity));
        List<AttemptEntity> attemptEntities = attemptRepository.findByExerciseId(exerciseEntity.getId());
        exercise.setAttempts(attemptAssembler.domainListToModelList(attemptEntities));

        return exercise;
    }

    public List<Exercise> domainListToModelList(List<ExerciseEntity> exerciseEntities) {
        List<Exercise> exercises = new LinkedList<>();
        for (ExerciseEntity exerciseEntity : exerciseEntities) {
            exercises.add(domainToModel(exerciseEntity));
        }

        return exercises;
    }

    public ExerciseEntity ModelToDomain(Exercise exercise) {
        ExerciseEntity exerciseEntity = new ExerciseEntity();
        exerciseEntity.setId(exercise.getId());
        exerciseEntity.setExerciseTypeId(exercise.getExerciseType().getId());
        exerciseEntity.setWorkoutId(exercise.getWorkout().getId());

        return exerciseEntity;
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
