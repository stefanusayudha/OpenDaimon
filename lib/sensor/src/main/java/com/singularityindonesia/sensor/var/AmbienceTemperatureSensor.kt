package com.singularityindonesia.sensor.`var`

import android.hardware.Sensor
import android.hardware.SensorManager
import com.singularityindonesia.sensor.Sensor1D
import com.singularityindonesia.sensor.sensor1d

// todo: turn on sensor on demand
class AmbienceTemperatureSensor(
    sensorManager: SensorManager?,
    sensorDelay: Int
) : Sensor1D by sensor1d(
    sensorManager = sensorManager,
    sensorDelay = sensorDelay,
    sensorType = Sensor.TYPE_AMBIENT_TEMPERATURE,
    uncalibratedSensorType = -1
)