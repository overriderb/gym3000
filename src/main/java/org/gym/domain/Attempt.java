package org.gym.domain;

/**
 * Created by anni0913 on 22.10.2014.
 */
public class Attempt {

    public static final String TABLE_NAME = "ATTEMPT";

    private Long id;
    private Long parentId;
    private int weight;
    private int count;

    public enum Column {
        ID,
        PARENT_ID,
        WEIGHT,
        COUNT
    }

    public Attempt() {
    }

    public Attempt(Long parentId, int weight, int count) {
        this.parentId = parentId;
        this.weight = weight;
        this.count = count;
    }

    public Attempt(Exercise exercise, int weight, int count) {
        this.parentId = exercise.getId();
        this.weight = weight;
        this.count = count;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
