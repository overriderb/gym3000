package org.gym.repository;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import org.gym.domain.ExerciseTypeEntity;
import org.gym.logging.Logger;

import java.util.LinkedList;
import java.util.List;

/**
 * TODO: Add comment
 */
public class ExerciseTypeRepository {

    private static ExerciseTypeRepository instance;

    private DatabaseHelper databaseHelper;
    private SQLiteDatabase database;

    private ExerciseTypeRepository(DatabaseHelper databaseHelper) {
        this.databaseHelper = databaseHelper;
    }

    public static ExerciseTypeRepository getInstance() {
        if (instance == null) {
            instance = new ExerciseTypeRepository(DatabaseHelper.getInstance());
        }
        return instance;
    }

    public List<Long> store(List<ExerciseTypeEntity> exerciseTypeEntities) {
        List<Long> storedIds = new LinkedList<>();
        for(ExerciseTypeEntity exerciseTypeEntity : exerciseTypeEntities){
            Long storedId = store(exerciseTypeEntity);
            storedIds.add(storedId);
        }
        return storedIds;
    }

    public Long store(ExerciseTypeEntity exerciseTypeEntity) {
        instantiateDb();
        Logger.info("Storing exercise type: " + exerciseTypeEntity.toString(), ExerciseTypeRepository.class);

        ContentValues values = new ContentValues();
        values.put(ExerciseTypeEntity.Column.PROGRAM_ID.name(), exerciseTypeEntity.getProgramId());
        values.put(ExerciseTypeEntity.Column.NAME.name(), exerciseTypeEntity.getName());
        values.put(ExerciseTypeEntity.Column.DESCRIPTION.name(), exerciseTypeEntity.getDescription());
        values.put(ExerciseTypeEntity.Column.PICTURE_ID.name(), exerciseTypeEntity.getPictureId());

        Long id = database.insert(ExerciseTypeEntity.TABLE_NAME, null, values);
        exerciseTypeEntity.setId(id);
        Logger.info("Exercise type stored: " + exerciseTypeEntity.toString(), ExerciseTypeEntity.class);

        closeDb();
        return id;
    }

    public ExerciseTypeEntity find(Long exerciseTypeId){
        instantiateDb();
        Logger.info("Finding exercise type id: " + exerciseTypeId, ExerciseTypeRepository.class);
        String query = "SELECT  * FROM " + ExerciseTypeEntity.TABLE_NAME + " WHERE " + ExerciseTypeEntity.Column.ID + " = " + exerciseTypeId;
        Logger.info("Query: " + query, WorkoutRepository.class);

        ExerciseTypeEntity exerciseTypeEntity = null;

        Cursor cursor = database.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            exerciseTypeEntity = new ExerciseTypeEntity();
            exerciseTypeEntity.setId(cursor.getLong(0));
            exerciseTypeEntity.setProgramId(cursor.getLong(1));
            exerciseTypeEntity.setName(cursor.getString(2));
            exerciseTypeEntity.setDescription(cursor.getString(3));
            exerciseTypeEntity.setPictureId(cursor.getInt(4));
        }

        closeDb();
        return exerciseTypeEntity;
    }

    public List<ExerciseTypeEntity> findByProgramId(Long programId){
        instantiateDb();
        Logger.info("Finding exercise types by program id: " + programId, ExerciseTypeRepository.class);
        List<ExerciseTypeEntity> exerciseTypeEntities = new LinkedList<>();
        String query = "SELECT  * FROM " + ExerciseTypeEntity.TABLE_NAME + " WHERE " + ExerciseTypeEntity.Column.PROGRAM_ID + " = " + programId;
        Logger.info("Query: " + query, WorkoutRepository.class);

        Cursor cursor = database.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            do {
                ExerciseTypeEntity exerciseTypeEntity = new ExerciseTypeEntity();
                exerciseTypeEntity.setId(cursor.getLong(0));
                exerciseTypeEntity.setProgramId(cursor.getLong(1));
                exerciseTypeEntity.setName(cursor.getString(2));
                exerciseTypeEntity.setDescription(cursor.getString(3));
                exerciseTypeEntity.setPictureId(cursor.getInt(4));

                exerciseTypeEntities.add(exerciseTypeEntity);
            } while (cursor.moveToNext());
        }

        closeDb();
        return exerciseTypeEntities;
    }

    private void instantiateDb(){
        database = databaseHelper.getWritableDatabase();
    }

    private void closeDb(){
        database.close();
    }
}
