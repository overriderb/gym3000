package org.gym.domain;




/**
 * Created by anni0913 on 07.07.2014.
 */
public class Workout {

    public static final String TABLE_NAME = "workout";

    private Long id;
    private Long parentId;
    private String name;
    private String description;
    private int pictureId;

    public enum Column {
        ID,
        PARENT_ID,
        NAME,
        PICTURE_ID,
        DESCRIPTION
    }

    public Workout() { }

    public Workout(Long parentProgram, String name, String description, int pictureId) {
        this.parentId = parentProgram;
        this.name = name;
        this.description = description;
        this.pictureId = pictureId;
    }

    public Workout(Program program, String name, String description, int pictureId) {
        this.parentId = program.getId();
        this.name = name;
        this.description = description;
        this.pictureId = pictureId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getPictureId() {
        return pictureId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPictureId(int pictureId) {
        this.pictureId = pictureId;
    }

    @Override
    public String toString() {
        return "Workout{" +
                "id=" + id +
                ", parentId=" + parentId +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", pictureId=" + pictureId +
                '}';
    }
}
