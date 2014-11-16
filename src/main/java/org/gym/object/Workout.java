package org.gym.object;

import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;
import org.gym.dao.HelperFactory;

import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedList;

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

    public Workout(Program parentProgram, String name, String description, int pictureId) {
        this.parentProgram = parentProgram;
        this.name = name;
        this.description = description;
        this.pictureId = pictureId;
    }

    public static final String PARENT_PROGRAM_ID = "parentProgram_id";


    @DatabaseField(generatedId = true)
    private int id;

    @DatabaseField(foreign = true, foreignAutoRefresh = true, columnName = PARENT_PROGRAM_ID)
    private Program parentProgram;

    @DatabaseField(canBeNull = false, dataType = DataType.STRING)
    private String name;

    @DatabaseField(dataType = DataType.STRING)
    private String description;

    @DatabaseField
    private int pictureId;

    //@ForeignCollectionField(eager = true)
    //private Collection<Exercise> listOfExercises = new LinkedList<Exercise>();

   /* public void addExercise(Exercise exercise){
        exercise.setParentWorkout(this);
        try {
            HelperFactory.getHelper().getExerciseDAO().create(exercise);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        listOfExercises.add(exercise);
    }

    public void removeExercise(Exercise exercise){
        listOfExercises.remove(exercise);
        try {
            HelperFactory.getHelper().getExerciseDAO().delete(exercise);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }*/

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Program getParentProgram() {
        return parentProgram;
    }

    public void setParentProgram(Program parentProgram) {
        this.parentProgram = parentProgram;
    }

   /* public Collection<Exercise> getListOfExercises() {
        return listOfExercises;
    }

    public void setListOfExercises(Collection<Exercise> listOfExercises) {
        this.listOfExercises = listOfExercises;
    }*/

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
