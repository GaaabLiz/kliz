@file:Suppress("MemberVisibilityCanBePrivate")

package io.github.gaaabliz.kliz.android.util

import timber.log.Timber
import android.os.Build

object LogUtils {

    class Logger(private val tag : String) {
        fun verbose(message: String) = Timber.tag(tag).v(message)
        fun error(message: String) = Timber.tag(tag).e(message)
        fun debug(message: String) = Timber.tag(tag).d(message)
        fun info(message: String) = Timber.tag(tag).i(message)
        fun warn(message: String) = Timber.tag(tag).w(message)
        fun debugObj(obj : Any?) = Timber.tag(tag).d(obj.toString())
        fun exception(e : Throwable) = Timber.tag(tag).e(e)
        fun verboseArray(message: Array<String>) = run { message.forEach { verbose(it) }}
        fun errorArray(message: Array<String>) = run { message.forEach { error(it) }}
        fun debugArray(message: Array<String>) = run { message.forEach { debug(it) }}
        fun debugObjArray(message: Array<Any?>) = run { message.forEach { debugObj(it) }}
        fun <T> debugObjectList(message: List<T>) = run { message.forEach { debug(it.toString()) }}
    }

    fun addVerboseLog(tag: String, message: String) = Timber.tag(tag).v(message)
    fun addErrorLog(tag: String, message: String) = Timber.tag(tag).e(message)
    fun addDebugLog(tag: String, message: String) = Timber.tag(tag).d(message)
    fun addInfoLog(tag: String, message: String) = Timber.tag(tag).i(message)
    fun addWarnLog(tag: String, message: String) = Timber.tag(tag).w(message)
    fun addDebugObjLog(tag: String, obj : Any?) = Timber.tag(tag).d(obj.toString())

    fun Any?.addDebugLog(tag: String) = addDebugObjLog(tag, this)

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