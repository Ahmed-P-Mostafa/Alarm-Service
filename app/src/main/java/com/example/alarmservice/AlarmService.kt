package com.example.alarmservice

import android.R
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.Service
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.media.Ringtone
import android.os.Build
import android.os.IBinder
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat

class AlarmService : Service() {
    private val tag = "AlarmService"

    private lateinit var ringtone: Ringtone
    private var manager: NotificationManager?=null

    override fun onBind(intent: Intent): IBinder? {
        return null
    }

    @RequiresApi(Build.VERSION_CODES.P)
    override fun onStartCommand(intent: Intent, flags: Int, startId: Int): Int {

        Log.d(tag, "onStartCommand: ")
        ringtone = RingToneHelper.getInstance(this)!!
        ringtone.isLooping = true
        manager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        when(intent.action){
            "Start"->{
                Log.d(tag, "onStartCommand: start")
                startAlarm()
                showNotification()
            }
            "Stop"->{
                Log.d(tag, "onStartCommand: stop")
                stopAlarm()
            }
        }
        return START_STICKY

    }

    private fun showNotification() {

        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                "NOTIFICATION_ID",
                "NOTIFICATION_NAME",
                NotificationManager.IMPORTANCE_HIGH
            )
            channel.enableVibration(true)
            channel.enableLights(true)
            channel.lightColor = Color.GREEN
            channel.description = "NOTIFICATION_DESCRIPTION"
            manager?.createNotificationChannel(channel)
        }

        // stop intent for notifying the service to stop ringtone
        val stopIntent = Intent(this, AlarmService::class.java)
        stopIntent.action = "Stop"

        val stopPendingIntent =
            PendingIntent.getService(this, 1001, stopIntent, PendingIntent.FLAG_UPDATE_CURRENT)
        val stopAction =
            NotificationCompat.Action(R.drawable.star_big_off, "Stop", stopPendingIntent)

        val notification = NotificationCompat.Builder(this, "NOTIFICATION_ID")
            .setSmallIcon(R.drawable.ic_lock_idle_alarm).setContentTitle("Alarm")
            .setContentText("Alarm Text").addAction(stopAction)

        manager?.notify(1,notification.build())
    }

    @RequiresApi(Build.VERSION_CODES.P)
    private fun startAlarm(){
        ringtone.play()
    }
    private fun stopAlarm(){
        ringtone.stop()
        manager?.cancel(1)
    }

}