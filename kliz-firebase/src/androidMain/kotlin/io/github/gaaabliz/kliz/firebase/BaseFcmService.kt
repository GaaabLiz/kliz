package io.github.gaaabliz.kliz.firebase

import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import io.github.gaaabliz.kliz.android.util.LogUtils


abstract class BaseFcmServiceLiz(
    private val logTag: String
) : FirebaseMessagingService() {

    val logger = LogUtils.Logger(logTag)

    override fun onNewToken(token: String) {
        super.onNewToken(token)
        logger.warn("NEW TOKEN RECEIVED: $token")
    }

    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        super.onMessageReceived(remoteMessage)
        logger.debug("New FCM message from: \"${remoteMessage.from}\".")
        if (remoteMessage.data.isNotEmpty()) {
            logger.debug("New FCM message contains payload: \"${remoteMessage.data}\".")
        } else {
            logger.debug("New FCM message doesn't contains payload: \"${remoteMessage.data}\".")
        }
    }

    protected open fun handleRemoteMessage(
        remoteMessage: RemoteMessage,
        hasNotificationCallback : (RemoteMessage.Notification) -> Unit,
    ) {
        if(remoteMessage.notification != null) {
            val not = remoteMessage.notification!!
            logger.debug("FCM Message contains notification!!.")
            logger.debug("FCM Message notification title: \"${not.title}\".")
            logger.debug("FCM Message notification body: \"${not.body}\".")
            hasNotificationCallback(not)
        } else {
            logger.debug("Message does not contain any notification!.")
        }
    }

    override fun onMessageSent(msgId: String) {
        super.onMessageSent(msgId)
        logger.debug("New FCM sent: $msgId")
    }
}

