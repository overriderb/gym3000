package org.gym.domain;

import java.util.List;

/**
 * Program is type of workout that contains excercises of different types
 */
public class ProgramEntity {
//public class Program implements HasNameAndDescription{

    public static final String TABLE_NAME = "PROGRAM";

    private Long id;
    private Long userId;
    private String name;
    private String description;
    private int orderNumber;

    public enum Column {
        ID,
        PARENT_ID,
        NAME,
        DESCRIPTION,
        ORDER_NUMBER
    }

    public ProgramEntity() {
    }

    public ProgramEntity(Long userId, String name, String description, int orderNumber) {
        this.userId = userId;
        this.name = name;
        this.description = description;
        this.orderNumber = orderNumber;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
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

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "Program{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
