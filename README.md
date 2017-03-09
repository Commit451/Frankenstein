# Frankenstein

Relaunch Android app when it crashes

[![Build Status](https://travis-ci.org/Commit451/Frankenstein.svg?branch=master)](https://travis-ci.org/Commit451/Frankenstein) [![](https://jitpack.io/v/Commit451/Frankenstein.svg)](https://jitpack.io/#Commit451/Frankenstein)

# Usage
```java
public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        //if you have a DEFAULT activity defined
        Frankenstein.register(this);
    }
}
```
If you do not have a default activity defined:
```java
//specific the intent you want to relaunch when the app crashes
Frankenstein.register(this, new Intent(this, LaunchActivity.class));
```
Make sure to call this after setting up any Crash reporting software such as Fabric. Now later, if your app crashes, your `LaunchActivity` will be launched

Android Studio clears LogCat when the app is restarted, so, you might want to surround the registration with a non-debug check:
```java
if (!BuildConfig.DEBUG) {
    Frankenstein.register(this);
}
```

# Why?
It is great to keep things mostly stateless in an Android app, but there are times when you need to load something important when the app launches, and keep a reference to that thing throughout the life of the app. If you application crashes, you will lose all static memory, which is typically where these things reside.

*Only use this if you really do need it*

# Backgrounded
It is important to note that if the app were to crash in the background, it would relaunch into the foreground, which could be frustrating for your user. The sample shows how to counteract this using another library, [Lifeline](https://github.com/Commit451/Lifeline). All this setup takes place in the `App` class.

License
--------

    Copyright 2017 Commit 451

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
