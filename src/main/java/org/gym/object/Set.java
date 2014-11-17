package org.gym.object;

import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;

/**
 * Created by anni0913 on 22.10.2014.
 */
public class Set {

    @DatabaseField(generatedId = true)
    private int id;

    //@DatabaseField(canBeNull = false, foreign = true, foreignAutoRefresh = true)
    private Exercise parentExercise;

    @DatabaseField(dataType = DataType.INTEGER)
    private int weight;

    @DatabaseField(dataType = DataType.INTEGER)
    private int times;

    public Set() {
    }

    public Set(int weight, int times) {
        this.weight = weight;
        this.times = times;
    }

    public Exercise getParentExercise() {
        return parentExercise;
    }

    public void setParentExercise(Exercise parentExercise) {
        this.parentExercise = parentExercise;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getTimes() {
        return times;
    }

    public void setTimes(int times) {
        this.times = times;
    }




}
