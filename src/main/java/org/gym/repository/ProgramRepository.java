package org.gym.repository;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import org.gym.domain.Program;
import org.gym.logging.Logger;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by AndreyNick on 12.11.2014.
 */
public class ProgramRepository {

    private DatabaseHelper databaseHelper;
    private SQLiteDatabase database;

    public ProgramRepository(DatabaseHelper dh){
        this.databaseHelper = dh;
    }

    public List<Long> store(List<Program> programList){
        List<Long> storedIds = new LinkedList<>();
        for(Program program : programList){
            Long storedId = store(program);
            storedIds.add(storedId);
        }
        return storedIds;
    }

    public Long store(Program program) {
        instantiateDb();
        Logger.info("Storing program: " + program.toString(), ProgramRepository.class);

        ContentValues values = new ContentValues();
        values.put(Program.Column.NAME.name(), program.getName());
        values.put(Program.Column.DESCRIPTION.name(), program.getDescription());

        Long id = database.insert(Program.TABLE_NAME, null, values);
        program.setId(id);
        Logger.info("Program stored: " + program.toString(), ProgramRepository.class);

        closeDb();
        return id;
    }

    public Program find(Long programId) {
        instantiateDb();
        Logger.info("Finding program by id = " + programId, ProgramRepository.class);
        String query = "SELECT  * FROM " + Program.TABLE_NAME;

        Program program = null;

        Cursor cursor = database.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            program = new Program();
            program.setId(cursor.getLong(0));
            program.setName(cursor.getString(1));
            program.setDescription(cursor.getString(2));
        }

        closeDb();
        return program;
    }

    public List<Program> findAll() {
        instantiateDb();
        Logger.info("Finding all programs", ProgramRepository.class);
        List<Program> programs = new LinkedList<>();
        String query = "SELECT  * FROM " + Program.TABLE_NAME;

        Cursor cursor = database.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            do {
                Program program = new Program();
                program.setId(cursor.getLong(0));
                program.setName(cursor.getString(1));
                program.setDescription(cursor.getString(2));

                programs.add(program);
            } while (cursor.moveToNext());
        }

        closeDb();
        return programs;
    }

    private void instantiateDb(){
        database = databaseHelper.getWritableDatabase();
    }

    private void closeDb(){
        database.close();
    }

}
