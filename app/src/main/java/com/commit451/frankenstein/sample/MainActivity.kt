package com.commit451.frankenstein.sample

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        textUsername.text = UserManager.username()
        buttonCrash.setOnClickListener { throw IllegalArgumentException("blah") }
        buttonCrashDelayed.setOnClickListener { v -> v.postDelayed({ throw IllegalStateException("Blah") }, TimeUnit.SECONDS.toMillis(3)) }
        buttonSecondActivity.setOnClickListener {
            val intent = Intent(this, SecondActivity::class.java)
            startActivity(intent)
        }
        switchShouldRelaunch.isChecked = App.get().shouldRelaunch
        switchShouldRelaunch.setOnCheckedChangeListener { _, isChecked ->
            App.get().shouldRelaunch = isChecked
        }
    }
}
