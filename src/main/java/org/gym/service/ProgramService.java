package org.gym.service;

import org.gym.assembler.ProgramAssembler;
import org.gym.model.Program;
import org.gym.repository.ProgramRepository;

import java.util.List;

/**
 * TODO: Add comment
 */
public class ProgramService {

    private static ProgramService instance;

    private ProgramAssembler programAssembler;
    private ProgramRepository programRepository;

    private ProgramService() {}

    public static ProgramService getInstance() {
        if (instance == null) {
            instance = new ProgramService();
            instance.initFields();
        }
        return instance;
    }

    public Long save(String name, String description, int order) {
        Program program = new Program();
        program.setName(name);
        program.setDescription(description);
        program.setOrder(order);

        return save(program);
    }

    public Long save(Program program) {
        return programRepository.store(programAssembler.modelToDomain(program));
    }

    public Program find() {
        return null;
    }

    public List<Program> findAll() {
        return programAssembler.domainListToModelList(programRepository.findAll());
    }

    private void initFields() {
        programAssembler = ProgramAssembler.getInstance();
        programRepository = ProgramRepository.getInstance();
    }
}
