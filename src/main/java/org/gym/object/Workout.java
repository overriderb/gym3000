package org.gym.object;

import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by anni0913 on 07.07.2014.
 */

@DatabaseTable(tableName = "workouts")
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

    @DatabaseField(generatedId = true)
    private int id;

    @DatabaseField(canBeNull = false, dataType = DataType.STRING)
    private String name;

    @DatabaseField(dataType = DataType.STRING)
    private String description;

    @DatabaseField
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
