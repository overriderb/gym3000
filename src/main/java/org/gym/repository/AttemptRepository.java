package org.gym.repository;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import org.gym.domain.Attempt;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by anni0913 on 18.11.2014.
 */
public class AttemptRepository {

    private DatabaseHelper databaseHelper;
    private SQLiteDatabase database;

    public AttemptRepository(DatabaseHelper databaseHelper) {
        this.databaseHelper = databaseHelper;
    }

    public Long storeAttempt(Attempt attempt) {
        instantiateDb();

        ContentValues values = new ContentValues();
        values.put(Attempt.Column.PARENT_ID.name(), attempt.getParentId());
        values.put(Attempt.Column.WEIGHT.name(), attempt.getWeight());
        values.put(Attempt.Column.COUNT.name(), attempt.getCount());

        Long id = database.insert(Attempt.TABLE_NAME, null, values);
        attempt.setId(id);

        closeDb();
        return id;
    }

    public List<Attempt> findAttemptListByParentId(Long parentId) {
        instantiateDb();
        List<Attempt> attemptList = new LinkedList<Attempt>();
        String query = "SELECT  * FROM " + Attempt.TABLE_NAME + " WHERE " + Attempt.Column.PARENT_ID + " = " + parentId;

        Cursor cursor = database.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            do {
                Attempt attempt = new Attempt();
                attempt.setId(Long.parseLong(cursor.getString(0)));
                attempt.setParentId(Long.parseLong(cursor.getString(1)));
                attempt.setWeight(cursor.getString(2));
                attempt.setCount(Integer.parseInt(cursor.getString(3)));

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
