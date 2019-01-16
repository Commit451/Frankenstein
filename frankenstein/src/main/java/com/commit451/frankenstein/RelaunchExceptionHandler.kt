package com.commit451.frankenstein

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent

internal class RelaunchExceptionHandler(
        private val context: Context,
        private val intent: Intent,
        private val handler: Thread.UncaughtExceptionHandler
) : Thread.UncaughtExceptionHandler {
    private var relaunchChecker: RelaunchChecker? = null

    init {
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP
                or Intent.FLAG_ACTIVITY_CLEAR_TASK
                or Intent.FLAG_ACTIVITY_NEW_TASK)
    }

    fun setRelaunchChecker(relaunchChecker: RelaunchChecker) {
        this.relaunchChecker = relaunchChecker
    }

    override fun uncaughtException(t: Thread, e: Throwable) {

        if (relaunchChecker?.shouldRelaunch() != false) {
            val pendingIntent = PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_CANCEL_CURRENT)

            //Restart app after some small amount of time
            val mgr = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
            mgr.set(AlarmManager.RTC, System.currentTimeMillis() + 100,
                    pendingIntent)
        }

        //still call the default to allow it to do its thing
        handler.uncaughtException(t, e)
    }
}
