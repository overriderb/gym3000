package org.gym.service;

/**
 * TODO: Add comment
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
}
