package io.github.gaaabliz.kliz.android.base

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import io.github.gaaabliz.kliz.android.util.LogUtils.addVerboseLog

abstract class BaseCompactActivity(private val appLogTag : String = "") : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        addVerboseLog(appLogTag, "onCreate of ${this::class.java.simpleName} called!")
        setContentView(getContentView())
        onViewReady(savedInstanceState, intent)
        execFindIds()
        execListeners()
        execStartupCode()
    }

    override fun onStart() {
        super.onStart()
        addVerboseLog(appLogTag, "onStart of ${this::class.java.simpleName} called!")
    }

    override fun onRestart() {
        super.onRestart()
        addVerboseLog(appLogTag,"onRestart of ${this::class.java.simpleName} called!")
    }

    override fun onResume() {
        super.onResume()
        addVerboseLog(appLogTag,"onResume of ${this::class.java.simpleName} called!")
    }

    override fun onPause() {
        super.onPause()
        addVerboseLog(appLogTag,"onPause of ${this::class.java.simpleName} called!")
    }

    override fun onStop() {
        super.onStop()
        addVerboseLog(appLogTag,"onStop of ${this::class.java.simpleName} called!")
    }

    override fun onDestroy() {
        super.onDestroy()
        addVerboseLog(appLogTag,"onDestroy of ${this::class.java.simpleName} called!")
    }

    protected open fun getContentView(): Int { return 0 }
    protected open fun onViewReady(savedInstanceState: Bundle?, intent: Intent?) {}
    protected open fun execFindIds() {}
    protected open fun execListeners() {}
    protected open fun execStartupCode() {}


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

    /**
     * Metodo per mostrare un toast lungo
     * @param text String testo da scrivere
     */
    protected fun showLongToast(text:String) {
        Toast.makeText(this, text, Toast.LENGTH_LONG).show()
    }

    /**
     * Metodo per mostrare un toast corto
     * @param text String testo da scrivere
     */
    protected fun showShortToast(text:String) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
    }

    protected fun handleKeyboardClose() {
        val inputManager: InputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputManager.hideSoftInputFromWindow(currentFocus?.windowToken, 0)
    }

    protected fun getResString(resID:Int):String {
        return resources.getString(resID)
    }
}