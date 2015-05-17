package org.gym.repository;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import org.gym.domain.Workout;
import org.gym.logging.Logger;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by AndreyNick on 13.11.2014.
 */
public class WorkoutRepository {

    private DatabaseHelper databaseHelper;
    private SQLiteDatabase database;

    protected WorkoutRepository(DatabaseHelper databaseHelper){
        this.databaseHelper = databaseHelper;
    }

    public List<Long> store(List<Workout> workoutList) {
        List<Long> storedIds = new LinkedList<>();
        for(Workout workout : workoutList){
            Long storedId = store(workout);
            storedIds.add(storedId);
        }
        return storedIds;
    }

    public Long store(Workout workout) {
        instantiateDb();
        Logger.info("Storing workout: " + workout.toString(), WorkoutRepository.class);

        ContentValues values = new ContentValues();
        values.put(Workout.Column.PROGRAM_ID.name(), workout.getProgramId());
        values.put(Workout.Column.START_DATE.name(), workout.getStartDate());
        values.put(Workout.Column.END_DATE.name(), workout.getEndDate());
        values.put(Workout.Column.STATUS.name(), workout.getStatus().name());

        Long id = database.insert(Workout.TABLE_NAME, null, values);
        workout.setId(id);
        Logger.info("Workout stored: " + workout.toString(), WorkoutRepository.class);

        closeDb();
        return id;
    }

    public Workout find(Long workoutId){
        instantiateDb();
        Logger.info("Finding workout by id: " + workoutId, WorkoutRepository.class);
        String query = "SELECT  * FROM " + Workout.TABLE_NAME + " WHERE " + Workout.Column.ID + " = " + workoutId;
        Logger.info("Query: " + query, WorkoutRepository.class);

        Workout workout = null;

        Cursor cursor = database.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            workout = new Workout();
            workout.setId(cursor.getLong(0));
            workout.setProgramId(cursor.getLong(1));
            workout.setStartDate(cursor.getLong(2));
            workout.setEndDate(cursor.getLong(3));
            workout.setStatus(Workout.WorkoutStatus.valueOf(cursor.getString(4)));
        }

        closeDb();
        return workout;
    }

    public List<Workout> findByProgramId(Long programId){
        instantiateDb();
        Logger.info("Finding workout list by program id: " + programId, WorkoutRepository.class);
        List<Workout> workouts = new LinkedList<>();
        String query = "SELECT  * FROM " + Workout.TABLE_NAME + " WHERE " + Workout.Column.PROGRAM_ID + " = " + programId;
        Logger.info("Query: " + query, WorkoutRepository.class);

        Cursor cursor = database.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            do {
                Workout workout = new Workout();
                workout.setId(cursor.getLong(0));
                workout.setProgramId(cursor.getLong(1));
                workout.setStartDate(cursor.getLong(2));
                workout.setEndDate(cursor.getLong(3));
                workout.setStatus(Workout.WorkoutStatus.valueOf(cursor.getString(4)));

                workouts.add(workout);
            } while (cursor.moveToNext());
        }

        closeDb();
        return workouts;
    }

    private void instantiateDb(){
        database = databaseHelper.getWritableDatabase();
    }

    private void closeDb(){
        database.close();
    }
}