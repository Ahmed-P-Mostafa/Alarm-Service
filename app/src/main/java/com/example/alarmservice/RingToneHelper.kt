package com.example.alarmservice

import android.content.Context
import android.media.Ringtone
import android.media.RingtoneManager

object RingToneHelper {
    private var ringtone: Ringtone?=null

        // singleton to make the service start and stop ringtone from the same property
    fun getInstance(context: Context):Ringtone?{
            if (ringtone ==null){

                val uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM)
                ringtone = RingtoneManager.getRingtone(context,uri)
            }

            return ringtone
        }

}