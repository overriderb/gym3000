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

    public long store(Exercise exercise) {
        instantiateDb();
        Logger.info("Storing exercise: " + exercise.toString(), ExerciseRepository.class);

        ContentValues values = new ContentValues();
        values.put(Exercise.Column.TYPE.name(), exercise.getExerciseTypeId());
        values.put(Exercise.Column.WORKOUT_ID.name(), exercise.getWorkoutId());

        long id = database.insert(Exercise.TABLE_NAME, null, values);
        exercise.setId(id);
        Logger.info("Exercise stored: " + exercise.toString(), ExerciseRepository.class);

        closeDb();
        return id;
    }

    public Exercise find(Long exerciseId) {
        instantiateDb();
        Logger.info("Finding exercise id: " + exerciseId, ExerciseRepository.class);
        String query = "SELECT  * FROM " + Exercise.TABLE_NAME + " WHERE " + Exercise.Column.ID + " = " + exerciseId;
        Logger.info("Query: " + query, ExerciseRepository.class);

        Exercise exercise = null;

        Cursor cursor = database.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            exercise = new Exercise();
            exercise.setId(Long.parseLong(cursor.getString(0)));
            exercise.setWorkoutId(Long.parseLong(cursor.getString(1)));
            exercise.setExerciseTypeId(cursor.getLong(2));
        }

        closeDb();
        return exercise;
    }

    public List<Exercise> findByWorkoutId(Long workoutId) {
        instantiateDb();
        Logger.info("Finding exercise list by workout id: " + workoutId, ExerciseRepository.class);
        List<Exercise> exercises = new LinkedList<>();
        String query = "SELECT  * FROM " + Exercise.TABLE_NAME + " WHERE " + Exercise.Column.WORKOUT_ID + " = " + workoutId;
        Logger.info("Query: " + query, ExerciseRepository.class);

        Cursor cursor = database.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            do {
                Exercise exercise = new Exercise();
                exercise.setId(Long.parseLong(cursor.getString(0)));
                exercise.setWorkoutId(Long.parseLong(cursor.getString(1)));
                exercise.setExerciseTypeId(cursor.getLong(2));

                exercises.add(exercise);
            } while (cursor.moveToNext());
        }

        closeDb();
        return exercises;
    }

    private void instantiateDb(){
        database = databaseHelper.getWritableDatabase();
    }

    private void closeDb(){
        database.close();
    }
}
