package org.gym.domain;

/**
 * Created by anni0913 on 22.10.2014.
 */
public class Attempt {

    public static final String TABLE_NAME = "ATTEMPT";

    private Long id;
    private Long parentId;
    private String weight;
    private int count;

    public enum Column {
        ID,
        PARENT_ID,
        WEIGHT,
        COUNT
    }

    public Attempt() {
    }

    public Attempt(Long parentId, String weight, int count) {
        this.parentId = parentId;
        this.weight = weight;
        this.count = count;
    }

    public Attempt(Exercise exercise, String weight, int count) {
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

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
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

    @Override
    public String toString() {
        return "Attempt{" +
                "id=" + id +
                ", parentId=" + parentId +
                ", weight='" + weight + '\'' +
                ", count=" + count +
                '}';
    }
}
