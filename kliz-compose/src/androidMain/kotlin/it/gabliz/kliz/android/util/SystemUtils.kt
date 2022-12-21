@file:SuppressWarnings("WeakerAccess")
package it.gabliz.kliz.android.util

import kotlin.system.exitProcess

object SystemUtils {

    fun getDeviceManufacturer(): String = android.os.Build.MANUFACTURER
    fun getDeviceModel(): String = android.os.Build.MODEL
    fun getDeviceVersion(): Int = android.os.Build.VERSION.SDK_INT
    fun getDeviceVersionRelease(): String = android.os.Build.VERSION.RELEASE
    fun getDeviceBrand(): String = android.os.Build.BRAND
    fun getDeviceProduct(): String = android.os.Build.PRODUCT
    fun getDeviceBoard(): String = android.os.Build.BOARD
    fun getDeviceBootloader(): String = android.os.Build.BOOTLOADER
    fun getDeviceDevice(): String = android.os.Build.DEVICE
    fun getDeviceHardware(): String = android.os.Build.HARDWARE
    fun getDeviceHost(): String = android.os.Build.HOST
    fun getDeviceId(): String = android.os.Build.ID
    fun getDeviceDisplay(): String = android.os.Build.DISPLAY
    fun getDeviceFingerprint(): String = android.os.Build.FINGERPRINT
    fun getDeviceRadioVersion(): String = android.os.Build.getRadioVersion()
    fun getDeviceTags(): String = android.os.Build.TAGS
    fun getDeviceTime(): Long = android.os.Build.TIME
    fun getDeviceType(): String = android.os.Build.TYPE
    fun getDeviceUser(): String = android.os.Build.USER
    fun getDeviceCodename(): String = android.os.Build.VERSION.CODENAME
    fun getDeviceIncremental(): String = android.os.Build.VERSION.INCREMENTAL
    fun getDeviceSecurityPatch(): String = android.os.Build.VERSION.SECURITY_PATCH
    fun getDeviceBaseOS(): String = android.os.Build.VERSION.BASE_OS
    fun getDevicePreviewSdkInt(): Int = android.os.Build.VERSION.PREVIEW_SDK_INT

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

}