package org.gym.domain;

import java.util.List;

/**
 * Workout is certain training day
 */
public class WorkoutEntity {

    public static final String TABLE_NAME = "WORKOUT";

    private Long id;
    private Long programId;
    private Long startDate;
    private Long endDate;
    private String status;

    public enum Column {
        ID,
        PROGRAM_ID,
        START_DATE,
        END_DATE,
        STATUS
    }

    public WorkoutEntity() {}

    public WorkoutEntity(Long programId, Long startDate, Long endDate, String status) {
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
