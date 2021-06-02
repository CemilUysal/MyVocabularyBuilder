package com.example.myvocabularybuilder

import android.app.*
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Context.ALARM_SERVICE
import android.content.Intent
import android.icu.util.GregorianCalendar
import android.os.Build
import androidx.annotation.RequiresApi
import java.util.*

class BootReceiver: BroadcastReceiver() {


    @RequiresApi(Build.VERSION_CODES.N)
    override fun onReceive(context: Context, intent: Intent) {
        if (intent.action == "android.intent.action.BOOT_COMPLETED") {
            MainActivity().schedulePushNotifications()

        }
    }


}