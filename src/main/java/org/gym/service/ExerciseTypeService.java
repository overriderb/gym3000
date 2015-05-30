package org.gym.service;

import org.gym.model.ExerciseType;

import java.util.List;

/**
 * TODO: Add comment
 */
public class ExerciseTypeService {

    private static ExerciseTypeService instance;

    private ExerciseTypeService() {}

    public static ExerciseTypeService getInstance() {
        if (instance == null) {
            instance = new ExerciseTypeService();
        }
        return instance;
    }

    public Long save() {
        return null;
    }

    public ExerciseType find() {
        return null;
    }

    public List<ExerciseType> findAll() {
        return null;
    }
}
