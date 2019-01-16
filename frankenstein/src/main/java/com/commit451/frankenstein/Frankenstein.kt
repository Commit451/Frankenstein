package com.commit451.frankenstein

import android.content.ComponentName
import android.content.Context
import android.content.Intent

import android.content.Intent.ACTION_MAIN
import android.content.Intent.CATEGORY_DEFAULT

/**
 * "Nothing is so painful to the human mind as a great and sudden change."
 *
 * ― Mary Shelley, Frankenstein
 */
class Frankenstein {

    private var handler: RelaunchExceptionHandler? = null

    /**
     * Register for [Frankenstein] to relaunch the given intent when there is a crash.
     *
     * @param context the application context
     * @param intent  the intent you want to launch when the app crashes
     */
    @JvmOverloads
    fun register(context: Context, intent: Intent = getRestartIntent(context)) {
        val defaultHandler = Thread.getDefaultUncaughtExceptionHandler()
        handler = RelaunchExceptionHandler(context.applicationContext, intent, defaultHandler)
        Thread.setDefaultUncaughtExceptionHandler(handler)
    }

    /**
     * Set the checker to see if the app should be relaunched or not at the time of a crash
     * @param checker the checker
     */
    fun setRelaunchChecker(checker: RelaunchChecker) {
        if (handler == null) {
            throw IllegalStateException("You need to call register before setting the relaunch checker")
        }
        handler!!.setRelaunchChecker(checker)
    }

    private fun getRestartIntent(context: Context): Intent {
        val defaultIntent = Intent(ACTION_MAIN, null)
        defaultIntent.addCategory(CATEGORY_DEFAULT)

        val packageName = context.packageName
        val packageManager = context.packageManager
        for (resolveInfo in packageManager.queryIntentActivities(defaultIntent, 0)) {
            val activityInfo = resolveInfo.activityInfo
            if (activityInfo.packageName == packageName) {
                defaultIntent.component = ComponentName(packageName, activityInfo.name)
                return defaultIntent
            }
        }

        throw IllegalStateException("Unable to determine default activity for "
                + packageName
                + ". Does an activity specify the DEFAULT category in its intent filter?")
    }
}
