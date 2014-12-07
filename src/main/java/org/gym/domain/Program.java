package org.gym.domain;

/**
 * Created by anni0913 on 15.10.2014.
 */
public class Program {

    public static final String TABLE_NAME = "PROGRAM";

    private Long id;
    private String name;
    private String description;

    public enum Column {
        ID,
        NAME,
        DESCRIPTION
    }

    public Program() {
    }

    public Program(String name, String description) {
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
