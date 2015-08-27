package org.gym.assembler;

import org.gym.domain.ExerciseTypeEntity;
import org.gym.domain.ProgramEntity;
import org.gym.model.ExerciseType;
import org.gym.repository.ProgramRepository;

import java.util.LinkedList;
import java.util.List;

/**
 * TODO: Add comment
 */
public class ExerciseTypeAssembler {

    private static ExerciseTypeAssembler instance;

    private ProgramRepository programRepository;
    private ProgramAssembler programAssembler;

    private ExerciseTypeAssembler() {}

    public static ExerciseTypeAssembler getInstance() {
        if (instance == null) {
            instance = new ExerciseTypeAssembler();
            instance.initFields();
        }
        return instance;
    }

    public ExerciseType domainToModel(ExerciseTypeEntity exerciseTypeEntity) {
        return domainToModel(exerciseTypeEntity, true);
    }

    public List<ExerciseType> domainListToModelList(List<ExerciseTypeEntity> exerciseTypeEntities) {
        return domainListToModelList(exerciseTypeEntities, true);
    }

    public ExerciseTypeEntity modelToDomain(ExerciseType exerciseType) {
        ExerciseTypeEntity exerciseTypeEntity = new ExerciseTypeEntity();
        exerciseTypeEntity.setId(exerciseType.getId());
        exerciseTypeEntity.setName(exerciseType.getName());
        exerciseTypeEntity.setDescription(exerciseType.getDescription());
        exerciseTypeEntity.setPictureId(exerciseType.getPictureId());
        exerciseTypeEntity.setProgramId(exerciseType.getProgram().getId());
        exerciseTypeEntity.setOrderNumber(exerciseType.getOrderNumber());

        return exerciseTypeEntity;
    }

    ExerciseType domainToModel(ExerciseTypeEntity exerciseTypeEntity, boolean withDependencies) {
        ExerciseType exerciseType = new ExerciseType();
        exerciseType.setId(exerciseTypeEntity.getId());
        exerciseType.setName(exerciseTypeEntity.getName());
        exerciseType.setDescription(exerciseTypeEntity.getDescription());
        exerciseType.setPictureId(exerciseTypeEntity.getPictureId());
        exerciseType.setOrderNumber(exerciseTypeEntity.getOrderNumber());
        // Need for correct filling field with cyclic dependencies
        if (withDependencies) {
            ProgramEntity programEntity = programRepository.find(exerciseTypeEntity.getProgramId());
            exerciseType.setProgram(programAssembler.domainToModel(programEntity));
        }

        return exerciseType;
    }

    List<ExerciseType> domainListToModelList(List<ExerciseTypeEntity> exerciseTypeEntities, boolean withDependencies) {
        List<ExerciseType> exerciseTypes = new LinkedList<>();
        for (ExerciseTypeEntity exerciseTypeEntity : exerciseTypeEntities) {
            exerciseTypes.add(domainToModel(exerciseTypeEntity, withDependencies));
        }

        return exerciseTypes;
    }

    private void initFields() {
        programRepository = ProgramRepository.getInstance();
        programAssembler = ProgramAssembler.getInstance();
    }
}
