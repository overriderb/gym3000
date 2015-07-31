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

    public Long save() {
        return null;
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

//    public Long persistProgram(Context context, Long userId, String name, String description, int order_number){
//        DatabaseHelper databaseHelper = new DatabaseHelper(context);
//
//        Program program = new Program(userId, name, description, order_number);
//        return databaseHelper.getProgramRepository().storeProgram(program);
//    }
//
//    public List<Program> getPrograms(Context context){
//        DatabaseHelper databaseHelper = new DatabaseHelper(context);
//        return databaseHelper.getProgramRepository().findAllProgramsList();
//    }
}
