package io.github.gaaabliz.kliz.common.model

/**
 * Represents a geographical point with latitude and longitude coordinates.
 *
 * @property latitude The latitude coordinate, ranging from -90.0 to +90.0.
 * @property longitude The longitude coordinate, ranging from -180.0 to +180.0.
 */
data class LizLatLng(
    var latitude: Double = 0.0,
    var longitude: Double = 0.0
)
