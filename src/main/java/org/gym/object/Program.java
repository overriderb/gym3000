package org.gym.object;

import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.List;

/**
 * Created by anni0913 on 15.10.2014.
 */

@DatabaseTable(tableName = "programs")
public class Program {

    public Program() {
    }

    public Program(String name, String description, List<Workout> listOfWorkouts) {
        this.name = name;
        this.description = description;
        this.listOfWorkouts = listOfWorkouts;
    }

    @DatabaseField(generatedId = true)
    private int id;

    @DatabaseField(canBeNull = false, dataType = DataType.STRING)
    private String name;

    @DatabaseField(dataType = DataType.STRING)
    private String description;

    private List<Workout> listOfWorkouts;

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

    public List<Workout> getListOfWorkouts() {
        return listOfWorkouts;
    }

    public void setListOfWorkouts(List<Workout> listOfWorkouts) {
        this.listOfWorkouts = listOfWorkouts;
    }
}
