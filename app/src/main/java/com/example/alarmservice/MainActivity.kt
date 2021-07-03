package com.example.alarmservice

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import java.util.*

class MainActivity : AppCompatActivity() {
    private val TAG = "TAG MainActivity"
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

    fun toast(view: View) {
        Log.d(TAG, "toast: ")
        val alarmManager = getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val toastIntent = Intent(this,MyReceiver::class.java)
        toastIntent.action = "toast"
        val pendingIntent = PendingIntent.getBroadcast(this,100,toastIntent,PendingIntent.FLAG_ONE_SHOT)
        val cal = Calendar.getInstance()
        val min = AlarmManager.INTERVAL_FIFTEEN_MINUTES / 15
        alarmManager.setInexactRepeating(AlarmManager.RTC_WAKEUP,cal.timeInMillis,min,pendingIntent)
    }
}