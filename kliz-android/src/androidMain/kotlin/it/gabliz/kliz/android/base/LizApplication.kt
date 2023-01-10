package it.gabliz.kliz.android.base

import android.app.Application
import it.gabliz.kliz.android.util.SystemUtils
import it.gabliz.kliz.android.util.LogUtils
import it.gabliz.kliz.android.util.LogUtils.addWarnLog
import timber.log.Timber


abstract class LizApplication(private val appLogTag : String) : Application() {

    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
        LogUtils.addVerboseLogArray(SystemUtils.getDeviceInformationArray(), appLogTag)
        SystemUtils.setupExceptionHandler { thread, throwable ->
            LogUtils.addErrorLog(appLogTag, "Application crashed on thread ${thread.name}!")
            LogUtils.addErrorLog(appLogTag, "Crash info: ${throwable.message}")
        }
    }

    override fun onLowMemory() {
        super.onLowMemory()
        addWarnLog(appLogTag, "onLowMemory problem caught!")
    }
}