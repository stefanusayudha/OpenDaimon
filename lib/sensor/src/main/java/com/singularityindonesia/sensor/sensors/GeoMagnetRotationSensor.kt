package com.singularityindonesia.sensor.sensors

import android.hardware.Sensor
import android.hardware.SensorManager
import com.singularityindonesia.sensor.Sensor3D
import com.singularityindonesia.sensor.sensor3d

// todo: turn on sensor on demand
class GeoMagnetRotationSensor(
    sensorManager: SensorManager?,
    sensorDelay: Int
) : Sensor3D by sensor3d(
    sensorManager = sensorManager,
    sensorDelay = sensorDelay,
    sensorType = Sensor.TYPE_GEOMAGNETIC_ROTATION_VECTOR,
    uncalibratedSensorType = -1
)