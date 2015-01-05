package org.gym.repository;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import org.gym.domain.Exercise;
import org.gym.logging.Logger;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by anni0913 on 18.11.2014.
 */
public class ExerciseRepository {

    private DatabaseHelper databaseHelper;
    private SQLiteDatabase database;

    public ExerciseRepository(DatabaseHelper databaseHelper) {
        this.databaseHelper = databaseHelper;
    }

    public long storeExercise(Exercise exercise) {
        instantiateDb();
        Logger.info("Storing exercise: " + exercise.toString(), ExerciseRepository.class);

        ContentValues values = new ContentValues();
        values.put(Exercise.Column.PARENT_ID.name(), exercise.getParentId());
        values.put(Exercise.Column.DATE.name(), exercise.getDate());
        values.put(Exercise.Column.TYPE.name(), exercise.getType().name());

        long id = database.insert(Exercise.TABLE_NAME, null, values);
        exercise.setId(id);
        Logger.info("Exercise stored: " + exercise.toString(), ExerciseRepository.class);

        closeDb();
        return id;
    }

    public List<Exercise> findExerciseListByParentId(Long parentId) {
        instantiateDb();
        Logger.info("Finding exercise list by parent id: " + parentId, ExerciseRepository.class);
        List<Exercise> exerciseList = new LinkedList<Exercise>();
        String query = "SELECT  * FROM " + Exercise.TABLE_NAME + " WHERE " + Exercise.Column.PARENT_ID + " = " + parentId;
        Logger.info("Query: " + query, ExerciseRepository.class);

        Cursor cursor = database.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            do {
                Exercise exercise = new Exercise();
                exercise.setId(Long.parseLong(cursor.getString(0)));
                exercise.setParentId(Long.parseLong(cursor.getString(1)));
                exercise.setDate(cursor.getString(2));
                exercise.setType(Exercise.TYPE.valueOf(cursor.getString(3)));

                exerciseList.add(exercise);
            } while (cursor.moveToNext());
        }

        closeDb();
        return exerciseList;
    }

    private void instantiateDb(){
        database = databaseHelper.getWritableDatabase();
    }

    private void closeDb(){
        database.close();
    }
}
