package it.gabliz.kliz.android.util

import timber.log.Timber
import android.os.Build

object LogUtils {

    fun addVerboseLog(message: String) = Timber.v(message)
    fun addErrorLog(message: String) = Timber.e(message)
    fun addDebugLog(message: String) = Timber.d(message)
    fun addInfoLog(message: String) = Timber.i(message)
    fun addWarnLog(message: String) = Timber.w(message)

    fun addVerboseLog(tag: String, message: String) = Timber.tag(tag).v(message)
    fun addErrorLog(tag: String, message: String) = Timber.tag(tag).e(message)
    fun addDebugLog(tag: String, message: String) = Timber.tag(tag).d(message)
    fun addInfoLog(tag: String, message: String) = Timber.tag(tag).i(message)
    fun addWarnLog(tag: String, message: String) = Timber.tag(tag).w(message)



    fun printDeviceAppInfo(appLogTag : String = "") {
        val manufacturer: String = Build.MANUFACTURER
        val model: String = Build.MODEL
        val version: Int = Build.VERSION.SDK_INT
        val versionRelease: String = Build.VERSION.RELEASE
        addDebugLog(appLogTag, "Printing device info...")
        addDebugLog(appLogTag,"Manufacturer: $manufacturer")
        addDebugLog(appLogTag,"Model: $model")
        addDebugLog(appLogTag,"Version: $version")
        addDebugLog(appLogTag,"Version Release: $versionRelease")
    }

    fun addVerboseLogArray(message: Array<String>, appLogTag : String = "") {
        message.forEach {
            addVerboseLog(appLogTag, it)
        }
    }


}