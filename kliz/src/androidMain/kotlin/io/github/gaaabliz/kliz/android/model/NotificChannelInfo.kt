package io.github.gaaabliz.kliz.android.model

import android.app.NotificationManager

data class NotificChannelInfo(
    val channelId : String,
    val systemName : String,
    val name : String,
    val importance : Int = NotificationManager.IMPORTANCE_DEFAULT,
    val settingTextTitle : String? = null,
    val settingTextDescription : String? = null,
    val enabledLights : Boolean = true,
)
