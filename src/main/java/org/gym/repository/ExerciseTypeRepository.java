package org.gym.repository;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import org.gym.domain.ExerciseType;
import org.gym.logging.Logger;

import java.util.LinkedList;
import java.util.List;

/**
 * TODO: Add comment
 */
public class ExerciseTypeRepository {

    private DatabaseHelper databaseHelper;
    private SQLiteDatabase database;

    public ExerciseTypeRepository(DatabaseHelper databaseHelper) {
        this.databaseHelper = databaseHelper;
    }

    public List<Long> storeExerciseTypes(List<ExerciseType> exerciseTypes) {
        List<Long> storedIds = new LinkedList<>();
        for(ExerciseType exerciseType : exerciseTypes){
            Long storedId = store(exerciseType);
            storedIds.add(storedId);
        }
        return storedIds;
    }

    public Long store(ExerciseType exerciseType) {
        instantiateDb();
        Logger.info("Storing exercise type: " + exerciseType.toString(), ExerciseTypeRepository.class);

        ContentValues values = new ContentValues();
        values.put(ExerciseType.Column.PROGRAM_ID.name(), exerciseType.getProgramId());
        values.put(ExerciseType.Column.NAME.name(), exerciseType.getName());
        values.put(ExerciseType.Column.DESCRIPTION.name(), exerciseType.getDescription());
        values.put(ExerciseType.Column.PICTURE_ID.name(), exerciseType.getPictureId());

        Long id = database.insert(ExerciseType.TABLE_NAME, null, values);
        exerciseType.setId(id);
        Logger.info("Exercise type stored: " + exerciseType.toString(), ExerciseType.class);

        closeDb();
        return id;
    }

    public ExerciseType find(Long exerciseTypeId){
        instantiateDb();
        Logger.info("Finding exercise type id: " + exerciseTypeId, ExerciseTypeRepository.class);
        String query = "SELECT  * FROM " + ExerciseType.TABLE_NAME + " WHERE " + ExerciseType.Column.ID + " = " + exerciseTypeId;
        Logger.info("Query: " + query, WorkoutRepository.class);

        ExerciseType exerciseType = null;

        Cursor cursor = database.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            exerciseType = new ExerciseType();
            exerciseType.setId(cursor.getLong(0));
            exerciseType.setProgramId(cursor.getLong(1));
            exerciseType.setName(cursor.getString(2));
            exerciseType.setDescription(cursor.getString(3));
            exerciseType.setPictureId(cursor.getInt(4));
        }

        closeDb();
        return exerciseType;
    }

    public List<ExerciseType> findByProgramId(Long programId){
        instantiateDb();
        Logger.info("Finding exercise types by program id: " + programId, ExerciseTypeRepository.class);
        List<ExerciseType> exerciseTypes = new LinkedList<>();
        String query = "SELECT  * FROM " + ExerciseType.TABLE_NAME + " WHERE " + ExerciseType.Column.PROGRAM_ID + " = " + programId;
        Logger.info("Query: " + query, WorkoutRepository.class);

        Cursor cursor = database.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            do {
                ExerciseType exerciseType = new ExerciseType();
                exerciseType.setId(cursor.getLong(0));
                exerciseType.setProgramId(cursor.getLong(1));
                exerciseType.setName(cursor.getString(2));
                exerciseType.setDescription(cursor.getString(3));
                exerciseType.setPictureId(cursor.getInt(4));

                exerciseTypes.add(exerciseType);
            } while (cursor.moveToNext());
        }

        closeDb();
        return exerciseTypes;
    }

    private void instantiateDb(){
        database = databaseHelper.getWritableDatabase();
    }

    private void closeDb(){
        database.close();
    }
}
