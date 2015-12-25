package org.gym.repository;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import org.gym.domain.ProgramEntity;
import org.gym.domain.ProgramTemplateEntity;
import org.gym.logging.Logger;
import org.gym.model.ProgramTemplate;

/**
 * Created by anni0913 on 25.12.2015.
 */
public class ProgramTemplateRepository {

    private static ProgramTemplateRepository instance;

    private DatabaseHelper databaseHelper;
    private SQLiteDatabase database;

    private ProgramTemplateRepository(DatabaseHelper databaseHelper){
        this.databaseHelper = databaseHelper;
    }

    public static ProgramTemplateRepository getInstance() {
        if (instance == null) {
            instance = new ProgramTemplateRepository(DatabaseHelper.getInstance());
        }
        return instance;
    }

    public Long store(ProgramTemplate programTemplate) {
        instantiateDb();
        Logger.info("Storing programTemplate: " + programTemplate.toString(), ProgramTemplate.class);

        ContentValues values = new ContentValues();
        values.put(ProgramTemplateEntity.Column.NAME.name(), programTemplate.getName());
        values.put(ProgramTemplateEntity.Column.DESCRIPTION.name(), programTemplate.getDescription());

        Long id = database.insert(ProgramTemplateEntity.TABLE_NAME, null, values);
        programTemplate.setId(id);
        Logger.info("Program stored: " + programTemplate.toString(), ProgramTemplate.class);

        closeDb();
        return id;
    }

    public ProgramTemplateEntity find(Long id) {
        instantiateDb();
        Logger.info("Finding program by id = " + id, ProgramRepository.class);
        String query = "SELECT * FROM " + ProgramEntity.TABLE_NAME +  " WHERE " + ProgramEntity.Column.ID + "=" + id;

        ProgramTemplateEntity programTemplateEntity = null;

        Cursor cursor = database.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            programTemplateEntity = new ProgramTemplateEntity();
            programTemplateEntity.setId(cursor.getLong(0));
            programTemplateEntity.setName(cursor.getString(1));
            programTemplateEntity.setDescription(cursor.getString(2));
        }
        closeDb();
        return programTemplateEntity;
    }

    private void instantiateDb(){
        database = databaseHelper.getWritableDatabase();
    }

    private void closeDb(){
        //database.close();
    }
}
