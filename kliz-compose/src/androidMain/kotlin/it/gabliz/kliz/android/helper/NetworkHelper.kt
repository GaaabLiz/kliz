package it.gabliz.kliz.android.helper

import android.content.Context
import it.gabliz.kliz.android.util.LogUtils
import it.gabliz.kliz.android.util.NetworkUtility

/**
 * Metodo per controllare se la connessione a internet è presente nel telefono.
 * Se la connessione non ce, ogni tot secondi ne viene ri-controllata la presenza.
 * Il delay tra un tentativo e un altro e il numero di tentativi sono specificati
 * nel file di configurazione dell'app (config.xml).
 */
fun checkAndRepeatConnections(
    context: Context,
    delayEachConnection : Long,
    maxConnectionTry : Int
): ConnectionStatus {
    var status: ConnectionStatus = ConnectionStatus.NOT_CONNECTED
    var counterConnectionTry = 0

    /* effettuo tentativi */
    for (i in 1..maxConnectionTry) {

        /* controllo connessione */
        if(NetworkUtility.checkInternetConnection(context)) {
            LogUtils.addInfoLog("Connection found!")
            status = ConnectionStatus.CONNECTED
            break
        } else {
            counterConnectionTry++
            LogUtils.addWarnLog("Attempt n°$counterConnectionTry of failed connection attempts.")
            status = ConnectionStatus.NOT_CONNECTED
        }

        /* setto ritardo */
        try {
            Thread.sleep(delayEachConnection)
        }catch (e:InterruptedException) {
            LogUtils.addErrorLog("Interrupt error on thread.")
        }

    }
    return status
}

/** Dichiarazione dei tre tipi di stati di una connessione */
enum class ConnectionStatus { CONNECTED, NOT_CONNECTED }