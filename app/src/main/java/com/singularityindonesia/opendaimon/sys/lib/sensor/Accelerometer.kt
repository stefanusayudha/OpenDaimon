package com.singularityindonesia.opendaimon.sys.lib.sensor

import android.hardware.Sensor
import android.hardware.SensorManager

// todo: turn on sensor on demand
class Accelerometer(
    sensorManager: SensorManager?,
    sensorDelay: Int
) : Sensor3D by sensor3d(
    sensorManager = sensorManager,
    sensorDelay = sensorDelay,
    sensorType = Sensor.TYPE_ACCELEROMETER,
    uncalibratedSensorType = Sensor.TYPE_ACCELEROMETER_UNCALIBRATED
)