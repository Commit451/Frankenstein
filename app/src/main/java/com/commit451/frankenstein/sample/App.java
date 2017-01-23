package com.commit451.frankenstein.sample;

import android.app.Application;
import android.content.Intent;

import com.commit451.frankenstein.Frankenstein;

public class App extends Application {

    static App app;

    public static App get() {
        return app;
    }

    String userName;

    @Override
    public void onCreate() {
        super.onCreate();
        app = this;

        Frankenstein.register(this, new Intent(this, LaunchActivity.class));
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserName() {
        return userName;
    }
}
