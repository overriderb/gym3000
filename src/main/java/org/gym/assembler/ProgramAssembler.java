package org.gym.assembler;

import org.gym.domain.ExerciseTypeEntity;
import org.gym.domain.ProgramEntity;
import org.gym.model.Program;
import org.gym.repository.ExerciseTypeRepository;

import java.util.LinkedList;
import java.util.List;

/**
 * TODO: Add comment
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
        List<ExerciseTypeEntity> exerciseTypeEntities = exerciseTypeRepository.findByProgramId(programEntity.getId());
        program.setExerciseTypes(exerciseTypeAssembler.domainListToModelList(exerciseTypeEntities));

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

        return programEntity;
    }

    private void initFields() {
        exerciseTypeRepository = ExerciseTypeRepository.getInstance();
        exerciseTypeAssembler = ExerciseTypeAssembler.getInstance();
    }
}
