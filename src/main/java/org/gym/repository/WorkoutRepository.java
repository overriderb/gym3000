package org.gym.repository;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import org.gym.domain.Workout;
import org.gym.logging.Logger;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * It provides methods for DB working with Workout objects
 */
public class WorkoutRepository {

    private DatabaseHelper databaseHelper;
    private SQLiteDatabase database;

    protected WorkoutRepository(DatabaseHelper databaseHelper){
        this.databaseHelper = databaseHelper;
    }

    public void storeWorkouts(List<Workout> workoutList){  //TODO think about posibility of adding collection and return their own ids
        for(Workout workout : workoutList){              //now it doesn't return
            storeWorkout(workout);
        }
    }

    public Long storeWorkout(Workout workout) {
        instantiateDb();
        Logger.info("Storing workout: " + workout.toString(), WorkoutRepository.class);

        ContentValues values = new ContentValues();
        values.put(Workout.Column.PARENT_ID.name(), workout.getParentId());
        values.put(Workout.Column.NAME.name(), workout.getName());
        values.put(Workout.Column.PICTURE_ID.name(), workout.getPictureId());
        values.put(Workout.Column.DESCRIPTION.name(), workout.getDescription());
        values.put(Workout.Column.ORDER_NUMBER.name(), workout.getOrderNumber());

        Long id = database.insert(Workout.TABLE_NAME, null, values);
        workout.setId(id);
        Logger.info("Workout stored: " + workout.toString(), WorkoutRepository.class);

        closeDb();
        return id;
    }

    public List<Workout> getWorkoutsListByParentId(Long parentId){
        instantiateDb();
        Logger.info("Finding workout list by parent id: " + parentId, WorkoutRepository.class);
        List<Workout> workoutList = new LinkedList<Workout>();
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

                workoutList.add(workout);
            } while (cursor.moveToNext());
        }

        closeDb();
        return workoutList;
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

    public Cursor getWorkoutsCursorByParentId(long parentId){
        instantiateDb();
        String query = "SELECT  * FROM " + Workout.TABLE_NAME + " WHERE " + Workout.Column.PARENT_ID + " = " + parentId;
        Cursor cursor = database.rawQuery(query, null);
        closeDb();
        return cursor;
    }

    private void instantiateDb(){
        database = databaseHelper.getWritableDatabase();
    }

    private void closeDb(){
        database.close();
    }



}
