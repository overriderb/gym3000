package org.gym.service;

import android.content.Context;
import org.gym.assembler.ExerciseAssembler;
import org.gym.domain.ExerciseEntity;
import org.gym.model.Exercise;
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

    public Long save(Context context, Long workoutId, Long exerciseTypeId) {
        ExerciseEntity exerciseEntity = new ExerciseEntity();
        exerciseEntity.setWorkoutId(workoutId);
        exerciseEntity.setExerciseTypeId(exerciseTypeId);

        return exerciseRepository.store(exerciseEntity);
    }

    public List<Exercise> findByTypeId(Long typeId) {

        List<ExerciseEntity> exerciseEntities = exerciseRepository.findByTypeId(typeId);

        return exerciseAssembler.domainListToModelList(exerciseEntities);
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
