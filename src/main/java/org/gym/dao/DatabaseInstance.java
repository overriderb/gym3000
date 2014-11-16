package org.gym.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import org.gym.Gym3000;

/**
 * Created by AndreyNick on 16.11.2014.
 */
public class DatabaseInstance {

    private DatabaseInstance(){
        //helper = new DbHelper();
    }

    private static DbHelper helper;

    public static DbHelper getHelper(){
        return helper;
    }
}
