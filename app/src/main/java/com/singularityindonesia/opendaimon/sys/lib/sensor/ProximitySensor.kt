package com.singularityindonesia.opendaimon.sys.lib.sensor

import android.hardware.Sensor
import android.hardware.SensorManager

// todo: turn on sensor on demand
class ProximitySensor(
    sensorManager: SensorManager?,
    sensorDelay: Int
) : Sensor1D by sensor1d(
    sensorManager = sensorManager,
    sensorDelay = sensorDelay,
    sensorType = Sensor.TYPE_PROXIMITY,
    uncalibratedSensorType = -1
)