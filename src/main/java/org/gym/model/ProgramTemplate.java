package org.gym.model;

import java.util.List;

/**
 * Created by anni0913 on 25.12.2015.
 */
public class ProgramTemplate {

    private Long id;
    private String name;
    private String description;
    private List<ExerciseTemplate> exerciseTemplateList;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public List<ExerciseTemplate> getExerciseTemplateList() {
        return exerciseTemplateList;
    }

    public void setExerciseTemplateList(List<ExerciseTemplate> exerciseTemplateList) {
        this.exerciseTemplateList = exerciseTemplateList;
    }

    @Override
    public String toString() {
        return "ProgramTemplate{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
