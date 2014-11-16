package org.gym;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import org.gym.dao.DatabaseInstance;
import org.gym.dao.DbHelper;
import org.gym.dao.HelperFactory;

import java.sql.SQLException;

/**
 * Created by anni0913 on 15.10.2014.
 */
public class Gym3000 extends Application {

    private static Context context;

    @Override
    public void onCreate() {
        this.getApplicationContext();
        super.onCreate();



        //HelperFactory.setHelper(getApplicationContext());  //ORM
        /*if(isFirstStart()){
            try {
                //Factory.setPreparedProgramsAndWorkouts(); //ORM
            } catch (SQLException e) {
                Log.e("SQLException onCreate app", e.getMessage());
            }
        }*/

        Factory.setPrograms();
    }

    @Override
    public void onTerminate() {
        //HelperFactory.releaseHelper();
        super.onTerminate();
    }

    public static Context getContext(){
        return context;
    }

    private boolean isFirstStart() {
        Context context = this.getApplicationContext();
        SharedPreferences sp = context.getSharedPreferences("SharedPreferences", 0);
        boolean firstStartKey = sp.getBoolean("key", true);

        if (firstStartKey) {
            SharedPreferences.Editor e = sp.edit();
            e.putBoolean("key", false);
            e.commit();
        }
        return firstStartKey;
    }
}
