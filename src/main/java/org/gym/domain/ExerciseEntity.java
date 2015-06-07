package org.gym.domain;

import java.util.List;

/**
 * Exercise is list of attempts
 */
public class ExerciseEntity {

    public static final String TABLE_NAME = "EXERCISE";

    private Long id;
    private String level;
    private Long workoutId;
    private Long exerciseTypeId;

    public enum Column {
        ID,
        LEVEL,
        TYPE,
        WORKOUT_ID
    }

    public ExerciseEntity() {
    }

    public ExerciseEntity(String level, Long workoutId, Long exerciseTypeId) {
        this.level = level;
        this.workoutId = workoutId;
        this.exerciseTypeId = exerciseTypeId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public Long getExerciseTypeId() {
        return exerciseTypeId;
    }

    public void setExerciseTypeId(Long exerciseTypeId) {
        this.exerciseTypeId = exerciseTypeId;
    }

    public Long getWorkoutId() {
        return workoutId;
    }

    public void setWorkoutId(Long workoutId) {
        this.workoutId = workoutId;
    }

}
