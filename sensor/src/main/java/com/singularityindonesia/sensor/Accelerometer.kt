package com.singularityindonesia.sensor

import android.hardware.Sensor
import android.hardware.SensorManager
import android.os.Build
import androidx.annotation.RequiresApi

// todo: turn on sensor on demand
@RequiresApi(Build.VERSION_CODES.O)
class Accelerometer(
    sensorManager: SensorManager?,
    sensorDelay: Int
) : Sensor3D by sensor3d(
    sensorManager = sensorManager,
    sensorDelay = sensorDelay,
    sensorType = Sensor.TYPE_ACCELEROMETER,
    uncalibratedSensorType = Sensor.TYPE_ACCELEROMETER_UNCALIBRATED
)