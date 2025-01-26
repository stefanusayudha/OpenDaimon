package com.singularityindonesia.sensor.sensors

import android.hardware.Sensor
import android.hardware.SensorManager
import android.os.Build
import androidx.annotation.RequiresApi
import com.singularityindonesia.sensor.Sensor3D
import com.singularityindonesia.sensor.sensor3d
import kotlinx.coroutines.CoroutineScope

// todo: turn on sensor on demand
@RequiresApi(Build.VERSION_CODES.O)
class Accelerometer(
    coroutineScope: CoroutineScope,
    sensorManager: SensorManager?,
    sensorDelay: Int
) : Sensor3D by sensor3d(
    sensorManager = sensorManager,
    sensorDelay = sensorDelay,
    sensorType = Sensor.TYPE_ACCELEROMETER,
    uncalibratedSensorType = Sensor.TYPE_ACCELEROMETER_UNCALIBRATED,
    coroutineScope = coroutineScope
)