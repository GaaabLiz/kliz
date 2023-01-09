@file:Suppress("DEPRECATION")
package it.gabliz.kliz.android.util


import android.Manifest
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import androidx.annotation.RequiresPermission


/**
 * Funzioni di utilità per quanto riguarda internet e la connettività.
 */
object NetworkUtils {

    /**
     * Metodo che controlla se sul device è presente una connessione a internet.
     * @param context Il contesto corrente
     * @return Boolean TRUE se è presente connessione; FALSE altrimenti.
     */
    @RequiresPermission(Manifest.permission.ACCESS_NETWORK_STATE)
    fun checkInternetConnection(context: Context):Boolean {
        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork: NetworkInfo? = cm.activeNetworkInfo
        return activeNetwork?.isConnectedOrConnecting == true
    }


}