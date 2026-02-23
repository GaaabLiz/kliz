package io.github.gaaabliz.kliz.android.base

import android.content.res.Resources
import androidx.annotation.RawRes
import android.content.ClipData
import android.content.ClipboardManager
import android.content.ContentUris
import android.content.Context
import android.net.Uri
import android.os.Looper
import android.webkit.URLUtil
import androidx.core.content.pm.ShortcutInfoCompat
import androidx.core.content.pm.ShortcutManagerCompat

fun Context.copyToClipboard(text: CharSequence){
    val clipboard = getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
    val clip = ClipData.newPlainText("label",text)
    clipboard.setPrimaryClip(clip)
}

fun Context.addDynamicShortcutCompat(id: String, shortcut: ShortcutInfoCompat) {
    if (ShortcutManagerCompat.getDynamicShortcuts(this).any { it.id == id }.not()) {
        try {
            ShortcutManagerCompat.addDynamicShortcuts(this, mutableListOf(shortcut))
        } catch (_: Exception) {
        }
    }
}


fun Resources.getRawTextFile(@RawRes id: Int) = openRawResource(id).bufferedReader().use { it.readText() }
fun Uri.withAppendedId(id: Long): Uri = ContentUris.withAppendedId(this, id)
inline val isMainThread: Boolean get() = Looper.getMainLooper() == Looper.myLooper()
fun String?.isNetworkUrl(): Boolean = URLUtil.isNetworkUrl(this)
fun String?.isValidUrl(): Boolean = URLUtil.isValidUrl(this)

