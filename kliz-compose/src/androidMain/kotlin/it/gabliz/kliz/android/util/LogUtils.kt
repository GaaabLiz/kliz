package it.gabliz.kliz.android.util

import timber.log.Timber
import android.os.Build

object LogUtils {

    fun addVerboseLog(message: String) = Timber.v(message)
    fun addErrorLog(message: String) = Timber.e(message)
    fun addDebugLog(message: String) = Timber.d(message)
    fun addInfoLog(message: String) = Timber.i(message)
    fun addWarnLog(message: String) = Timber.w(message)


    fun printDeviceAppInfo() {
        val manufacturer: String = Build.MANUFACTURER
        val model: String = Build.MODEL
        val version: Int = Build.VERSION.SDK_INT
        val versionRelease: String = Build.VERSION.RELEASE
        addDebugLog("Printing device info...")
        addDebugLog("Manufacturer: $manufacturer")
        addDebugLog("Model: $model")
        addDebugLog("Version: $version")
        addDebugLog("Version Release: $versionRelease")
    }

    fun addVerboseLogArray(message: Array<String>) {
        message.forEach {
            addVerboseLog(it)
        }
    }


}