@file:SuppressWarnings("WeakerAccess")

package io.github.gaaabliz.kliz.android.util

import android.content.Context
import android.app.ActivityManager
import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context.ACTIVITY_SERVICE
import android.content.res.Resources
import android.os.Build
import androidx.annotation.RawRes
import kotlin.system.exitProcess

object SystemUtils {

    fun getDeviceManufacturer(): String = Build.MANUFACTURER
    fun getDeviceModel(): String = Build.MODEL
    fun getDeviceVersion(): Int = Build.VERSION.SDK_INT
    fun getDeviceVersionRelease(): String = Build.VERSION.RELEASE
    fun getDeviceBrand(): String = Build.BRAND
    fun getDeviceProduct(): String = Build.PRODUCT
    fun getDeviceBoard(): String = Build.BOARD
    fun getDeviceBootloader(): String = Build.BOOTLOADER
    fun getDeviceDevice(): String = Build.DEVICE
    fun getDeviceHardware(): String = Build.HARDWARE
    fun getDeviceHost(): String = Build.HOST
    fun getDeviceId(): String = Build.ID
    fun getDeviceDisplay(): String = Build.DISPLAY
    fun getDeviceFingerprint(): String = Build.FINGERPRINT
    fun getDeviceRadioVersion(): String = Build.getRadioVersion()
    fun getDeviceTags(): String = Build.TAGS
    fun getDeviceTime(): Long = Build.TIME
    fun getDeviceType(): String = Build.TYPE
    fun getDeviceUser(): String = Build.USER
    fun getDeviceCodename(): String = Build.VERSION.CODENAME
    fun getDeviceIncremental(): String = Build.VERSION.INCREMENTAL
    fun getDeviceSecurityPatch(): String = Build.VERSION.SECURITY_PATCH
    fun getDeviceBaseOS(): String = Build.VERSION.BASE_OS
    fun getDevicePreviewSdkInt(): Int = Build.VERSION.PREVIEW_SDK_INT

    fun getDeviceInformationArray(): Array<String> {
        return arrayOf(
            "Manufacturer: ${getDeviceManufacturer()}",
            "Model: ${getDeviceModel()}",
            "Version: ${getDeviceVersion()}",
            "Version Release: ${getDeviceVersionRelease()}",
            "Brand: ${getDeviceBrand()}",
            "Product: ${getDeviceProduct()}",
            "Device: ${getDeviceDevice()}",
            "Hardware: ${getDeviceHardware()}",
            "Host: ${getDeviceHost()}",
            "ID: ${getDeviceId()}",
            "Tags: ${getDeviceTags()}",
            "Type: ${getDeviceType()}",
            "User: ${getDeviceUser()}",
            "Base OS: ${getDeviceBaseOS()}",
        )
    }

    fun setupExceptionHandler(onHandlerCalled : (Thread, Throwable) -> Unit) {
        val oldHandler = Thread.getDefaultUncaughtExceptionHandler()
        Thread.setDefaultUncaughtExceptionHandler { paramThread, paramThrowable ->
            onHandlerCalled(paramThread, paramThrowable)
            if (oldHandler != null) {
                oldHandler.uncaughtException(paramThread, paramThrowable)
            } else {
                exitProcess(2)
            }
        }
    }

    fun isAppBackgroundRestricted(context: Context) : Boolean? {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P){
            val am = context.getSystemService(ACTIVITY_SERVICE) as ActivityManager
            am.isBackgroundRestricted
        } else{
            null
        }
    }

    fun Context.copyToClipboard(text: CharSequence){
        val clipboard = getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
        val clip = ClipData.newPlainText("label",text)
        clipboard.setPrimaryClip(clip)
    }

    fun Resources.getRawTextFile(@RawRes id: Int) = openRawResource(id).bufferedReader().use { it.readText() }

    fun sleep(l: Long) {
        try {
            Thread.sleep(l)
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }
    }

}