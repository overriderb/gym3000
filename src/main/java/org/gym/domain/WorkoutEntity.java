package org.gym.domain;

/**
 * Workout is certain training day
 */
public class WorkoutEntity {

    public static final String TABLE_NAME = "WORKOUT";

    private Long id;
    private Long programId;
    private Long userId;
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

    public WorkoutEntity(Long userId, Long programId, Long startDate, Long endDate, String status) {
        this.userId = userId;
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

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
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

    @Override
    public String toString() {
        return "WorkoutEntity{" +
                "id=" + id +
                ", programId=" + programId +
                ", userId=" + userId +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", status='" + status + '\'' +
                '}';
    }
}
