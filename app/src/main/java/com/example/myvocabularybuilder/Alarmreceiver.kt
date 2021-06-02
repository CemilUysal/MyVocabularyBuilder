package com.example.myvocabularybuilder

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Build
import androidx.navigation.NavDeepLinkBuilder

class Alarmreceiver: BroadcastReceiver() {
    private val CHANNEL_ID ="channel_id_exemple"
    private val description = "Test Notification"
    lateinit var notificationChannel: NotificationChannel
    lateinit var notificationManager: NotificationManager
    lateinit var builder: Notification.Builder
    override fun onReceive(context: Context, intent: Intent) {
        showPushNotification(context)
    }
    fun showPushNotification(context: Context){
        var notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        var pendingIntent = NavDeepLinkBuilder(context)
                .setComponentName(MainActivity::class.java)
                .setGraph(R.navigation.navigation_graph)
                .setDestination(R.id.notificationFragment)
                .createPendingIntent()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            notificationChannel = NotificationChannel(CHANNEL_ID,description,NotificationManager.IMPORTANCE_HIGH)
            notificationChannel.enableLights(true)
            notificationChannel.enableVibration(false)
            notificationManager.createNotificationChannel(notificationChannel)

            builder = Notification.Builder(context,CHANNEL_ID)
                    .setContentTitle("Remember Time")
                    .setContentText("Come and Remember your words")
                    .setSmallIcon(R.mipmap.ic_launcher_round)
                    .setLargeIcon(BitmapFactory.decodeResource(context.resources,R.mipmap.ic_launcher_round))
                    .setContentIntent(pendingIntent)
        }
        else{
            builder = Notification.Builder(context)
                    .setContentTitle("Remember Time")
                    .setContentText("Come and Remember your words")
                    .setSmallIcon(R.mipmap.ic_launcher_round)
                    .setLargeIcon(BitmapFactory.decodeResource(context.resources,R.mipmap.ic_launcher_round))
                    .setContentIntent(pendingIntent)
        }
        notificationManager.notify(1234,builder.build())
    }

}