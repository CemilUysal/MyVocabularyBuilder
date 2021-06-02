package com.example.myvocabularybuilder

import android.app.*
import android.content.Context
import android.content.Intent

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.annotation.RequiresApi
import java.util.*


class MainActivity : AppCompatActivity() {


    lateinit  var alarmManager :AlarmManager
    lateinit var pendingIntent: PendingIntent

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        schedulePushNotifications()

    }


    @RequiresApi(Build.VERSION_CODES.N)
    fun schedulePushNotifications() {
        alarmManager = getSystemService(Context.ALARM_SERVICE) as AlarmManager

        var mcurrentCal = Calendar.getInstance()
        var mfiringCal = Calendar.getInstance()
        mfiringCal.set(Calendar.HOUR_OF_DAY, 17)
        mfiringCal.set(Calendar.MINUTE, 30)
        mfiringCal.set(Calendar.SECOND,0)
        var  intendedTime = mfiringCal.timeInMillis
        var currentTime = mcurrentCal.timeInMillis
        if(intendedTime>=currentTime){
            val intent = Intent(this, Alarmreceiver::class.java)
            pendingIntent = PendingIntent.getBroadcast(this,100,intent,PendingIntent.FLAG_UPDATE_CURRENT)
            alarmManager.setRepeating(
                    AlarmManager.RTC_WAKEUP,
                    mfiringCal.timeInMillis,
                    AlarmManager.INTERVAL_DAY,
                    pendingIntent
            )
        }
        else{
            val intent = Intent(this, Alarmreceiver::class.java)
            pendingIntent = PendingIntent.getBroadcast(this,100,intent,PendingIntent.FLAG_UPDATE_CURRENT)
            mfiringCal.add(Calendar.DAY_OF_MONTH,1)
            intendedTime = mfiringCal.timeInMillis
            alarmManager.setRepeating(
                    AlarmManager.RTC,
                    intendedTime,
                    AlarmManager.INTERVAL_DAY,
                    pendingIntent
            )
        }

        var  nfiringCal = Calendar.getInstance()
        var ncurrentCal = Calendar.getInstance()
        nfiringCal.set(Calendar.HOUR_OF_DAY, 21)
        nfiringCal.set(Calendar.MINUTE, 30)
        nfiringCal.set(Calendar.SECOND,0)
        var  intendedTime1 = nfiringCal.timeInMillis
        var currentTime1 = ncurrentCal.timeInMillis
        if(intendedTime1>=currentTime1){
            val intent = Intent(this, Alarmreceiver::class.java)
            pendingIntent = PendingIntent.getBroadcast(this,101,intent,PendingIntent.FLAG_UPDATE_CURRENT)
            alarmManager.setRepeating(
                    AlarmManager.RTC_WAKEUP,
                    nfiringCal.timeInMillis,
                    AlarmManager.INTERVAL_DAY,
                    pendingIntent
            )
        }
        else{
            val intent = Intent(this, Alarmreceiver::class.java)
            pendingIntent = PendingIntent.getBroadcast(this,101,intent,PendingIntent.FLAG_UPDATE_CURRENT)
            nfiringCal.add(Calendar.DAY_OF_MONTH,1)
            intendedTime1 = nfiringCal.timeInMillis
            alarmManager.setRepeating(
                    AlarmManager.RTC,
                    intendedTime1,
                    AlarmManager.INTERVAL_DAY,
                    pendingIntent
            )
        }

    }

}