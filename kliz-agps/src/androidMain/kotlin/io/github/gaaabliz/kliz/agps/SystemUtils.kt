package io.github.gaaabliz.kliz.agps

import android.content.Context
import com.google.android.gms.common.ConnectionResult
import com.google.android.gms.common.GoogleApiAvailability
import io.github.gaaabliz.kliz.android.util.LogUtils

object SystemUtils {

    private fun checkPlayServices(
        context: Context,
        logger : LogUtils.Logger
    ) {
        when(getPlayServicesStatus(context)) {
            PlayServicesStatus.ACTIVE -> logger.debug("Play Services are active!")
            PlayServicesStatus.ERROR_BUT_USER_CAN_FIX -> logger.warn("Play Services are not active, but user can fix it! Some backend features may not work!")
            PlayServicesStatus.ERROR_BUT_USER_CANT_FIX -> logger.error("Play Services are not active!! This is a problem!")
        }
    }

    private fun getPlayServicesStatus(context: Context) : PlayServicesStatus {
        val googleApiAvailability = GoogleApiAvailability.getInstance()
        val resultCode = googleApiAvailability.isGooglePlayServicesAvailable(context)
        if (resultCode != ConnectionResult.SUCCESS) {
            return if (googleApiAvailability.isUserResolvableError(resultCode)) {
                PlayServicesStatus.ERROR_BUT_USER_CAN_FIX
            } else {
                PlayServicesStatus.ERROR_BUT_USER_CANT_FIX
            }
        }
        //logger.info("Google play service are active.")
        return PlayServicesStatus.ACTIVE
    }

}