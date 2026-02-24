package io.github.gaaabliz.kliz.agps

import android.content.Context
import android.location.Address
import android.location.Geocoder
import android.location.Location
import com.google.android.gms.location.R
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import io.github.gaaabliz.kliz.common.base.OperationException
import java.io.IOException

object LocationUtils {


    fun calcolateMeterDistance(
        start : LatLng,
        end : LatLng
    ) : Float {
        val startLocation = Location("start")
        startLocation.latitude = start.latitude
        startLocation.longitude = start.longitude
        val endLocation = Location("end")
        endLocation.latitude = end.latitude
        endLocation.longitude = end.longitude
        return startLocation.distanceTo(endLocation)
    }

    fun calcolateMeterDistance(
        longitudeStart : Double,
        latitudeStart : Double,
        longitudeEnd : Double,
        latitudeEnd : Double
    ) : Float {
        val startLocation = Location("start")
        startLocation.latitude = latitudeStart
        startLocation.longitude = longitudeStart
        val endLocation = Location("end")
        endLocation.latitude = latitudeEnd
        endLocation.longitude = longitudeEnd
        return startLocation.distanceTo(endLocation)
    }


    fun genRandomLocation() : LatLng {
        /*return LatLng.newBuilder()
            .setLatitude(45.123456)
            .setLongitude(7.123456)
            .build()*/
        return LatLng(45.123456, 7.123456)
    }

    fun convertCoordinateToStringAddress(
        context: Context,
        coordinate: LatLng
    ):String {
        val place:String
        var geocodeMatches: List<Address>? = null

        try {
            geocodeMatches = Geocoder(context).getFromLocation(coordinate.latitude, coordinate.longitude, 1)
        } catch (e: IOException) {
            throw OperationException(e.message ?: "Unable to convert coordinates to address.")
        }

        if (!geocodeMatches.isNullOrEmpty()) {
            place =
                "${geocodeMatches[0].getAddressLine(0)}, ${geocodeMatches[0].getAddressLine(1)}, ${geocodeMatches[0].adminArea}"
            return place.replace("null,", "").replace("null", "")
        }else {
            throw OperationException("Unable to convert coordinates to address.")
        }
    }

    fun convertAddressToCoordinate(editTextStringAddress:String, context: Context):LatLng? {
        val latitude : Double
        val longitude : Double

        var geocodeMatches: List<Address>? = null

        try {
            geocodeMatches = Geocoder(context).getFromLocationName(editTextStringAddress, 10)
        } catch (e: IOException) {
            e.printStackTrace()
        }

        if (!geocodeMatches.isNullOrEmpty()) {
            latitude = geocodeMatches[0].latitude
            longitude = geocodeMatches[0].longitude
            return LatLng(latitude, longitude)
        }
        return null
    }

    fun getRouteUrl(
        origin: LatLng,
        dest: LatLng,
        directionMode: String,
        context: Context,
        googleMapsApiKey : String
    ): String {
        val strOrigin = "origin=" + origin.latitude.toString() + "," + origin.longitude
        val strDest = "destination=" + dest.latitude.toString() + "," + dest.longitude
        val mode = "mode=$directionMode"
        val parameters = "$strOrigin&$strDest&$mode"
        val output = "json"
        return "https://maps.googleapis.com/maps/api/directions/$output?$parameters&key=$googleMapsApiKey"
    }




}