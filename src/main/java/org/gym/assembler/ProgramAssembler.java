package org.gym.assembler;

import org.gym.domain.ExerciseTypeEntity;
import org.gym.domain.ProgramEntity;
import org.gym.model.ExerciseType;
import org.gym.model.Program;
import org.gym.repository.ExerciseTypeRepository;

import java.util.LinkedList;
import java.util.List;

/**
 * Assemble program domain entities to model entities and vice versa
 */
public class ProgramAssembler {

    private static ProgramAssembler instance;

    private ExerciseTypeRepository exerciseTypeRepository;
    private ExerciseTypeAssembler exerciseTypeAssembler;

    private ProgramAssembler() {}

    public static ProgramAssembler getInstance() {
        if (instance == null) {
            instance = new ProgramAssembler();
            instance.initFields();
        }
        return instance;
    }

    public Program domainToModel(ProgramEntity programEntity) {
        Program program = new Program();
        program.setId(programEntity.getId());
        program.setName(programEntity.getName());
        program.setDescription(programEntity.getDescription());
        program.setOrderNumber(programEntity.getOrderNumber());
        List<ExerciseTypeEntity> exerciseTypeEntities = exerciseTypeRepository.findByProgramId(programEntity.getId());
        List<ExerciseType> exerciseTypes = exerciseTypeAssembler.domainListToModelList(exerciseTypeEntities, false);
        program.setExerciseTypes(fillExerciseTypes(exerciseTypes, program));

        return program;
    }

    public List<Program> domainListToModelList(List<ProgramEntity> programEntities) {
        List<Program> programs = new LinkedList<>();
        for (ProgramEntity programEntity : programEntities) {
            programs.add(domainToModel(programEntity));
        }

        return programs;
    }

    public ProgramEntity modelToDomain(Program program) {
        ProgramEntity programEntity = new ProgramEntity();
        programEntity.setId(program.getId());
        programEntity.setName(program.getName());
        programEntity.setDescription(program.getDescription());
        programEntity.setOrderNumber(program.getOrderNumber());

        return programEntity;
    }

    private List<ExerciseType> fillExerciseTypes(List<ExerciseType> exerciseTypes, Program program) {
        for (ExerciseType exerciseType : exerciseTypes) {
            exerciseType.setProgram(program);
        }
        return exerciseTypes;
    }

    private void initFields() {
        exerciseTypeRepository = ExerciseTypeRepository.getInstance();
        exerciseTypeAssembler = ExerciseTypeAssembler.getInstance();
    }
}
