package io.github.gaaabliz.kliz.android.base

import android.app.Application
import io.github.gaaabliz.kliz.android.util.SystemUtils
import io.github.gaaabliz.kliz.android.util.LogUtils
import timber.log.Timber
import java.util.*


abstract class BaseApplication(private val appLogTag : String) : Application() {

    val logger = LogUtils.Logger(appLogTag)

    override fun onCreate() {
        super.onCreate()
        instance = this
        setupLogs()
        setupExceptionHandler()
        printDeviceInfo()
        checkBackgroundRestrictions()
    }

    private fun checkBackgroundRestrictions() {
        when(SystemUtils.isAppBackgroundRestricted(this)) {
            true -> logger.warn("App is restricted to run in background!")
            false -> logger.debug("App is not restricted to run in background!")
            null -> logger.warn("App background restriction status is unknown! Maybe the app is not SDK >28?")
        }
    }

    private fun setupExceptionHandler() {
        SystemUtils.setupExceptionHandler { thread, throwable ->
            logger.error("Application crashed on thread ${thread.name}!")
            logger.error("Crash info: ${throwable.message}")
        }
    }

    private fun setupLogs() = run { Timber.plant(Timber.DebugTree()) }
    private fun printDeviceInfo() = run { logger.verboseArray(SystemUtils.getDeviceInformationArray()) }
    fun setMainLocale(locale : Locale) = run { resources.configuration.setLocale(locale) }

    override fun onLowMemory() {
        super.onLowMemory()
        logger.warn("onLowMemory problem caught!")
    }

    companion object {
        lateinit var instance: BaseApplication private set
    }
}