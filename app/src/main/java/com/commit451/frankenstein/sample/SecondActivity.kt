package com.commit451.frankenstein.sample

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_second.*

import java.util.concurrent.TimeUnit

class SecondActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        textUsername.text = UserManager.username()
        buttonCrash.setOnClickListener { throw IllegalArgumentException("blah") }
        buttonCrashDelayed.setOnClickListener { v -> v.postDelayed({ throw IllegalStateException("Blah") }, TimeUnit.SECONDS.toMillis(3)) }
    }
}
