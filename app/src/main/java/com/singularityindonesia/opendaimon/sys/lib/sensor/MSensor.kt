package com.singularityindonesia.opendaimon.sys.lib.sensor

import android.hardware.Sensor
import android.hardware.SensorManager
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

interface MSensor {
    val sensorManager: SensorManager?

    val sensor: Sensor?
    val sensorType: Int

    val uncalibratedSensor: Sensor?
    val uncalibratedSensorType: Int

    fun exist(): Boolean {
        return sensor != null || uncalibratedSensor != null
    }

    @Composable
    fun Display(modifier: Modifier, alignment: Alignment.Horizontal)

    @Composable
    fun Calibrated(modifier: Modifier)

    @Composable
    fun Uncalibrated(modifier: Modifier)
}
