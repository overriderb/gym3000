package org.gym.domain;

/**
 * User entity
 */
public class UserEntity {

    public static final String TABLE_NAME = "USER";

    private Long id;
    private String name;

    public enum Column {
        ID,
        NAME
    }

    public UserEntity() {
    }

    public UserEntity(String name) {
        this.name = name;
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

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
