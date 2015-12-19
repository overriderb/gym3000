package org.gym.repository;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import org.gym.domain.ExerciseEntity;
import org.gym.logging.Logger;

import java.util.LinkedList;
import java.util.List;

/**
 * It provides methods for DB working with Exercise objects
 */
public class ExerciseRepository {

    private static ExerciseRepository instance;

    private DatabaseHelper databaseHelper;
    private SQLiteDatabase database;

    private ExerciseRepository(DatabaseHelper databaseHelper) {
        this.databaseHelper = databaseHelper;
    }

    public static ExerciseRepository getInstance() {
        if (instance == null) {
            instance = new ExerciseRepository(DatabaseHelper.getInstance());
        }
        return instance;
    }

    public long store(ExerciseEntity exerciseEntity) {
        instantiateDb();
        Logger.info("Storing exercise: " + exerciseEntity.toString(), ExerciseRepository.class);

        ContentValues values = new ContentValues();
        values.put(ExerciseEntity.Column.LEVEL.name(), exerciseEntity.getLevel());
        values.put(ExerciseEntity.Column.TYPE.name(), exerciseEntity.getExerciseTypeId());
        values.put(ExerciseEntity.Column.WORKOUT_ID.name(), exerciseEntity.getWorkoutId());

        long id = database.insert(ExerciseEntity.TABLE_NAME, null, values);
        exerciseEntity.setId(id);
        Logger.info("Exercise stored: " + exerciseEntity.toString(), ExerciseRepository.class);

        closeDb();
        return id;
    }

    public ExerciseEntity find(Long exerciseId) {
        instantiateDb();
        Logger.info("Finding exercise id: " + exerciseId, ExerciseRepository.class);
        String query = "SELECT  * FROM " + ExerciseEntity.TABLE_NAME + " WHERE " + ExerciseEntity.Column.ID + " = " + exerciseId;
        Logger.info("Query: " + query, ExerciseRepository.class);

        ExerciseEntity exerciseEntity = null;

        Cursor cursor = database.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            exerciseEntity = new ExerciseEntity();
            exerciseEntity.setId(cursor.getLong(0));
            exerciseEntity.setLevel(cursor.getString(1));
            exerciseEntity.setExerciseTypeId(cursor.getLong(2));
            exerciseEntity.setWorkoutId(cursor.getLong(3));
        }

        closeDb();
        return exerciseEntity;
    }

    public List<ExerciseEntity> findByWorkoutId(Long workoutId) {
        instantiateDb();
        Logger.info("Finding exercise list by workout id: " + workoutId, ExerciseRepository.class);
        List<ExerciseEntity> exerciseEntities = new LinkedList<>();
        String query = "SELECT  * FROM " + ExerciseEntity.TABLE_NAME + " WHERE " + ExerciseEntity.Column.WORKOUT_ID + " = " + workoutId;
        Logger.info("Query: " + query, ExerciseRepository.class);

        Cursor cursor = database.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            do {
                ExerciseEntity exerciseEntity = new ExerciseEntity();
                exerciseEntity.setId(cursor.getLong(0));
                exerciseEntity.setLevel(cursor.getString(1));
                exerciseEntity.setExerciseTypeId(cursor.getLong(2));
                exerciseEntity.setWorkoutId(cursor.getLong(3));

                exerciseEntities.add(exerciseEntity);
            } while (cursor.moveToNext());
        }

        closeDb();
        return exerciseEntities;
    }

    public List<ExerciseEntity> findByTypeId(Long exerciseTypeId) {
        instantiateDb();
        Logger.info("Finding exercise list by exercise type id: " + exerciseTypeId, ExerciseRepository.class);
        List<ExerciseEntity> exerciseEntities = new LinkedList<>();
        String query = "SELECT  * FROM " + ExerciseEntity.TABLE_NAME + " WHERE " + ExerciseEntity.Column.TYPE + " = " + exerciseTypeId;
        Logger.info("Query: " + query, ExerciseRepository.class);

        Cursor cursor = database.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            do {
                ExerciseEntity exerciseEntity = new ExerciseEntity();
                exerciseEntity.setId(cursor.getLong(0));
                exerciseEntity.setLevel(cursor.getString(1));
                exerciseEntity.setExerciseTypeId(cursor.getLong(2));
                exerciseEntity.setWorkoutId(cursor.getLong(3));

                exerciseEntities.add(exerciseEntity);
            } while (cursor.moveToNext());
        }

        closeDb();
        return exerciseEntities;
    }

    private void instantiateDb(){
        database = databaseHelper.getWritableDatabase();
    }

    private void closeDb(){
        database.close();
    }
}
