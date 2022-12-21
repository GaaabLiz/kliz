package it.gabliz.kliz.android.base

import android.os.Bundle
import android.os.PersistableBundle
import android.widget.Toast
import androidx.activity.ComponentActivity

abstract class LizBaseComponentActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
    }

    /**
     * Metodo per fare l'override delle transition di una activity
     * @param transitionType TransitionType il tipo di transizione da applicare
     */
    protected open fun applyTransition(transitionType: TransitionType) {
        when(transitionType) {
            TransitionType.SLIDE_OPEN -> {
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
            }
            TransitionType.DEFAULT -> {
                addLog('w', logSectionAppName,
                    "E' stata usata in ${this.javaClass.simpleName} un apertura di un activity con transizione base.")
            }
            TransitionType.SLIDE_CLOSE -> {
                overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right)
            }
        }
    }

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

}