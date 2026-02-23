package io.github.gaaabliz.kliz.android.util


import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.provider.Settings
import android.widget.Toast

object IntentUtils {

    fun sendEmailIntent(
        context: Context,
        email: String,
        subject: String = "",
        body: String = ""
    ) {
        val i = Intent(Intent.ACTION_SEND)
        i.type = "message/rfc822"
        i.putExtra(Intent.EXTRA_EMAIL, arrayOf(email))
        i.putExtra(Intent.EXTRA_SUBJECT, subject)
        i.putExtra(Intent.EXTRA_TEXT, body)
        try {
            context.startActivity(Intent.createChooser(i, "Send mail..."))
        } catch (ex: ActivityNotFoundException) {
            Toast.makeText(context, "There are no email clients installed.", Toast.LENGTH_SHORT).show()
        }
    }

    /*
    fun openSystemMapWithMarker(
        context: Context,
        latitude: Double,
        longitude: Double
    ) {
        val geoUri = "geo:$latitude,$longitude?q=$latitude,$longitude(${R.attr.label})"
        val gmmIntentUri = Uri.parse(geoUri)
        val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri)
        mapIntent.setPackage("com.google.android.apps.maps")
        if(mapIntent.resolveActivity(context.packageManager) != null) {
            ContextCompat.startActivity(context, mapIntent, null)
        }
    }*/

    fun openActWithPayload(
        context: Context,
        activityToOpen: Class<*>,
        payload: String
    ) {
        val intent = Intent(context, activityToOpen)
        intent.putExtra("payload", payload)
        context.startActivity(intent)
    }

    fun openUrlIntent(
        context: Context,
        urlString : String
    ) {
        val i = Intent(Intent.ACTION_VIEW)
        i.data = Uri.parse(urlString)
        context.startActivity(i)
    }

    fun openPermissionSetting(
        context: Context
    ) = run {
        val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
        val uri = Uri.fromParts("package", context.packageName, null)
        intent.data = uri
        context.startActivity(intent)
    }
}