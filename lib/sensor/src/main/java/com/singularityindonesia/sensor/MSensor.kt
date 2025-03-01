package com.singularityindonesia.sensor

import android.hardware.Sensor
import android.hardware.SensorManager
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import kotlinx.coroutines.CoroutineScope

interface MSensor {
    val coroutineScope: CoroutineScope
    val sensorManager: SensorManager?

    val sensor: Sensor?
    val sensorType: Int

    val uncalibratedSensor: Sensor?
    val uncalibratedSensorType: Int

    fun exist(): Boolean {
        return sensor != null || uncalibratedSensor != null
    }

    fun start()
    fun stop()
    
    @Composable
    fun Display(modifier: Modifier, alignment: Alignment.Horizontal)

    @Composable
    fun Calibrated(modifier: Modifier)

    @Composable
    fun Uncalibrated(modifier: Modifier)
}
