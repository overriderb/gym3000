package org.gym.service;

/**
 * Service for processing logic about workout entity
 */
public class WorkoutService {

    private static WorkoutService instance;

    private WorkoutService() {}

    public static WorkoutService getInstance() {
        if (instance == null) {
            instance = new WorkoutService();
        }
        return instance;
    }

    public Long save() {
        return null;
    }

//    public Long persistWorkout(
//            Context context, Long parentProgram, String name, String description,
//            int pictureId, int order_number){
//        DatabaseHelper databaseHelper = new DatabaseHelper(context);
//
//        Workout workout = new Workout(parentProgram, name, description, pictureId, order_number);
//        return databaseHelper.getWorkoutRepository().storeWorkout(workout);
//    }
//
//    public List<Workout> getWorkouts(Context context, Long programId){
//        DatabaseHelper databaseHelper = new DatabaseHelper(context);
//
//        return databaseHelper.getWorkoutRepository().getWorkoutsListByParentId(programId);
//    }
}
