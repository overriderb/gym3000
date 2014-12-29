package org.gym.helper;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by anni0913 on 29.12.2014.
 */
public class SharedPreferencesHelper {

    public static final String IS_PICTURE_OPEN = "picture_open_bool";
    public static final String IS_DESCRIPTION_OPEN = "description_open_bool";
    public static final String PICTURE_HEIGHT = "picture_height";
    public static final String DESCRIPTION_HEIGHT = "description_height";

    private static final String PREFERENCES = "gym300_settings";

    private static SharedPreferences.Editor editor;
    private static SharedPreferences preferences;


    private SharedPreferencesHelper(){

    }

    public static void setString(Context context, String key, String value){
        if(editor == null){
            if(preferences == null){
                preferences = context.getSharedPreferences(PREFERENCES, Context.MODE_PRIVATE);
            }
            editor = preferences.edit();
        }
        editor.putString(key, value);
        editor.apply();
    }

    public static void setInt(Context context, String key, int value){
        if(editor == null){
            if(preferences == null){
                preferences = context.getSharedPreferences(PREFERENCES, Context.MODE_PRIVATE);
            }
            editor = preferences.edit();
        }
        editor.putInt(key, value);
        editor.apply();
    }

    public static void setBool(Context context, String key, boolean value){
        if(editor == null){
            if(preferences == null){
                preferences = context.getSharedPreferences(PREFERENCES, Context.MODE_PRIVATE);
            }
            editor = preferences.edit();
        }
        editor.putBoolean(key, value);
        editor.apply();
    }

    public static String getString(Context context, String key){
        String gefValue = "";
        if(preferences == null){
            preferences = context.getSharedPreferences(PREFERENCES, Context.MODE_PRIVATE);
        }
        return preferences.getString(key, gefValue);
    }

    public static int getInt(Context context, String key){
        int gefValue = 0;
        if(preferences == null){
            preferences = context.getSharedPreferences(PREFERENCES, Context.MODE_PRIVATE);
        }
        return preferences.getInt(key, gefValue);
    }

    /**
     * TODO How should we work in this case? Should it return false or Exception in case of this value is absent in preferences?
     */
    public static boolean getBool(Context context, String key){
        boolean gefValue = false;
        if(preferences == null){
            preferences = context.getSharedPreferences(PREFERENCES, Context.MODE_PRIVATE);
        }
        return preferences.getBoolean(key, gefValue);
    }
}
