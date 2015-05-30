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
        ExerciseType exerciseType = new ExerciseType();
        exerciseType.setId(exerciseTypeEntity.getId());
        exerciseType.setName(exerciseTypeEntity.getName());
        exerciseType.setDescription(exerciseTypeEntity.getDescription());
        exerciseType.setPictureId(exerciseTypeEntity.getPictureId());
        ProgramEntity programEntity = programRepository.find(exerciseTypeEntity.getProgramId());
//        exerciseType.setProgram(programAssembler.domainToModel(programEntity));

        return exerciseType;
    }

    public List<ExerciseType> domainListToModelList(List<ExerciseTypeEntity> exerciseTypeEntities) {
        List<ExerciseType> exerciseTypes = new LinkedList<>();
        for (ExerciseTypeEntity exerciseTypeEntity : exerciseTypeEntities) {
            exerciseTypes.add(domainToModel(exerciseTypeEntity));
        }

        return exerciseTypes;
    }

    public ExerciseTypeEntity modelToDomain(ExerciseType exerciseType) {
        ExerciseTypeEntity exerciseTypeEntity = new ExerciseTypeEntity();
        exerciseTypeEntity.setId(exerciseType.getId());
        exerciseTypeEntity.setName(exerciseType.getName());
        exerciseTypeEntity.setDescription(exerciseType.getDescription());
        exerciseTypeEntity.setPictureId(exerciseType.getPictureId());
        exerciseTypeEntity.setProgramId(exerciseType.getProgram().getId());

        return exerciseTypeEntity;
    }

    private void initFields() {
        programRepository = ProgramRepository.getInstance();
        programAssembler = ProgramAssembler.getInstance();
    }
}
