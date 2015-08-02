package org.gym.service;

import org.gym.assembler.WorkoutAssembler;
import org.gym.model.Exercise;
import org.gym.model.Program;
import org.gym.model.Workout;
import org.gym.repository.WorkoutRepository;

import java.util.List;

/**
 * Service for processing logic about workout entity
 */
public class WorkoutService {

    private static WorkoutService instance;

    private WorkoutAssembler workoutAssembler;
    private WorkoutRepository workoutRepository;

    private WorkoutService() {}

    public static WorkoutService getInstance() {
        if (instance == null) {
            instance = new WorkoutService();
            instance.initFields();
        }
        return instance;
    }

    public Long save(Program program, Long startDate, Long endDate, Workout.WorkoutStatus status, List<Exercise> exercises) {
        Workout workout = new Workout();
        workout.setProgram(program);
        workout.setStartDate(startDate);
        workout.setEndDate(endDate);
        workout.setStatus(status);
        workout.setExercises(exercises);

        return save(workout);
    }

    public Long save(Workout workout) {
        return workoutRepository.store(workoutAssembler.modelToDomain(workout));
    }

    public Workout find(Long id) {
        return workoutAssembler.domainToModel(workoutRepository.find(id));
    }

    public List<Workout> findByProgram(Program program) {
        return workoutAssembler.domainListToModelList(workoutRepository.findByProgramId(program.getId()));
    }

    private void initFields() {
        workoutAssembler = WorkoutAssembler.getInstance();
        workoutRepository = WorkoutRepository.getInstance();
    }
}
