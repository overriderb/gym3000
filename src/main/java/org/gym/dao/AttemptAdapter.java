package org.gym.dao;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import org.gym.object.Attempt;
import org.gym.object.Exercise;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by anni0913 on 18.11.2014.
 */
public class AttemptAdapter {

    public AttemptAdapter(DatabaseHelper databaseHelper) {
        this.databaseHelper = databaseHelper;
    }

    public static String TABLE_NAME = "attempt";
    public static String ID = "_id";
    public static String PARENT_ID = "patent_id";
    public static String WEIGHT = "weight";
    public static String TIMES = "times";
    private DatabaseHelper databaseHelper;
    private SQLiteDatabase database;


    public long setAttempt(Attempt attempt) {
        instantiateDb();

        ContentValues values = new ContentValues();
        values.put(PARENT_ID, attempt.getParentId());
        values.put(WEIGHT, attempt.getWeight());
        values.put(TIMES, attempt.getTimes());

        long id = database.insert(TABLE_NAME, null, values);
        attempt.setId(id);

        closeDb();
        return id;
    }

    public List<Attempt> getAttemptListByParentId(long parentId){
        instantiateDb();
        List<Attempt> attemptList = new LinkedList<Attempt>();
        String query = "SELECT  * FROM " + TABLE_NAME + " WHERE " + PARENT_ID + " = " + parentId;

        Cursor cursor = database.rawQuery(query, null);
        Attempt attempt = null;
        if (cursor.moveToFirst()) {
            do {
                attempt = new Attempt();
                attempt.setId(Long.parseLong(cursor.getString(0)));
                attempt.setParentId(Long.parseLong(cursor.getString(1)));
                attempt.setWeight(Integer.parseInt(cursor.getString(2)));
                attempt.setTimes(Integer.parseInt(cursor.getString(3)));

                attemptList.add(attempt);
            } while (cursor.moveToNext());
        }

        closeDb();
        return attemptList;
    }

    private void instantiateDb(){
        database = databaseHelper.getWritableDatabase();
    }

    private void closeDb(){
        database.close();
    }
}
