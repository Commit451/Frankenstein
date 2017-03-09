package com.commit451.frankenstein;

/**
 * Determines if the app should be relaunched or not
 */
public interface RelaunchChecker {

    /**
     * Relaunch check, which occurs at the time of a crash
     * @return true if you want the app to relaunch, false to prevent the app from relaunching
     */
    boolean shouldRelaunch();
}
