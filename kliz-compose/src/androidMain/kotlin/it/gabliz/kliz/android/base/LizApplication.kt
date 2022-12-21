package it.gabliz.kliz.android.base

import android.app.Application
import it.gabliz.kliz.android.util.SystemUtils
import it.gabliz.kliz.android.util.LogUtils
import timber.log.Timber


class LizApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
        LogUtils.addVerboseLogArray(SystemUtils.getDeviceInformationArray())
        SystemUtils.setupExceptionHandler { thread, throwable ->
            LogUtils.addErrorLog("Application crashed on thread ${thread.name}!")
            LogUtils.addErrorLog("Crash info: ${throwable.message}")
        }
    }


}