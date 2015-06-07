package org.gym.repository;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import org.gym.domain.AttemptEntity;
import org.gym.logging.Logger;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by anni0913 on 18.11.2014.
 */
public class AttemptRepository {

    private static AttemptRepository instance;

    private DatabaseHelper databaseHelper;
    private SQLiteDatabase database;

    private AttemptRepository(DatabaseHelper databaseHelper) {
        this.databaseHelper = databaseHelper;
    }

    public static AttemptRepository getInstance() {
        if (instance == null) {
            instance = new AttemptRepository(DatabaseHelper.getInstance());
        }
        return instance;
    }

    public Long store(AttemptEntity attemptEntity) {
        instantiateDb();
        Logger.info("Storing attempt: " + attemptEntity.toString(), AttemptRepository.class);

        ContentValues values = new ContentValues();
        values.put(AttemptEntity.Column.EXERCISE_ID.name(), attemptEntity.getExerciseId());
        values.put(AttemptEntity.Column.WEIGHT.name(), attemptEntity.getWeight());
        values.put(AttemptEntity.Column.COUNT.name(), attemptEntity.getCount());
        values.put(AttemptEntity.Column.COMMENT.name(), attemptEntity.getComment());

        Long id = database.insert(AttemptEntity.TABLE_NAME, null, values);
        attemptEntity.setId(id);
        Logger.info("Attempt stored: " + attemptEntity.toString(), AttemptRepository.class);

        closeDb();
        return id;
    }

    public AttemptEntity find(Long attemptId) {
        instantiateDb();
        Logger.info("Finding attempt id: " + attemptId, AttemptRepository.class);
        String query = "SELECT  * FROM " + AttemptEntity.TABLE_NAME + " WHERE " + AttemptEntity.Column.ID + " = " + attemptId;
        Logger.info("Query: " + query, AttemptRepository.class);

        AttemptEntity attemptEntity = null;

        Cursor cursor = database.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            attemptEntity = new AttemptEntity();
            attemptEntity.setId(cursor.getLong(0));
            attemptEntity.setExerciseId(cursor.getLong(1));
            attemptEntity.setWeight(cursor.getString(2));
            attemptEntity.setCount(cursor.getInt(3));
            attemptEntity.setComment(cursor.getString(4));
        }

        closeDb();
        return attemptEntity;
    }

    public List<AttemptEntity> findByExerciseId(Long exerciseId) {
        instantiateDb();
        Logger.info("Finding attempt list by exercise id: " + exerciseId, AttemptRepository.class);
        List<AttemptEntity> attemptEntities = new LinkedList<>();
        String query = "SELECT  * FROM " + AttemptEntity.TABLE_NAME + " WHERE " + AttemptEntity.Column.EXERCISE_ID + " = " + exerciseId;
        Logger.info("Query: " + query, AttemptRepository.class);

        Cursor cursor = database.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            do {
                AttemptEntity attemptEntity = new AttemptEntity();
                attemptEntity.setId(cursor.getLong(0));
                attemptEntity.setExerciseId(cursor.getLong(1));
                attemptEntity.setWeight(cursor.getString(2));
                attemptEntity.setCount(cursor.getInt(3));
                attemptEntity.setComment(cursor.getString(4));

                attemptEntities.add(attemptEntity);
            } while (cursor.moveToNext());
        }

        closeDb();
        return attemptEntities;
    }

    private void instantiateDb(){
        database = databaseHelper.getWritableDatabase();
    }

    private void closeDb(){
        database.close();
    }
}
