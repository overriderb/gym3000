package org.gym.domain;

/**
 * Created by anni0913 on 15.10.2014.
 */
public class Program implements HasNameAndDescription{

    public static final String TABLE_NAME = "PROGRAM";

    private Long id;
    private String name;
    private String description;
    private int orderNumber;

    public enum Column {
        ID,
        NAME,
        DESCRIPTION,
        ORDER_NUMBER
    }

    public Program() {
    }

    public Program(String name, String description, int order_number) {
        this.name = name;
        this.description = description;
        this.orderNumber = order_number;
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

    @Override
    public String toString() {
        return "Program{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
