package org.gym.dao;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import org.gym.object.Program;
import org.gym.object.Workout;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by AndreyNick on 13.11.2014.
 */
public class WorkoutAdapter {

    protected WorkoutAdapter(DatabaseHelper databaseHelper){
        this.databaseHelper = databaseHelper;

    }

    public static String TABLE_NAME = "workout";
    public static String ID = "_id";
    public static String PARENT_ID = "patent_id";
    public static String NAME = "name";
    public static String PICTURE_ID = "picture_id";
    public static String DESCRIPTION = "description";
    private DatabaseHelper databaseHelper;
    private SQLiteDatabase database;

    public void setWorkouts(List<Workout> workoutList){  //TODO think about posibility of adding collection and return their own ids
        for(Workout workout : workoutList){              //now it doesn't return
            setWorkout(workout);
        }
    }

    public long setWorkout(Workout workout) {
        instantiateDb();

        ContentValues values = new ContentValues();
        values.put(PARENT_ID, workout.getParentId());
        values.put(NAME, workout.getName());
        values.put(PICTURE_ID, workout.getPictureId());
        values.put(DESCRIPTION, workout.getDescription());

        long id = database.insert(TABLE_NAME, null, values);
        workout.setId(id);

        closeDb();
        return id;
    }

    public List<Workout> getWorkoutsListByParentId(long parentId){
        instantiateDb();
        List<Workout> workoutList = new LinkedList<Workout>();
        String query = "SELECT  * FROM " + TABLE_NAME + " WHERE " + PARENT_ID + " = " + parentId;

        Cursor cursor = database.rawQuery(query, null);
        Workout workout = null;
        if (cursor.moveToFirst()) {
            do {
                workout = new Workout();
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
        String query = "SELECT  * FROM " + TABLE_NAME + " WHERE " + PARENT_ID + " = " + parentId;
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
