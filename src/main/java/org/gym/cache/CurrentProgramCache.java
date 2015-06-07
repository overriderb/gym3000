package org.gym.cache;

//import org.gym.domain.Workout;

import org.gym.model.ExerciseType;

import java.util.List;

/**
 * Created by anni0913 on 01.12.2014.
 */
public class CurrentProgramCache {

    private static CurrentProgramCache instance = null;
    private String name;
    private String description;
    private List<ExerciseType> exerciseTypes;

    private CurrentProgramCache(){}

    public static CurrentProgramCache getInstance(){
        if(instance == null){
            instance = new CurrentProgramCache();
        }
        return instance;
    }

    public void setValues(String name, String description, List<ExerciseType> exerciseTypes){
        this.name = name;
        this.description = description;
        this.exerciseTypes = exerciseTypes;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public List<ExerciseType> getExerciseTypes() {
        return exerciseTypes;
    }
}
