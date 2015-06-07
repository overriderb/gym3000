package org.gym;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import org.gym.repository.DatabaseHelper;
import org.gym.repository.InitializeUtils;

/**
 * Created by anni0913 on 15.10.2014.
 */
public class Gym3000 extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        DatabaseHelper.init(this);
        if(isFirstStart()){                                //For first start of application please change change isFirstStart()
            InitializeUtils.initTestPrograms();            //to true for correct setting to DB default programs
        }
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
    }


    /**
     * Method needs for checking, is this a first start of application on this phone
     */
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
