package io.github.gaaabliz.kliz.android.util

import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import androidx.activity.ComponentActivity
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat

object PermissionUtils {

    fun hasPermissions(context: Context, permissions: Array<String>): Boolean = permissions.all {
        ActivityCompat.checkSelfPermission(context, it) == PackageManager.PERMISSION_GRANTED
    }

    private fun getPermissionRequestLauncher(
        act: ComponentActivity,
        logger: LogUtils.Logger,
    ) : ActivityResultLauncher<Array<String>> {
        return act.registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) { permissions ->
            val granted = permissions.entries.all { it.value }
            if (granted) {
                logger.info( "All permission granted!")
            } else {
                logger.warn("Some permission has not been granted!")
            }
        }
    }

    fun requestPermissions(
        act: ComponentActivity,
        permissions: Array<String>,
        logger: LogUtils.Logger,
    ) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            getPermissionRequestLauncher(
                act = act,
                logger,
            ).launch(permissions)
        } else {
            logger.error("Permission request not supported on this device!")
        }
    }

}