package org.gym;

import android.app.Application;
import org.gym.dao.HelperFactory;

/**
 * Created by anni0913 on 15.10.2014.
 */
public class Gym3000 extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        HelperFactory.setHelper(getApplicationContext());
    }
    @Override
    public void onTerminate() {
        HelperFactory.releaseHelper();
        super.onTerminate();
    }
}
