package com.singularityindonesia.opendaimon.lib.sensor

import android.hardware.Sensor
import android.hardware.SensorManager

// todo: turn on sensor on demand
class GravitySensor(
    sensorManager: SensorManager?,
    sensorDelay: Int
)  : Sensor3D by sensor3d(
    sensorManager = sensorManager,
    sensorDelay = sensorDelay,
    sensorType = Sensor.TYPE_GRAVITY,
    uncalibratedSensorType = -1
)