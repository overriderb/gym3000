package org.gym.repository;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import org.gym.domain.WorkoutEntity;
import org.gym.logging.Logger;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * It provides methods for DB working with Workout objects
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
//        values.put(Workout.Column.PARENT_ID.name(), workout.getParentId());
//        values.put(Workout.Column.NAME.name(), workout.getName());
//        values.put(Workout.Column.PICTURE_ID.name(), workout.getPictureId());
//        values.put(Workout.Column.DESCRIPTION.name(), workout.getDescription());
//        values.put(Workout.Column.ORDER_NUMBER.name(), workout.getOrderNumber());

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
//        List<Workout> workoutList = new LinkedList<Workout>();
//        String query = "SELECT  * FROM " + Workout.TABLE_NAME + " WHERE " + Workout.Column.PARENT_ID + " = " +
//                parentId+  " ORDER BY ORDER_NUMBER";
        
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
//            do {
//                Workout workout = new Workout();
//                workout.setId(Long.parseLong(cursor.getString(0)));
//                workout.setParentId(Long.parseLong(cursor.getString(1)));
//                workout.setName(cursor.getString(2));
//                workout.setPictureId(Integer.parseInt(cursor.getString(3)));
//                workout.setDescription(cursor.getString(4));
//                workout.setOrderNumber(Integer.parseInt(cursor.getString(5)));
//
//                workoutList.add(workout);
//            } while (cursor.moveToNext());
        }

        closeDb();
        return workoutEntity;
    }

    public Map<Integer, Workout> getWorkoutsMapByParentId(Long parentId){
        instantiateDb();
        Logger.info("Finding workout map by parent id: " + parentId, WorkoutRepository.class);
        Map<Integer, Workout> workoutMap = new HashMap<Integer, Workout>(15);
        String query = "SELECT  * FROM " + Workout.TABLE_NAME + " WHERE " + Workout.Column.PARENT_ID + " = " +
                parentId+  " ORDER BY ORDER_NUMBER";

        Logger.info("Query: " + query, WorkoutRepository.class);

        Cursor cursor = database.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            do {
                Workout workout = new Workout();
                workout.setId(Long.parseLong(cursor.getString(0)));
                workout.setParentId(Long.parseLong(cursor.getString(1)));
                workout.setName(cursor.getString(2));
                workout.setPictureId(Integer.parseInt(cursor.getString(3)));
                workout.setDescription(cursor.getString(4));
                workout.setOrderNumber(Integer.parseInt(cursor.getString(5)));

                workoutMap.put(workout.getOrderNumber(), workout);
            } while (cursor.moveToNext());
        }

        closeDb();
        return workoutMap;

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