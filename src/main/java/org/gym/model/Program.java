package org.gym.model;

import java.util.List;

/**
 * Program model entity for transfer data to presentation layer
 */
public class Program {

    private Long id;
    private String name;
    private String description;
    private int orderNumber;
    private List<ExerciseType> exerciseTypes;

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

    public int getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(int orderNumber) {
        this.orderNumber = orderNumber;
    }

    public List<ExerciseType> getExerciseTypes() {
        return exerciseTypes;
    }

    public void setExerciseTypes(List<ExerciseType> exerciseTypes) {
        this.exerciseTypes = exerciseTypes;
    }
}
