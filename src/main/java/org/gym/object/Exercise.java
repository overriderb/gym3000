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

    public Exercise(long parentId, Date date, char typeOfExercise) {
        this.parentId = parentId;
        this.date = date;
        this.typeOfExercise = typeOfExercise;
    }


    private long id;
    private long parentId;
    private Date date;
    private char typeOfExercise;


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
