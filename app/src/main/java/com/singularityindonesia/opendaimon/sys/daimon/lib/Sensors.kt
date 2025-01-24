package com.singularityindonesia.opendaimon.sys.daimon.lib

import android.content.Context
import android.hardware.SensorManager
import com.singularityindonesia.sensor.Accelerometer
import com.singularityindonesia.sensor.AmbienceTemperatureSensor
import com.singularityindonesia.sensor.GeoMagnetRotationSensor
import com.singularityindonesia.sensor.GravitySensor
import com.singularityindonesia.sensor.GyroscopeSensor
import com.singularityindonesia.sensor.HumiditySensor
import com.singularityindonesia.sensor.LightSensor
import com.singularityindonesia.sensor.LinearAccelerationSensor
import com.singularityindonesia.sensor.PressureSensor
import com.singularityindonesia.sensor.ProximitySensor
import com.singularityindonesia.sensor.RotationSensor

// todo: turn on sensor on demand
class Sensors(
    sensorManager: SensorManager?
) {

    constructor(context: Context) : this(context.getSystemService(Context.SENSOR_SERVICE) as SensorManager)

    // todo: adjust the delay on demand
    val accelerometer = Accelerometer(sensorManager, SensorManager.SENSOR_DELAY_NORMAL)

    // todo: adjust the delay on demand
    val gravity = GravitySensor(sensorManager, SensorManager.SENSOR_DELAY_NORMAL)

    // todo: adjust the delay on demand
    val linearAcceleration =
        LinearAccelerationSensor(sensorManager, SensorManager.SENSOR_DELAY_NORMAL)

    // todo: adjust the delay on demand
    val gyroscope = GyroscopeSensor(sensorManager, SensorManager.SENSOR_DELAY_NORMAL)

    // todo: adjust the delay on demand
    val rotation = RotationSensor(sensorManager, SensorManager.SENSOR_DELAY_NORMAL)

    // todo: adjust the delay on demand
    val geoRotation = GeoMagnetRotationSensor(sensorManager, SensorManager.SENSOR_DELAY_NORMAL)

    // todo: adjust the delay on demand
    val proximity = ProximitySensor(sensorManager, SensorManager.SENSOR_DELAY_NORMAL)

    // todo: adjust the delay on demand
    val light = LightSensor(sensorManager, SensorManager.SENSOR_DELAY_NORMAL)

    // todo: adjust the delay on demand
    val ambienceTemp = AmbienceTemperatureSensor(sensorManager, SensorManager.SENSOR_DELAY_NORMAL)

    // todo: adjust the delay on demand
    val pressure = PressureSensor(sensorManager, SensorManager.SENSOR_DELAY_NORMAL)

    // todo: adjust the delay on demand
    val humidity = HumiditySensor(sensorManager, SensorManager.SENSOR_DELAY_NORMAL)

    fun start() {
        startAll()
    }

    fun startAll() {
        accelerometer.start()
        gravity.start()
        linearAcceleration.start()
        gyroscope.start()
        rotation.start()
        geoRotation.start()
        proximity.start()
        light.start()
        ambienceTemp.start()
        pressure.start()
        humidity.start()
    }

    fun stop() {
        stopAll()
    }

    fun stopAll() {
        accelerometer.stop()
        gravity.stop()
        linearAcceleration.stop()
        gyroscope.stop()
        rotation.stop()
        geoRotation.stop()
        proximity.stop()
        light.stop()
        ambienceTemp.stop()
        pressure.stop()
        humidity.stop()
    }

}
