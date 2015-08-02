package org.gym.domain;

/**
 * Program is type of workout that contains excercises of different types
 */
public class ProgramEntity implements HasNameAndDescription {

    public static final String TABLE_NAME = "PROGRAM";

    private Long id;
    private String name;
    private String description;
    private int order;

    public enum Column {
        ID,
        NAME,
        DESCRIPTION,
        ORDER
    }

    public ProgramEntity() {
    }

    public ProgramEntity(String name, String description, int order) {
        this.name = name;
        this.description = description;
        this.order = order;
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

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
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
