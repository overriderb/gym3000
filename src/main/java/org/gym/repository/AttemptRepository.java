package org.gym.repository;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import org.gym.domain.Attempt;
import org.gym.logging.Logger;

import java.util.LinkedList;
import java.util.List;

/**
 * It provides methods for DB working with Attempt objects
 */
public class AttemptRepository {

    private DatabaseHelper databaseHelper;
    private SQLiteDatabase database;

    public AttemptRepository(DatabaseHelper databaseHelper) {
        this.databaseHelper = databaseHelper;
    }

    public Long storeAttempt(Attempt attempt) {
        instantiateDb();
        Logger.info("Storing attempt: " + attempt.toString(), AttemptRepository.class);

        ContentValues values = new ContentValues();
        values.put(Attempt.Column.PARENT_ID.name(), attempt.getParentId());
        values.put(Attempt.Column.WEIGHT.name(), attempt.getWeight());
        values.put(Attempt.Column.COUNT.name(), attempt.getCount());

        Long id = database.insert(Attempt.TABLE_NAME, null, values);
        attempt.setId(id);
        Logger.info("Attempt stored: " + attempt.toString(), AttemptRepository.class);

        closeDb();
        return id;
    }

    public List<Attempt> findAttemptListByParentId(Long parentId) {
        instantiateDb();
        Logger.info("Finding attempt list by parent id: " + parentId, AttemptRepository.class);
        List<Attempt> attemptList = new LinkedList<Attempt>();
        String query = "SELECT  * FROM " + Attempt.TABLE_NAME + " WHERE " + Attempt.Column.PARENT_ID + " = " + parentId +  " ORDER BY WEIGHT";
        
        Logger.info("Query: " + query, AttemptRepository.class);

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
