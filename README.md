# Frankenstein

[![Build Status](https://travis-ci.org/Commit451/Frankenstein.svg?branch=master)](https://travis-ci.org/Commit451/Frankenstein) [![](https://jitpack.io/v/Commit451/Frankenstein.svg)](https://jitpack.io/#Commit451/Frankenstein)

# Usage
```java
public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        //make sure to call this after setting up any Crash reporting software
        Frankenstein.register(this, new Intent(this, LaunchActivity.class));
    }
}
```

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
