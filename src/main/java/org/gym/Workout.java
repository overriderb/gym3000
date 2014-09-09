package org.gym;

/**
 * Created by anni0913 on 07.07.2014.
 */
public class Workout {

    public Workout(){ }

    public Workout(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public Workout(String name, String description, int picturePath) {
        this.name = name;
        this.description = description;
        this.pictureId = picturePath;
    }

    private String name;
    private String description;
    private int pictureId;

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
