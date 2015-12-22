package org.gym.domain;

import java.util.List;

/**
 *
 */
public class ProgramTemplateEntity {

    public static final String TABLE_NAME = "PROGRAM_TEMPLATE";

    private Long id;
    private String name;
    private String description;
    private List<ExerciseTemplateEntity> exerciseTemplateEntityList;

    public enum Column {
        ID,
        NAME,
        DESCRIPTION
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

    public List<ExerciseTemplateEntity> getExerciseTemplateEntityList() {
        return exerciseTemplateEntityList;
    }

    public void setExerciseTemplateEntityList(List<ExerciseTemplateEntity> exerciseTemplateEntityList) {
        this.exerciseTemplateEntityList = exerciseTemplateEntityList;
    }

    public Long getId() {
        return id;
    }
}
