package com.commit451.frankenstein.sample

import android.app.Application
import android.content.Intent

import com.commit451.frankenstein.Frankenstein
import com.commit451.frankenstein.RelaunchChecker
import com.commit451.lifeline.Lifeline

class App : Application() {

    companion object {

        private lateinit var app: App

        fun get(): App {
            return app
        }
    }

    var userName: String? = null
    var shouldRelaunch = true

    override fun onCreate() {
        super.onCreate()
        app = this

        Lifeline.init(this)
        val frankenstein = Frankenstein()
        frankenstein.register(this, Intent(this, LaunchActivity::class.java))
        frankenstein.setRelaunchChecker(object : RelaunchChecker {
            override fun shouldRelaunch(): Boolean {
                return Lifeline.isInForeground() && shouldRelaunch
            }
        })
    }
}
