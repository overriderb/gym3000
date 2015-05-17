package org.gym.domain;

import java.util.List;

/**
 * Workout is certain training day
 */
public class Workout {

    public static final String TABLE_NAME = "WORKOUT";

    private Long id;
    private Long programId;
    private Long startDate;
    private Long endDate;
    private WorkoutStatus status;
    private List<Exercise> exercises;

    public enum WorkoutStatus {
        IN_PROGRESS, FINISHED, NOT_STARTED
    }

    public enum Column {
        ID,
        PROGRAM_ID,
        START_DATE,
        END_DATE,
        STATUS
    }

    public Workout() {
    }

    public Workout(Long programId, Long startDate) {
        this.programId = programId;
        this.startDate = startDate;
    }

    public Workout(Long programId, Long startDate, Long endDate, WorkoutStatus status) {
        this.programId = programId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getProgramId() {
        return programId;
    }

    public void setProgramId(Long programId) {
        this.programId = programId;
    }

    public Long getStartDate() {
        return startDate;
    }

    public void setStartDate(Long startDate) {
        this.startDate = startDate;
    }

    public Long getEndDate() {
        return endDate;
    }

    public void setEndDate(Long endDate) {
        this.endDate = endDate;
    }

    public WorkoutStatus getStatus() {
        return status;
    }

    public void setStatus(WorkoutStatus status) {
        this.status = status;
    }

    public List<Exercise> getExercises() {
        return exercises;
    }

    public void setExercises(List<Exercise> exercises) {
        this.exercises = exercises;
    }
}
