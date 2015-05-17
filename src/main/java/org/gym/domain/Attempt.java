package org.gym.domain;

/**
 * Attempt class is described attempt to certain exercise
 */
public class Attempt {

    public static final String TABLE_NAME = "ATTEMPT";

    private Long id;
    private Long exerciseId;
    private String weight;
    private int count;
    private Type type;
    private String comment;

    public enum Column {
        ID,
        EXERCISE_ID,
        WEIGHT,
        COUNT,
        TYPE,
        COMMENT
    }

    public enum Type {
        S, M, L, W
    }

    public Attempt() {
    }

    public Attempt(Long exerciseId, String weight, int count, Type type) {
        this.exerciseId = exerciseId;
        this.weight = weight;
        this.count = count;
        this.type = type;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getExerciseId() {
        return exerciseId;
    }

    public void setExerciseId(Long exerciseId) {
        this.exerciseId = exerciseId;
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

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
