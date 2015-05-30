package org.gym.domain;

import java.util.List;

/**
 * Program is type of workout that contains excercises of different types
 */
public class ProgramEntity {

    public static final String TABLE_NAME = "PROGRAM";

    private Long id;
    private String name;
    private String description;

    public enum Column {
        ID,
        NAME,
        DESCRIPTION
    }

    public ProgramEntity() {
    }

    public ProgramEntity(String name, String description) {
        this.name = name;
        this.description = description;
    }

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
}
