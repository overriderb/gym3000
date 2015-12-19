package org.gym.service;

import org.gym.assembler.ExerciseTypeAssembler;
import org.gym.model.ExerciseType;
import org.gym.model.Program;
import org.gym.repository.ExerciseTypeRepository;

import java.util.List;

/**
 * Service for processing exercise type entities
 */
public class ExerciseTypeService {

    private static ExerciseTypeService instance;

    private ExerciseTypeRepository exerciseTypeRepository;
    private ExerciseTypeAssembler exerciseTypeAssembler;

    private ExerciseTypeService() {}

    public static ExerciseTypeService getInstance() {
        if (instance == null) {
            instance = new ExerciseTypeService();
            instance.initFields();
        }
        return instance;
    }

    public Long save() {
        return null;
    }

    public ExerciseType find() {
        return null;
    }

    public List<ExerciseType> findAll() {
        return null;
    }

    public List<ExerciseType> findByProgram(Program program) {
        return exerciseTypeAssembler.domainListToModelList(exerciseTypeRepository.findByProgramId(program.getId()));
    }

    private void initFields() {
        exerciseTypeRepository = ExerciseTypeRepository.getInstance();
        exerciseTypeAssembler = ExerciseTypeAssembler.getInstance();
    }
}
