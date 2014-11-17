package org.gym.object;



/**
 * Created by anni0913 on 15.10.2014.
 */


public class Program {

    public Program() {
    }

    public Program(String name, String description) {
        this.name = name;
        this.description = description;
    }

    private long id;
    private String name;
    private String description;


    public long getId() {
        return id;
    }

    public void setId(long id) {
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
