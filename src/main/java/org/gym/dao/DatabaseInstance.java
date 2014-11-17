package org.gym.dao;

/**
 * Created by AndreyNick on 16.11.2014.
 */
public class DatabaseInstance {

    private DatabaseInstance(){
        //helper = new DbHelper();
    }

    private static DatabaseHelper helper;

    public static DatabaseHelper getHelper(){
        return helper;
    }
}
