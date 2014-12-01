package org.gym.dao;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import org.gym.object.Exercise;
import org.gym.object.Workout;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by anni0913 on 18.11.2014.
 */
public class ExerciseAdapter {

    public ExerciseAdapter(DatabaseHelper databaseHelper) {
        this.databaseHelper = databaseHelper;
    }

    public static String TABLE_NAME = "exercise";
    public static String ID = "_id";
    public static String PARENT_ID = "patent_id";
    public static String DATE = "date";
    public static String TYPE_OF_EXERCISE = "type";
    private DatabaseHelper databaseHelper;
    private SQLiteDatabase database;


    public long setExercise(Exercise exercise) {
        instantiateDb();

        ContentValues values = new ContentValues();
        values.put(PARENT_ID, exercise.getParentId());
        values.put(DATE, exercise.getDate());
        values.put(TYPE_OF_EXERCISE, exercise.getTypeOfExercise());

        long id = database.insert(TABLE_NAME, null, values);
        exercise.setId(id);

        closeDb();
        return id;
    }

    public List<Exercise> getExerciseListByParentId(long parentId){
        instantiateDb();
        List<Exercise> exerciseList = new LinkedList<Exercise>();
        String query = "SELECT  * FROM " + TABLE_NAME + " WHERE " + PARENT_ID + " = " + parentId;

        Cursor cursor = database.rawQuery(query, null);
        Exercise exercise = null;
        if (cursor.moveToFirst()) {
            do {
                exercise = new Exercise();
                exercise.setId(Long.parseLong(cursor.getString(0)));
                exercise.setParentId(Long.parseLong(cursor.getString(1)));
                exercise.setDate(cursor.getString(2));
                exercise.setTypeOfExercise(cursor.getString(3));

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
