package org.gym.logging;

import android.util.Log;

/**
 * Main logger for Gym3000 application
 */
public final class Logger {
    private static final String TAG = "Gym3000";

    public static void verbose(String message, Class clazz) {
        Log.v(TAG, formatMessage(message, clazz));
    }

    public static void info(String message, Class clazz) {
        Log.i(TAG, formatMessage(message, clazz));
    }

    public static void debug(String message, Class clazz) {
        Log.d(TAG, formatMessage(message, clazz));
    }

    public static void debug(String message, Throwable throwable, Class clazz) {
        Log.d(TAG, formatMessage(message, clazz), throwable);
    }

    public static void warning(String message, Class clazz) {
        Log.w(TAG, formatMessage(message, clazz));
    }

    public static void warning(String message, Throwable throwable, Class clazz) {
        Log.w(TAG, formatMessage(message, clazz), throwable);
    }

    public static void error(String message, Class clazz) {
        Log.e(TAG, formatMessage(message, clazz));
    }

    public static void error(String message, Throwable throwable, Class clazz) {
        Log.e(TAG, formatMessage(message, clazz), throwable);
    }

    /**
     * Format message for logging in useful way
     * TODO: implement logging of method name, line in code, etc
     *
     * @param clazz   class that use logger now
     * @param message input message for logging
     * @return formatted string for logging
     */
    private static String formatMessage(String message, Class clazz) {
        return "[" + clazz.getName() + "]: " + message;
    }
}