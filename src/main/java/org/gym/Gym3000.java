package org.gym;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import org.gym.dao.HelperFactory;

import java.sql.SQLException;

/**
 * Created by anni0913 on 15.10.2014.
 */
public class Gym3000 extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        HelperFactory.setHelper(getApplicationContext());
        if(isFirstStart()){
            try {
                Factory.setPreparedProgramsAndWorkouts();
            } catch (SQLException e) {
                Log.e("SQLException onCreate app", e.getMessage());
            }
        }
    }

    @Override
    public void onTerminate() {
        HelperFactory.releaseHelper();
        super.onTerminate();
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
