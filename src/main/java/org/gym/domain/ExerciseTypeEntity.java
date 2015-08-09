package org.gym.domain;

/**
 * Exercise type describe details of exercise
 */
public class ExerciseTypeEntity {

    public static final String TABLE_NAME = "EXERCISE_TYPE";

    private Long id;
    private Long programId;
    private String name;
    private String description;
    private int pictureId;
    private int order;

    public enum Column {
        ID,
        PROGRAM_ID,
        NAME,
        DESCRIPTION,
        PICTURE_ID,
        ORDER
    }

    public ExerciseTypeEntity() {
    }

    public ExerciseTypeEntity(Long programId, String name, String description, int pictureId, int order) {
        this.programId = programId;
        this.name = name;
        this.description = description;
        this.pictureId = pictureId;
        this.order = order;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPictureId() {
        return pictureId;
    }

    public void setPictureId(int pictureId) {
        this.pictureId = pictureId;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }
}
