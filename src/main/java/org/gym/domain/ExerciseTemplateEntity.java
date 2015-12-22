package org.gym.domain;

/**
 * Created by anni0913 on 22.12.2015.
 */
public class ExerciseTemplateEntity {

    public static final String TABLE_NAME = "EXERCISE_TEMPLATE";

    private Long id;
    private String name;
    private String description;

    public enum Column {
        ID,
        NAME,
        DESCRIPTION
    }

    public ExerciseTemplateEntity() {
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

    public Long getId() {
        return id;
    }
}
