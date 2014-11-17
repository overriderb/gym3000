package org.gym.object;

/**
 * Created by anni0913 on 22.10.2014.
 */
public class Set {

    public Set() {
    }

    public Set(long parentId, int weight, int times) {
        this.parentId = parentId;
        this.weight = weight;
        this.times = times;
    }

    private long id;

    private long parentId;

    private int weight;

    private int times;

    public long getParentId() {
        return parentId;
    }

    public void setParentId(long parentId) {
        this.parentId = parentId;
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

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
