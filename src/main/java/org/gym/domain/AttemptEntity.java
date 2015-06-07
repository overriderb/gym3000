package org.gym.domain;

/**
 * Attempt class is described attempt to certain exercise
 */
public class AttemptEntity {

    public static final String TABLE_NAME = "ATTEMPT";

    private Long id;
    private Long exerciseId;
    private String weight;
    private int count;
    private String comment;

    public enum Column {
        ID,
        EXERCISE_ID,
        WEIGHT,
        COUNT,
        COMMENT
    }

    public AttemptEntity() {}

    public AttemptEntity(Long exerciseId, String weight, int count) {
        this.exerciseId = exerciseId;
        this.weight = weight;
        this.count = count;
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

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
