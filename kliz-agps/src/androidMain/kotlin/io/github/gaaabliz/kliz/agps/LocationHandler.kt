package io.github.gaaabliz.kliz.agps

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationManager
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.model.LatLng
import io.github.gaaabliz.kliz.android.util.LogUtils

class LocationHandler(private val logger : LogUtils.Logger) {

    lateinit var fusedLocationClient: FusedLocationProviderClient

    fun initLocation(activity : Activity) {
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(activity)
    }

    @SuppressLint("MissingPermission")
    fun getLastLocation(context: Context, onLoc : (LatLng) -> Unit) {
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
            && ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            throw IllegalStateException("Location permission not granted)")
        }
        fusedLocationClient.lastLocation
            .addOnSuccessListener { location : Location? ->
                if (location != null) {
                    /*val currentLocation = LatLng.newBuilder()
                        .setLatitude(location.latitude.toDouble())
                        .setLongitude(location.longitude.toDouble())
                        .build()*/
                    val currentLocation = LatLng(location.latitude, location.longitude)
                    onLoc(currentLocation)
                } else {
                    throw IllegalStateException("Location is null")
                }
            }
            .addOnFailureListener{
                throw IllegalStateException("Error getting location" + it.message)
            }
    }

    /**
     * Metodo che controlla se nel telefono sono attivi i servizi di localizzazione e di GPS.
     * @param activity L'activity chiamante.
     * @return Boolean <tt>TRUE</tt> se sono attivi, <tt>FALSE</tt> altrimenti.
     */
    fun checkGpsSetting(context: Context, activity: Activity):Boolean {
        val locationManager = activity.getSystemService(AppCompatActivity.LOCATION_SERVICE) as LocationManager
        return if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            logger.debug("Location services are enabled.")
            true
        } else {
            logger.warn("Location services are disabled.")
            false
        }
    }

}