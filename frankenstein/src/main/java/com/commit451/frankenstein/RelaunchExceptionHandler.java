package com.commit451.frankenstein;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

class RelaunchExceptionHandler implements Thread.UncaughtExceptionHandler {

    private Context context;
    private Intent intent;
    private Thread.UncaughtExceptionHandler handler;

    RelaunchExceptionHandler(Context context, Intent relaunchIntent, Thread.UncaughtExceptionHandler defaultUncaughtExceptionHandler) {
        this.context = context;
        intent = relaunchIntent;
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP
                | Intent.FLAG_ACTIVITY_CLEAR_TASK
                | Intent.FLAG_ACTIVITY_NEW_TASK);
        handler = defaultUncaughtExceptionHandler;
    }

    @Override
    public void uncaughtException(Thread t, Throwable e) {

        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_CANCEL_CURRENT);

        //Restart app after some time
        AlarmManager mgr = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        mgr.set(AlarmManager.RTC, System.currentTimeMillis() + 100,
                pendingIntent);

        handler.uncaughtException(t, e);
    }
}
