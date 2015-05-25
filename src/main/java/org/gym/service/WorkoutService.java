package org.gym.service;

import android.content.Context;

import org.gym.domain.Workout;
import org.gym.repository.DatabaseHelper;

import java.util.List;

/**
 * Service for processing logic about workout entity
 */
public class WorkoutService {

    public Long persistWorkout(
            Context context, Long parentProgram, String name, String description,
            int pictureId, int order_number){
        DatabaseHelper databaseHelper = new DatabaseHelper(context);

        Workout workout = new Workout(parentProgram, name, description, pictureId, order_number);
        return databaseHelper.getWorkoutRepository().storeWorkout(workout);
    }

    public List<Workout> getWorkouts(Context context, Long programId){
        DatabaseHelper databaseHelper = new DatabaseHelper(context);

        return databaseHelper.getWorkoutRepository().getWorkoutsListByParentId(programId);
    }
}
