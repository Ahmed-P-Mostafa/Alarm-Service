package com.example.alarmservice

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
    /**
     * start the service from here
     */

    fun start(view: View) {
        val startIntent =Intent(this,AlarmService::class.java)
        startIntent.action = "Start"

            startService(startIntent)

    }
}