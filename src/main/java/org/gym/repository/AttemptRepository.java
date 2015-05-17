package org.gym.repository;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import org.gym.domain.Attempt;
import org.gym.domain.Exercise;
import org.gym.logging.Logger;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by anni0913 on 18.11.2014.
 */
public class AttemptRepository {

    private DatabaseHelper databaseHelper;
    private SQLiteDatabase database;

    public AttemptRepository(DatabaseHelper databaseHelper) {
        this.databaseHelper = databaseHelper;
    }

    public Long store(Attempt attempt) {
        instantiateDb();
        Logger.info("Storing attempt: " + attempt.toString(), AttemptRepository.class);

        ContentValues values = new ContentValues();
        values.put(Attempt.Column.EXERCISE_ID.name(), attempt.getExerciseId());
        values.put(Attempt.Column.WEIGHT.name(), attempt.getWeight());
        values.put(Attempt.Column.COUNT.name(), attempt.getCount());
        values.put(Attempt.Column.TYPE.name(), attempt.getType().name());
        values.put(Attempt.Column.COMMENT.name(), attempt.getComment());

        Long id = database.insert(Attempt.TABLE_NAME, null, values);
        attempt.setId(id);
        Logger.info("Attempt stored: " + attempt.toString(), AttemptRepository.class);

        closeDb();
        return id;
    }

    public Attempt find(Long attemptId) {
        instantiateDb();
        Logger.info("Finding attempt id: " + attemptId, AttemptRepository.class);
        String query = "SELECT  * FROM " + Attempt.TABLE_NAME + " WHERE " + Attempt.Column.ID + " = " + attemptId;
        Logger.info("Query: " + query, AttemptRepository.class);

        Attempt attempt = null;

        Cursor cursor = database.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            attempt = new Attempt();
            attempt.setId(cursor.getLong(0));
            attempt.setExerciseId(cursor.getLong(1));
            attempt.setWeight(cursor.getString(2));
            attempt.setCount(cursor.getInt(3));
            attempt.setType(Attempt.Type.valueOf(cursor.getString(4)));
            attempt.setComment(cursor.getString(5));
        }

        closeDb();
        return attempt;
    }

    public List<Attempt> findByExerciseId(Long exerciseId) {
        instantiateDb();
        Logger.info("Finding attempt list by exercise id: " + exerciseId, AttemptRepository.class);
        List<Attempt> attempts = new LinkedList<>();
        String query = "SELECT  * FROM " + Attempt.TABLE_NAME + " WHERE " + Attempt.Column.EXERCISE_ID + " = " + exerciseId;
        Logger.info("Query: " + query, AttemptRepository.class);

        Cursor cursor = database.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            do {
                Attempt attempt = new Attempt();
                attempt.setId(cursor.getLong(0));
                attempt.setExerciseId(cursor.getLong(1));
                attempt.setWeight(cursor.getString(2));
                attempt.setCount(cursor.getInt(3));
                attempt.setType(Attempt.Type.valueOf(cursor.getString(4)));
                attempt.setComment(cursor.getString(5));

                attempts.add(attempt);
            } while (cursor.moveToNext());
        }

        closeDb();
        return attempts;
    }

    private void instantiateDb(){
        database = databaseHelper.getWritableDatabase();
    }

    private void closeDb(){
        database.close();
    }
}
