package org.gym.domain;




/**
 * Created by anni0913 on 07.07.2014.
 */
public class Workout implements HasNameAndDescription{

    public static final String TABLE_NAME = "WORKOUT";

    private Long id;
    private Long parentId;
    private String name;
    private String description;
    private int pictureId;
    private int orderNumber;

    public enum Column {
        ID,
        PARENT_ID,
        NAME,
        PICTURE_ID,
        DESCRIPTION,
        ORDER_NUMBER
    }

    public Workout() { }

    public Workout(Long parentProgram, String name, String description, int pictureId, int order_number) {
        this.parentId = parentProgram;
        this.name = name;
        this.description = description;
        this.pictureId = pictureId;
        this.orderNumber = order_number;
    }

    public Workout(Program program, String name, String description, int pictureId, int order_number) {
        this.parentId = program.getId();
        this.name = name;
        this.description = description;
        this.pictureId = pictureId;
        this.orderNumber = order_number;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getDescription() {
        return description;
    }

    public int getPictureId() {
        return pictureId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPictureId(int pictureId) {
        this.pictureId = pictureId;
    }

    public int getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(int orderNumber) {
        this.orderNumber = orderNumber;
    }

    @Override
    public String toString() {
        return "Workout{" +
                "id=" + id +
                ", parentId=" + parentId +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", pictureId=" + pictureId +
                '}';
    }
}
