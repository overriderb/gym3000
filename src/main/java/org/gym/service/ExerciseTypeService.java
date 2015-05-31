package org.gym.service;

import org.gym.assembler.ExerciseTypeAssembler;
import org.gym.domain.ExerciseTypeEntity;
import org.gym.model.ExerciseType;
import org.gym.repository.ExerciseTypeRepository;

import java.util.List;

/**
 * TODO: Add comment
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

    public List<ExerciseType> findByProgramId(Long programId) {
        List<ExerciseTypeEntity> exerciseTypeEntityList = exerciseTypeRepository.findByProgramId(programId);
        return exerciseTypeAssembler.domainListToModelList(exerciseTypeEntityList);
    }

    private void initFields() {
        exerciseTypeRepository = ExerciseTypeRepository.getInstance();
        exerciseTypeAssembler = ExerciseTypeAssembler.getInstance();
    }
}
