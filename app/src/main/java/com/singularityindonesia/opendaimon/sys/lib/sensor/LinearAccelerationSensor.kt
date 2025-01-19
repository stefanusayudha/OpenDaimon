package com.singularityindonesia.opendaimon.sys.lib.sensor

import android.hardware.Sensor
import android.hardware.SensorManager

// todo: turn on sensor on demand
class LinearAccelerationSensor(
    sensorManager: SensorManager?,
    sensorDelay: Int
) : Sensor3D by sensor3d(
    sensorManager = sensorManager,
    sensorDelay = sensorDelay,
    sensorType = Sensor.TYPE_LINEAR_ACCELERATION,
    uncalibratedSensorType = -1
)