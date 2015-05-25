package org.gym.service;

import android.content.Context;

import org.gym.domain.Program;
import org.gym.repository.DatabaseHelper;

import java.util.List;

/**
 * Service for processing logic about program entity
 */
public class ProgramService {

    public Long persistProgram(Context context, String name, String description, int order_number){
        DatabaseHelper databaseHelper = new DatabaseHelper(context);

        Program program = new Program(name, description, order_number);
        return databaseHelper.getProgramRepository().storeProgram(program);
    }

    public List<Program> getPrograms(Context context){
        DatabaseHelper databaseHelper = new DatabaseHelper(context);
        return databaseHelper.getProgramRepository().findAllProgramsList();
    }
}
