package org.gym.dao;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import org.gym.object.Workout;

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

    public void storeWorkouts(List<Workout> workoutList){  //TODO think about posibility of adding collection and return their own ids
        for(Workout workout : workoutList){              //now it doesn't return
            storeWorkout(workout);
        }
    }

    public Long storeWorkout(Workout workout) {
        instantiateDb();

        ContentValues values = new ContentValues();
        values.put(Workout.Column.PARENT_ID.name(), workout.getParentId());
        values.put(Workout.Column.NAME.name(), workout.getName());
        values.put(Workout.Column.PICTURE_ID.name(), workout.getPictureId());
        values.put(Workout.Column.DESCRIPTION.name(), workout.getDescription());

        Long id = database.insert(Workout.TABLE_NAME, null, values);
        workout.setId(id);

        closeDb();
        return id;
    }

    public List<Workout> findWorkoutsListByParentId(long parentId){
        instantiateDb();
        List<Workout> workoutList = new LinkedList<Workout>();
        String query = "SELECT  * FROM " + Workout.TABLE_NAME + " WHERE " + Workout.Column.PARENT_ID + " = " + parentId;

        Cursor cursor = database.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            do {
                Workout workout = new Workout();
                workout.setId(Long.parseLong(cursor.getString(0)));
                workout.setParentId(Long.parseLong(cursor.getString(1)));
                workout.setName(cursor.getString(2));
                workout.setPictureId(Integer.parseInt(cursor.getString(3)));
                workout.setDescription(cursor.getString(4));

                workoutList.add(workout);
            } while (cursor.moveToNext());
        }

        closeDb();
        return workoutList;
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
