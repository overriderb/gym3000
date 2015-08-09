package org.gym;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.widget.FrameLayout;
import org.gym.activity.R;
import org.gym.domain.UserEntity;
import org.gym.helper.SharedPreferencesHelper;
import org.gym.repository.DatabaseHelper;
import org.gym.repository.InitializeUtils;

/**
 * Main application class
 */
public class Gym3000 extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        DatabaseHelper.init(this);
        if(false){                                //For first start of application please change change isFirstStart()
            InitializeUtils.initTestPrograms();
            setInitialPreferences();              //to true for correct setting to DB default programs
        }
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        DatabaseHelper.closeDb();
    }

    @Override
    public void onLowMemory(){
        super.onLowMemory();
        DatabaseHelper.closeDb();
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

    private void setInitialPreferences() {
        SharedPreferencesHelper.setBool(this, SharedPreferencesHelper.IS_PICTURE_OPEN, true);
        SharedPreferencesHelper.setBool(this, SharedPreferencesHelper.IS_DESCRIPTION_OPEN, true);
        SharedPreferencesHelper.setInt(this, SharedPreferencesHelper.PICTURE_HEIGHT, 200);
        SharedPreferencesHelper.setInt(this, SharedPreferencesHelper.DESCRIPTION_HEIGHT, FrameLayout.LayoutParams.WRAP_CONTENT);
    }
}
