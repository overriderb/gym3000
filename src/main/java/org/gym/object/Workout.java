package org.gym.object;




/**
 * Created by anni0913 on 07.07.2014.
 */


public class Workout {

    public Workout(){ }

    public Workout(long parentProgram, String name, String description, int pictureId) {
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

    private long id;
    private long parentId;
    private String name;
    private String description;
    private int pictureId;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getParentId() {
        return parentId;
    }

    public void setParentId(long parentId) {
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
}
