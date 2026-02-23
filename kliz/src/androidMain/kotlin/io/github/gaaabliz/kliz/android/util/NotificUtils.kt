package io.github.gaaabliz.kliz.android.util

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import androidx.core.app.NotificationCompat
import io.github.gaaabliz.kliz.android.model.NotificChannelInfo
import kotlin.random.Random

object NotificUtils {

    fun createChannel(
        context : Context,
        channelId:String,
        channelSystemName:String,
        importance : Int = NotificationManager.IMPORTANCE_DEFAULT
    ) {
        val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val channel = NotificationChannel(
            channelId,
            channelSystemName,
            importance
        )
        channel.enableLights(true)
        notificationManager.createNotificationChannel(channel)
    }

    fun createChannel(info : NotificChannelInfo, context: Context) {
        val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val channel = NotificationChannel(
            info.channelId,
            info.systemName,
            info.importance
        )
        if(info.enabledLights) channel.enableLights(true)
        notificationManager.createNotificationChannel(channel)
    }

    fun createAndSendNotification(
        context: Context,
        channelId: String,
        title : String,
        body : String,
        priority : Int = NotificationCompat.PRIORITY_DEFAULT,
        smallIcon : Int,
    ) {
        val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val notificationID = Random.nextInt()
        val notification = NotificationCompat.Builder(context, channelId)
            .setContentTitle(title)
            .setContentText(body)
            .setAutoCancel(true)
            .setPriority(priority)
            .setWhen(System.currentTimeMillis())
            .setSmallIcon(smallIcon)
            .build()
        notificationManager.notify(notificationID, notification)
    }

}