package org.gym.object;

import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import org.gym.dao.HelperFactory;
import org.gym.logging.Logger;

import java.sql.SQLException;
import java.util.Collection;
import java.util.Date;
import java.util.LinkedList;

/**
 * Created by anni0913 on 15.10.2014.
 */
public class Exercise {

    @DatabaseField(generatedId = true)
    private int id;

    //@DatabaseField(canBeNull = false, foreign = true, foreignAutoRefresh = true)
    private Workout parentWorkout;

    @DatabaseField(canBeNull = false, dataType = DataType.DATE)
    private Date date;

    @DatabaseField(dataType = DataType.CHAR)
    private char typeOfExercise;

    //@ForeignCollectionField(eager = true)
    private Collection<Set> listOfSets = new LinkedList<Set>();

    public Exercise() {
    }

    public Exercise(Date date, char typeOfExercise, Collection<Set> listOfSets) {
        this.date = date;
        this.typeOfExercise = typeOfExercise;
        this.listOfSets = listOfSets;
    }

    public void addSet(Set set) {
        set.setParentExercise(this);
        try {
            HelperFactory.getHelper().getSetDAO().create(set);
        } catch (SQLException e) {
            Logger.error("SQL exception occurred while storing Set", e, Exercise.class);
        }
        listOfSets.add(set);
    }

    public void removeSet(Set set){
        listOfSets.remove(set);
        try {
            HelperFactory.getHelper().getSetDAO().delete(set);
        } catch (SQLException e) {
            Logger.error("SQL exception occurred while removing Set", e, Exercise.class);
        }
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public char getTypeOfExercise() {
        return typeOfExercise;
    }

    public void setTypeOfExercise(char typeOfExercise) {
        this.typeOfExercise = typeOfExercise;
    }

    public Collection<Set> getListOfSets() {
        return listOfSets;
    }

    public void setListOfSets(Collection<Set> listOfSets) {
        this.listOfSets = listOfSets;
    }

    public Workout getParentWorkout() {
        return parentWorkout;
    }

    public void setParentWorkout(Workout parentWorkout) {
        this.parentWorkout = parentWorkout;
    }
}
