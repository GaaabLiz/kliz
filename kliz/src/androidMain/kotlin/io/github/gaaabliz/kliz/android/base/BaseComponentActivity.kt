package io.github.gaaabliz.kliz.android.base

import android.os.Bundle
import android.os.PersistableBundle
import android.view.inputmethod.InputMethodManager
import androidx.activity.ComponentActivity
import io.github.gaaabliz.kliz.android.util.LogUtils
import io.github.gaaabliz.kliz.android.util.ToastUtils

abstract class LizBaseComponentActivity(private val appLogTag : String) : ComponentActivity() {

    val logger : LogUtils.Logger = LogUtils.Logger(appLogTag)

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        logger.verbose("onCreate of ${this::class.java.simpleName} called!")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        logger.verbose("onCreate of ${this::class.java.simpleName} called!")
    }

    override fun onStart() {
        super.onStart()
        logger.verbose("onStart of ${this::class.java.simpleName} called!")
    }

    override fun onRestart() {
        super.onRestart()
        logger.verbose("onRestart of ${this::class.java.simpleName} called!")
    }

    override fun onResume() {
        super.onResume()
        logger.verbose("onResume of ${this::class.java.simpleName} called!")
    }

    override fun onPause() {
        super.onPause()
        logger.verbose("onPause of ${this::class.java.simpleName} called!")
    }

    override fun onStop() {
        super.onStop()
        logger.verbose("onStop of ${this::class.java.simpleName} called!")
    }

    override fun onDestroy() {
        super.onDestroy()
        logger.verbose("onDestroy of ${this::class.java.simpleName} called!")
    }

    /*protected open fun applyTransition(transitionType: TransitionType) {
        when(transitionType) {
            TransitionType.SLIDE_OPEN -> {
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
            }
            TransitionType.DEFAULT -> {}
            TransitionType.SLIDE_CLOSE -> {
                overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right)
            }
        }
    }*/

    protected fun showLongToast(text:String) { ToastUtils.showLong(this, text) }

    protected fun showShortToast(text:String) { ToastUtils.showShort(this, text) }

    protected fun handleKeyboardClose() {
        val inputManager: InputMethodManager = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
        inputManager.hideSoftInputFromWindow(currentFocus?.windowToken, 0)
    }
}