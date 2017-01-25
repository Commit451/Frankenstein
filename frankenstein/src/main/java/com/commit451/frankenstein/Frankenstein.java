package com.commit451.frankenstein;

import android.app.Application;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.support.annotation.NonNull;

import static android.content.Intent.ACTION_MAIN;
import static android.content.Intent.CATEGORY_DEFAULT;

/**
 * "Nothing is so painful to the human mind as a great and sudden change."
 * <p>
 * ― Mary Shelley, Frankenstein
 */
public class Frankenstein {

    /**
     * Register for {@link Frankenstein} to relaunch the DEFAULT intent when there is a crash.
     *
     * @param context the application context
     */
    public static void register(@NonNull Context context) {
        register(context, getRestartIntent(context));
    }

    /**
     * Register for {@link Frankenstein} to relaunch the given intent when there is a crash.
     *
     * @param context the application context
     * @param intent  the intent you want to launch when the app crashes
     */
    public static void register(@NonNull Context context, @NonNull Intent intent) {
        if (!(context instanceof Application)) {
            throw new IllegalArgumentException("You need to pass the application context");
        }
        Thread.UncaughtExceptionHandler defaultHandler = Thread.getDefaultUncaughtExceptionHandler();
        RelaunchExceptionHandler handler = new RelaunchExceptionHandler(context, intent, defaultHandler);
        Thread.setDefaultUncaughtExceptionHandler(handler);
    }

    private static Intent getRestartIntent(Context context) {
        Intent defaultIntent = new Intent(ACTION_MAIN, null);
        defaultIntent.addCategory(CATEGORY_DEFAULT);

        String packageName = context.getPackageName();
        PackageManager packageManager = context.getPackageManager();
        for (ResolveInfo resolveInfo : packageManager.queryIntentActivities(defaultIntent, 0)) {
            ActivityInfo activityInfo = resolveInfo.activityInfo;
            if (activityInfo.packageName.equals(packageName)) {
                defaultIntent.setComponent(new ComponentName(packageName, activityInfo.name));
                return defaultIntent;
            }
        }

        throw new IllegalStateException("Unable to determine default activity for "
                + packageName
                + ". Does an activity specify the DEFAULT category in its intent filter?");
    }
}
