package org.gym.domain;

/**
 * Created by anni0913 on 15.10.2014.
 */
public class Program implements HasNameAndDescription{

    public static final String TABLE_NAME = "PROGRAM";

    private Long id;
    private String name;
    private String description;
    private Long order_number;

    public enum Column {
        ID,
        NAME,
        DESCRIPTION,
        ORDER_NUMBER
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

    public Long getOrder_number() {
        return order_number;
    }

    public void setOrder_number(Long order_number) {
        this.order_number = order_number;
    }
}
