package org.gym.service;

import org.gym.assembler.ExerciseAssembler;
import org.gym.model.Exercise;
import org.gym.model.ExerciseType;
import org.gym.model.Workout;
import org.gym.repository.ExerciseRepository;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

/**
 * Service for processing logic about exercise entity
 */
public class ExerciseService {

    public static final String GYM_DATE_FORMAT = "dd.MM.yyyy";

    private static ExerciseService instance;

    private ExerciseAssembler exerciseAssembler;
    private ExerciseRepository exerciseRepository;

    private ExerciseService() {}

    public static ExerciseService getInstance() {
        if (instance == null) {
            instance = new ExerciseService();
            instance.initFields();
        }
        return instance;
    }

    public Long save(Exercise.Level level, Workout workout, ExerciseType exerciseType) {
        Exercise exercise = new Exercise();
        exercise.setLevel(level);
        exercise.setWorkout(workout);
        exercise.setExerciseType(exerciseType);

        return save(exercise);
    }

    public Long save(Exercise exercise) {
        return exerciseRepository.store(exerciseAssembler.modelToDomain(exercise));
    }

    public List<Exercise> findByType(ExerciseType exerciseType) {
        return exerciseAssembler.domainListToModelList(exerciseRepository.findByTypeId(exerciseType.getId()));
    }

    private void initFields() {
        exerciseAssembler = ExerciseAssembler.getInstance();
        exerciseRepository = ExerciseRepository.getInstance();
    }

    private String getCurrentDate() {
        SimpleDateFormat dateFormat = new SimpleDateFormat(GYM_DATE_FORMAT);
        return dateFormat.format(Calendar.getInstance().getTime());
    }
}
