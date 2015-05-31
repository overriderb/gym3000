package org.gym.assembler;

import org.gym.domain.ExerciseEntity;
import org.gym.domain.ProgramEntity;
import org.gym.domain.WorkoutEntity;
import org.gym.model.Exercise;
import org.gym.model.Workout;
import org.gym.repository.ExerciseRepository;
import org.gym.repository.ProgramRepository;

import java.util.List;

/**
 * TODO: Add comment
 */
public class WorkoutAssembler {

    private static WorkoutAssembler instance;

    private ExerciseRepository exerciseRepository;
    private ExerciseAssembler exerciseAssembler;
    private ProgramRepository programRepository;
    private ProgramAssembler programAssembler;

    private WorkoutAssembler() {}

    public static WorkoutAssembler getInstance() {
        if (instance == null) {
            instance = new WorkoutAssembler();
            instance.initFields();
        }
        return instance;
    }

    public Workout domainToModel(WorkoutEntity workoutEntity) {
        Workout workout = new Workout();
        workout.setId(workoutEntity.getId());
        workout.setStartDate(workoutEntity.getStartDate());
        workout.setEndDate(workoutEntity.getEndDate());
        workout.setStatus(Workout.WorkoutStatus.valueOf(workoutEntity.getStatus()));
        ProgramEntity programEntity = programRepository.find(workoutEntity.getProgramId());
        workout.setProgram(programAssembler.domainToModel(programEntity));
        List<ExerciseEntity> exerciseEntities = exerciseRepository.findByWorkoutId(workoutEntity.getId());
        List<Exercise> exercises = exerciseAssembler.domainListToModelList(exerciseEntities, false);
        workout.setExercises(fillExercises(exercises, workout));

        return workout;
    }

    public WorkoutEntity modelToDomain(Workout workout) {
        WorkoutEntity workoutEntity = new WorkoutEntity();
        workoutEntity.setId(workout.getId());
        workoutEntity.setStartDate(workout.getStartDate());
        workoutEntity.setEndDate(workout.getEndDate());
        workoutEntity.setStatus(workout.getStatus().name());
        workoutEntity.setProgramId(workout.getProgram().getId());

        return workoutEntity;
    }

    private List<Exercise> fillExercises(List<Exercise> exercises, Workout workout) {
        for (Exercise exercise : exercises) {
            exercise.setWorkout(workout);
        }
        return exercises;
    }

    private void initFields() {
        exerciseRepository = ExerciseRepository.getInstance();
        exerciseAssembler = ExerciseAssembler.getInstance();
        programRepository = ProgramRepository.getInstance();
        programAssembler = ProgramAssembler.getInstance();
    }
}
