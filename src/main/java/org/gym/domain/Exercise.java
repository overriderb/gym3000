package org.gym.domain;

import java.util.List;

/**
 * Exercise is list of attempts
 */
public class Exercise {

    public static final String TABLE_NAME = "EXERCISE";

    private Long id;
    private Long workoutId;
    private Long exerciseTypeId;
    private List<Attempt> attempts;

    public enum Column {
        ID,
        TYPE,
        WORKOUT_ID
    }

    public Exercise() {
    }

    public Exercise(Long workoutId, Long exerciseTypeId) {
        this.workoutId = workoutId;
        this.exerciseTypeId = exerciseTypeId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public List<Attempt> getAttempts() {
        return attempts;
    }

    public void setAttempts(List<Attempt> attempts) {
        this.attempts = attempts;
    }
}
