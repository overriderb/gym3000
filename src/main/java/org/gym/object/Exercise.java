package org.gym.object;

import java.util.Collection;
import java.util.Date;
import java.util.LinkedList;

/**
 * Created by anni0913 on 15.10.2014.
 */
public class Exercise {

    public Exercise() {
    }

    public Exercise(long parentId, String date, String typeOfExercise) {
        this.parentId = parentId;
        this.date = date;
        this.typeOfExercise = typeOfExercise;
    }

    public Exercise(Workout workout, String date, String typeOfExercise) {
        this.parentId = workout.getId();
        this.date = date;
        this.typeOfExercise = typeOfExercise;
    }


    private long id;
    private long parentId;
    private String date;   //TODO check type
    private String typeOfExercise;       //   TODO check type


    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTypeOfExercise() {
        return typeOfExercise;
    }

    public void setTypeOfExercise(String typeOfExercise) {
        this.typeOfExercise = typeOfExercise;
    }

    public long getParentId() {
        return parentId;
    }

    public void setParentId(long parentId) {
        this.parentId = parentId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
