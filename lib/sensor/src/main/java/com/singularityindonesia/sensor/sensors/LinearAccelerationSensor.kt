package com.singularityindonesia.sensor.sensors

import android.hardware.Sensor
import android.hardware.SensorManager
import com.singularityindonesia.sensor.Sensor3D
import com.singularityindonesia.sensor.sensor3d
import kotlinx.coroutines.CoroutineScope

// todo: turn on sensor on demand
class LinearAccelerationSensor(
    coroutineScope: CoroutineScope,
    sensorManager: SensorManager?,
    sensorDelay: Int
) : Sensor3D by sensor3d(
    sensorManager = sensorManager,
    sensorDelay = sensorDelay,
    sensorType = Sensor.TYPE_LINEAR_ACCELERATION,
    uncalibratedSensorType = -1,
    coroutineScope = coroutineScope
)