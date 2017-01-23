package com.commit451.frankenstein;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;

/**
 * "Nothing is so painful to the human mind as a great and sudden change."
 * <p>
 * ― Mary Shelley, Frankenstein
 */
public class Frankenstein {

    /**
     * Register for {@link Frankenstein} to relaunch the given intent when there is a crash.
     * @param context the application context
     * @param intent the intent you want to launch when the app crashes
     */
    public static void register(@NonNull Context context, @NonNull Intent intent) {
        if (context instanceof Application) {
            throw new IllegalArgumentException("You need to pass the application context");
        }
        Thread.UncaughtExceptionHandler defaultHandler = Thread.getDefaultUncaughtExceptionHandler();
        RelaunchExceptionHandler handler = new RelaunchExceptionHandler(context, intent, defaultHandler);
        Thread.setDefaultUncaughtExceptionHandler(handler);
    }
}
