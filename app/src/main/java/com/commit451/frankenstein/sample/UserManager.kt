package com.commit451.frankenstein.sample

object UserManager {

    fun username(): String {
        return App.get().userName ?: "Not found"
    }
}
