package org.gym.repository;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import org.gym.domain.WorkoutEntity;
import org.gym.logging.Logger;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by AndreyNick on 13.11.2014.
 */
public class WorkoutRepository {

    private static WorkoutRepository instance;

    private DatabaseHelper databaseHelper;
    private SQLiteDatabase database;

    private WorkoutRepository(DatabaseHelper databaseHelper){
        this.databaseHelper = databaseHelper;
    }

    public static WorkoutRepository getInstance() {
        if (instance == null) {
            instance = new WorkoutRepository(DatabaseHelper.getInstance());
        }
        return instance;
    }

    public List<Long> store(List<WorkoutEntity> workoutEntityList) {
        List<Long> storedIds = new LinkedList<>();
        for(WorkoutEntity workoutEntity : workoutEntityList){
            Long storedId = store(workoutEntity);
            storedIds.add(storedId);
        }
        return storedIds;
    }

    public Long store(WorkoutEntity workoutEntity) {
        instantiateDb();
        Logger.info("Storing workout: " + workoutEntity.toString(), WorkoutRepository.class);

        ContentValues values = new ContentValues();
        values.put(WorkoutEntity.Column.PROGRAM_ID.name(), workoutEntity.getProgramId());
        values.put(WorkoutEntity.Column.START_DATE.name(), workoutEntity.getStartDate());
        values.put(WorkoutEntity.Column.END_DATE.name(), workoutEntity.getEndDate());
        values.put(WorkoutEntity.Column.STATUS.name(), workoutEntity.getStatus());

        Long id = database.insert(WorkoutEntity.TABLE_NAME, null, values);
        workoutEntity.setId(id);
        Logger.info("Workout stored: " + workoutEntity.toString(), WorkoutRepository.class);

        closeDb();
        return id;
    }

    public WorkoutEntity find(Long workoutId){
        instantiateDb();
        Logger.info("Finding workout by id: " + workoutId, WorkoutRepository.class);
        String query = "SELECT  * FROM " + WorkoutEntity.TABLE_NAME + " WHERE " + WorkoutEntity.Column.ID + " = " + workoutId;
        Logger.info("Query: " + query, WorkoutRepository.class);

        WorkoutEntity workoutEntity = null;

        Cursor cursor = database.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            workoutEntity = new WorkoutEntity();
            workoutEntity.setId(cursor.getLong(0));
            workoutEntity.setProgramId(cursor.getLong(1));
            workoutEntity.setStartDate(cursor.getLong(2));
            workoutEntity.setEndDate(cursor.getLong(3));
            workoutEntity.setStatus(cursor.getString(4));
        }

        closeDb();
        return workoutEntity;
    }

    public List<WorkoutEntity> findByProgramId(Long programId){
        instantiateDb();
        Logger.info("Finding workout list by program id: " + programId, WorkoutRepository.class);
        List<WorkoutEntity> workoutEntities = new LinkedList<>();
        String query = "SELECT  * FROM " + WorkoutEntity.TABLE_NAME + " WHERE " + WorkoutEntity.Column.PROGRAM_ID + " = " + programId;
        Logger.info("Query: " + query, WorkoutRepository.class);

        Cursor cursor = database.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            do {
                WorkoutEntity workoutEntity = new WorkoutEntity();
                workoutEntity.setId(cursor.getLong(0));
                workoutEntity.setProgramId(cursor.getLong(1));
                workoutEntity.setStartDate(cursor.getLong(2));
                workoutEntity.setEndDate(cursor.getLong(3));
                workoutEntity.setStatus(cursor.getString(4));

                workoutEntities.add(workoutEntity);
            } while (cursor.moveToNext());
        }

        closeDb();
        return workoutEntities;
    }

    private void instantiateDb(){
        database = databaseHelper.getWritableDatabase();
    }

    private void closeDb(){
        database.close();
    }
}