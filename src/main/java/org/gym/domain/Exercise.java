package org.gym.domain;

/**
 * Created by anni0913 on 15.10.2014.
 */
public class Exercise {

    public static final String TABLE_NAME = "EXERCISE";

    private Long id;
    private Long parentId;
    private String date;
    private Enum type;

    public enum TYPE {
        S, M, L
    }

    public enum Column {
        ID,
        PARENT_ID,
        DATE,
        TYPE
    }

    public Exercise() {
    }

    public Exercise(Long parentId, String date, Enum type) {
        this.parentId = parentId;
        this.date = date;
        this.type = type;
    }

    public Exercise(Workout workout, String date, Enum type) {
        this.parentId = workout.getId();
        this.date = date;
        this.type = type;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Enum getType() {
        return type;
    }

    public void setType(Enum type) {
        this.type = type;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(long parentId) {
        this.parentId = parentId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
