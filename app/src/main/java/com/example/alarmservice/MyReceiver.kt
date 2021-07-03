package com.example.alarmservice

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.Toast

class MyReceiver : BroadcastReceiver() {
    private val TAG = "MyReceiver"

    override fun onReceive(context: Context, intent: Intent) {
        when(intent.action){
            "toast"->{
                Log.d(TAG, "onReceive: ")
                Toast.makeText(context, "Toast", Toast.LENGTH_SHORT).show()
            }
        }
    }
}