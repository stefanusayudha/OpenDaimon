package com.singularityindonesia.sensor.sensors

import android.hardware.Sensor
import android.hardware.SensorManager
import com.singularityindonesia.sensor.Sensor1D
import com.singularityindonesia.sensor.sensor1d
import kotlinx.coroutines.CoroutineScope

// todo: turn on sensor on demand
class ProximitySensor(
    coroutineScope: CoroutineScope,
    sensorManager: SensorManager?,
    sensorDelay: Int
) : Sensor1D by sensor1d(
    sensorManager = sensorManager,
    sensorDelay = sensorDelay,
    sensorType = Sensor.TYPE_PROXIMITY,
    uncalibratedSensorType = -1,
    coroutineScope = coroutineScope
)