package io.github.gaaabliz.kliz.common.util

import kotlin.test.*

class MeasureUtilsTest {

    @Test
    fun `convertMsecToSec should convert milliseconds to seconds modulo 60`() {
        assertEquals(0, MeasureUtils.convertMsecToSec(0))
        assertEquals(1, MeasureUtils.convertMsecToSec(1000))
        assertEquals(59, MeasureUtils.convertMsecToSec(59000))
        assertEquals(0, MeasureUtils.convertMsecToSec(60000))
        assertEquals(1, MeasureUtils.convertMsecToSec(61000))
        assertEquals(3, MeasureUtils.convertMsecToSec(123000)) // 2 minutes and 3 seconds
    }

    @Test
    fun `convertMillisecToSec should convert milliseconds to total seconds`() {
        assertEquals(0, MeasureUtils.convertMillisecToSec(0))
        assertEquals(1, MeasureUtils.convertMillisecToSec(1000))
        assertEquals(59, MeasureUtils.convertMillisecToSec(59000))
        assertEquals(60, MeasureUtils.convertMillisecToSec(60000))
        assertEquals(61, MeasureUtils.convertMillisecToSec(61000))
        assertEquals(123, MeasureUtils.convertMillisecToSec(123000))
    }

    @Test
    fun `convertMetersToKm should convert meters to kilometers`() {
        assertEquals(0.0, MeasureUtils.convertMetersToKm(0))
        assertEquals(1.0, MeasureUtils.convertMetersToKm(1000))
        assertEquals(0.5, MeasureUtils.convertMetersToKm(500))
        assertEquals(1.234, MeasureUtils.convertMetersToKm(1234.0))
        assertEquals(10.0, MeasureUtils.convertMetersToKm(10000L))
    }

    @Test
    fun `convertKmToMeters should convert kilometers to meters`() {
        assertEquals(0.0, MeasureUtils.convertKmToMeters(0))
        assertEquals(1000.0, MeasureUtils.convertKmToMeters(1))
        assertEquals(500.0, MeasureUtils.convertKmToMeters(0.5))
        assertEquals(1234.0, MeasureUtils.convertKmToMeters(1.234))
        assertEquals(10000.0, MeasureUtils.convertKmToMeters(10L))
    }

    @Test
    fun `isValidTemperatureValue should validate temperature values`() {
        assertTrue(MeasureUtils.isValidTemperatureValue("0"))
        assertTrue(MeasureUtils.isValidTemperatureValue("25"))
        assertTrue(MeasureUtils.isValidTemperatureValue("50"))
        assertFalse(MeasureUtils.isValidTemperatureValue("-1"))
        assertFalse(MeasureUtils.isValidTemperatureValue("51"))
        assertFalse(MeasureUtils.isValidTemperatureValue("abc"))
        assertFalse(MeasureUtils.isValidTemperatureValue("25.5"))
    }

    @Test
    fun `isValidLightValue should validate light values`() {
        assertTrue(MeasureUtils.isValidLightValue("0"))
        assertTrue(MeasureUtils.isValidLightValue("500"))
        assertTrue(MeasureUtils.isValidLightValue("1000"))
        assertFalse(MeasureUtils.isValidLightValue("-1"))
        assertFalse(MeasureUtils.isValidLightValue("1001"))
        assertFalse(MeasureUtils.isValidLightValue("abc"))
        assertFalse(MeasureUtils.isValidLightValue("500.5"))
    }

    @Test
    fun `isValidHumidityValue should validate humidity values`() {
        assertTrue(MeasureUtils.isValidHumidityValue("0"))
        assertTrue(MeasureUtils.isValidHumidityValue("25"))
        assertTrue(MeasureUtils.isValidHumidityValue("50"))
        assertFalse(MeasureUtils.isValidHumidityValue("-1"))
        assertFalse(MeasureUtils.isValidHumidityValue("51"))
        assertFalse(MeasureUtils.isValidHumidityValue("abc"))
        assertFalse(MeasureUtils.isValidHumidityValue("25.5"))
    }

    @Test
    fun `isValidIrrigationValue should validate irrigation values`() {
        assertTrue(MeasureUtils.isValidIrrigationValue("0"))
        assertTrue(MeasureUtils.isValidIrrigationValue("50"))
        assertTrue(MeasureUtils.isValidIrrigationValue("100"))
        assertFalse(MeasureUtils.isValidIrrigationValue("-1"))
        assertFalse(MeasureUtils.isValidIrrigationValue("101"))
        assertFalse(MeasureUtils.isValidIrrigationValue("abc"))
        assertFalse(MeasureUtils.isValidIrrigationValue("50.5"))
    }
}
