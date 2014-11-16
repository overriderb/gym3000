package org.gym.object;

import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;
import org.gym.dao.HelperFactory;

import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by anni0913 on 15.10.2014.
 */

@DatabaseTable(tableName = "programs")
public class Program {

    public Program() {
    }

    public Program(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public final static String NAME = "name";
    public final static String ID = "id";



    @DatabaseField(generatedId = true, columnName = ID)
    private int id;

    @DatabaseField(canBeNull = false, dataType = DataType.STRING, columnName = NAME)
    private String name;

    @DatabaseField(dataType = DataType.STRING)
    private String description;

    //@ForeignCollectionField(eager = false)
    //private Collection<Workout> listOfWorkouts = new LinkedList<Workout>();



    /*public void addWorkout(Workout workout){
        workout.setParentProgram(this);
        try {
            HelperFactory.getHelper().getWorkoutDAO().create(workout);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        listOfWorkouts.add(workout);
    }

    public void removeWorkout(Workout workout){
        listOfWorkouts.remove(workout);
        try {
            HelperFactory.getHelper().getWorkoutDAO().delete(workout);
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

    /*public Collection<Workout> getListOfWorkouts() {
        return listOfWorkouts;
    }

    public void setListOfWorkouts(Collection<Workout> listOfWorkouts) {
        this.listOfWorkouts = listOfWorkouts;
    }*/
}
