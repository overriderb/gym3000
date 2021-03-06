package org.gym.cache;

import org.gym.domain.Workout;

import java.util.List;

/**
 * This cache is used for coordinating History and Program activities
 * When user selects some program on Main activity, this program is added to cache and is used till
 * user moves back to main menu
 */
public class CurrentProgramCache {

    private static CurrentProgramCache instance = null;
    private Long id;
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

    public void setValues(Long id, String name, String description, List<Workout> workoutList){
        this.id = id;
        this.name = name;
        this.description = description;
        this.workoutList = workoutList;
    }

    public void setWorkout(Workout workout){
        workoutList.add(workout);
    }

    public Long getId() {
        return id;
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
