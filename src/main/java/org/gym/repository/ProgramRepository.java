package org.gym.repository;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import org.gym.domain.ProgramEntity;
import org.gym.logging.Logger;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by AndreyNick on 12.11.2014.
 */
public class ProgramRepository {

    private static ProgramRepository instance;

    private DatabaseHelper databaseHelper;
    private SQLiteDatabase database;

    private ProgramRepository(DatabaseHelper databaseHelper){
        this.databaseHelper = databaseHelper;
    }

    public static ProgramRepository getInstance() {
        if (instance == null) {
            instance = new ProgramRepository(DatabaseHelper.getInstance());
        }
        return instance;
    }

    public List<Long> store(List<ProgramEntity> programEntityList){
        List<Long> storedIds = new LinkedList<>();
        for(ProgramEntity programEntity : programEntityList){
            Long storedId = store(programEntity);
            storedIds.add(storedId);
        }
        return storedIds;
    }

    public Long store(ProgramEntity programEntity) {
        instantiateDb();
        Logger.info("Storing program: " + programEntity.toString(), ProgramRepository.class);

        ContentValues values = new ContentValues();
        values.put(ProgramEntity.Column.NAME.name(), programEntity.getName());
        values.put(ProgramEntity.Column.DESCRIPTION.name(), programEntity.getDescription());

        Long id = database.insert(ProgramEntity.TABLE_NAME, null, values);
        programEntity.setId(id);
        Logger.info("Program stored: " + programEntity.toString(), ProgramRepository.class);

        closeDb();
        return id;
    }

    public ProgramEntity find(Long programId) {
        instantiateDb();
        Logger.info("Finding program by id = " + programId, ProgramRepository.class);
        String query = "SELECT  * FROM " + ProgramEntity.TABLE_NAME;

        ProgramEntity programEntity = null;

        Cursor cursor = database.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            programEntity = new ProgramEntity();
            programEntity.setId(cursor.getLong(0));
            programEntity.setName(cursor.getString(1));
            programEntity.setDescription(cursor.getString(2));
        }

        closeDb();
        return programEntity;
    }

    public List<ProgramEntity> findAll() {
        instantiateDb();
        Logger.info("Finding all programs", ProgramRepository.class);
        List<ProgramEntity> programEntities = new LinkedList<>();
        String query = "SELECT  * FROM " + ProgramEntity.TABLE_NAME;

        Cursor cursor = database.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            do {
                ProgramEntity programEntity = new ProgramEntity();
                programEntity.setId(cursor.getLong(0));
                programEntity.setName(cursor.getString(1));
                programEntity.setDescription(cursor.getString(2));

                programEntities.add(programEntity);
            } while (cursor.moveToNext());
        }

        closeDb();
        return programEntities;
    }

    private void instantiateDb(){
        database = databaseHelper.getWritableDatabase();
    }

    private void closeDb(){
        database.close();
    }

}
