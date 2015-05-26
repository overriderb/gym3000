package org.gym.repository;

import android.database.sqlite.SQLiteDatabase;

/**
 * Created by anni0913 on 26.05.2015.
 */
public class UserRepository {

    private DatabaseHelper databaseHelper;
    private SQLiteDatabase database;

    public UserRepository(DatabaseHelper dh){
        this.databaseHelper = dh;
    }


    //Repository methods

    private void instantiateDb(){
        database = databaseHelper.getWritableDatabase();
    }

    private void closeDb(){
        database.close();
    }
}
