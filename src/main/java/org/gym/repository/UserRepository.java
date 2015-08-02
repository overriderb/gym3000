package org.gym.repository;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import org.gym.domain.UserEntity;
import org.gym.logging.Logger;

/**
 * Created by anni0913 on 26.05.2015.
 */
public class UserRepository {

    private static UserRepository instance;

    private DatabaseHelper databaseHelper;
    private SQLiteDatabase database;

    private UserRepository(DatabaseHelper dh){
        this.databaseHelper = dh;
    }

    public static UserRepository getInstance() {
        if (instance == null) {
            instance = new UserRepository(DatabaseHelper.getInstance());
        }
        return instance;
    }

    public UserEntity find(Long id) {
        instantiateDb();
        Logger.info("Finding user by id = " + id, UserRepository.class);
        String query = "SELECT * FROM " + UserEntity.TABLE_NAME +  " WHERE " + UserEntity.Column.ID + "=" + id;

        UserEntity userEntity = null;

        Cursor cursor = database.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            userEntity = new UserEntity();
            userEntity.setId(cursor.getLong(0));
            userEntity.setName(cursor.getString(1));
        }
        closeDb();
        return userEntity;
    }

    private void instantiateDb(){
        database = databaseHelper.getWritableDatabase();
    }

    private void closeDb(){
        database.close();
    }
}
