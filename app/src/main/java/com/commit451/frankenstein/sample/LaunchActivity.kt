package com.commit451.frankenstein.sample

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class LaunchActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //Think of this being a long running operation to fetch the user, which we do on launch
        //and keep in static memory
        App.get().userName = Build.MODEL
        Toast.makeText(this, "Launch activity launched", Toast.LENGTH_SHORT)
                .show()
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }
}
