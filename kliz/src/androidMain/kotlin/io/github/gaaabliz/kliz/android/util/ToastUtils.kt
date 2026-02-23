package io.github.gaaabliz.kliz.android.util

import android.annotation.SuppressLint
import android.content.Context
import android.os.Handler
import android.os.Looper
import android.widget.Toast
import androidx.annotation.AnyThread
import androidx.annotation.MainThread
import androidx.annotation.StringRes
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.lang.ref.WeakReference

object ToastUtils {

    private val handler = Handler(Looper.getMainLooper())
    private var toast: WeakReference<Toast>? = null

    @AnyThread
    fun show(context: Context, @StringRes strResId: Int) {
        show(context, context.getString(strResId))
    }

    fun showLong(context: Context, @StringRes strResId: Int) {
        Toast.makeText(context, context.getString(strResId), Toast.LENGTH_LONG).show()
    }

    fun showLong(context: Context, text : String) {
        Toast.makeText(context, text, Toast.LENGTH_LONG).show()
    }

    fun showShort(context: Context, @StringRes strResId: Int) {
        Toast.makeText(context, context.getString(strResId), Toast.LENGTH_SHORT).show()
    }

    fun showShort(context: Context, text : String) {
        Toast.makeText(context, text, Toast.LENGTH_SHORT).show()
    }

    suspend fun showCoroutineToast(context : Context, message : String) = withContext(Dispatchers.Main) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }

    @SuppressLint("WrongThread")
    @AnyThread
    fun show(context: Context, text: String) {
        if (Looper.getMainLooper() == Looper.myLooper()) {
            showInMain(context, text)
        } else {
            handler.post { showInMain(context, text) }
        }
    }

    @MainThread
    @Synchronized
    fun showInMain(context: Context, text: String) {
        toast?.get()?.cancel()
        toast = null
        Toast.makeText(context, text, Toast.LENGTH_SHORT).also {
            toast = WeakReference(it)
        }.show()
    }
}