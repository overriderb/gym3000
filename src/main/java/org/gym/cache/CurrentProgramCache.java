package org.gym.cache;

import org.gym.domain.Workout;

import java.util.List;

/**
 * Created by anni0913 on 01.12.2014.
 */
public class CurrentProgramCache {

    private static CurrentProgramCache instance = null;
    private String name;
    private String description;
    private List<Workout> workoutList;

    private CurrentProgramCache(){}

    public static CurrentProgramCache getInstance(){
        if(instance == null){
            instance = new CurrentProgramCache();
        }
        return instance;
    }

    public void setValues(String name, String description, List<Workout> workoutList){
        this.name = name;
        this.description = description;
        this.workoutList = workoutList;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public List<Workout> getWorkoutList() {
        return workoutList;
    }
}
