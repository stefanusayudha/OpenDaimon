package com.singularityindonesia.sensor

import android.content.Context
import android.hardware.SensorManager
import com.singularityindonesia.sensor.sensors.Accelerometer
import com.singularityindonesia.sensor.sensors.AmbienceTemperatureSensor
import com.singularityindonesia.sensor.sensors.GeoMagnetRotationSensor
import com.singularityindonesia.sensor.sensors.GravitySensor
import com.singularityindonesia.sensor.sensors.GyroscopeSensor
import com.singularityindonesia.sensor.sensors.HumiditySensor
import com.singularityindonesia.sensor.sensors.LightSensor
import com.singularityindonesia.sensor.sensors.LinearAccelerationSensor
import com.singularityindonesia.sensor.sensors.PressureSensor
import com.singularityindonesia.sensor.sensors.ProximitySensor
import com.singularityindonesia.sensor.sensors.RotationSensor
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.plus

// todo: turncoroutineScope +  on sensor on demand
class Sensors(
    coroutineScope: CoroutineScope,
    sensorManager: SensorManager?
) {

    constructor(context: Context, coroutineScope: CoroutineScope) : this(coroutineScope, context.getSystemService(Context.SENSOR_SERVICE) as SensorManager)

    // todo: adjust the delay on demand
    private val accelerometerSuperVisor = SupervisorJob()
    val accelerometer = Accelerometer(
        coroutineScope + accelerometerSuperVisor,
        sensorManager,
        SensorManager.SENSOR_DELAY_NORMAL
    )

    // todo: adjust the delay on demand
    private val gravitySuperVisor = SupervisorJob()
    val gravity = GravitySensor(
        coroutineScope + gravitySuperVisor,
        sensorManager,
        SensorManager.SENSOR_DELAY_NORMAL
    )

    // todo: adjust the delay on demand
    private val linearAccelerationSuperVisor = SupervisorJob()
    val linearAcceleration = LinearAccelerationSensor(
        coroutineScope + linearAccelerationSuperVisor,
        sensorManager,
        SensorManager.SENSOR_DELAY_NORMAL
    )

    // todo: adjust the delay on demand
    private val gyroscopeSuperVisor = SupervisorJob()
    val gyroscope = GyroscopeSensor(
        coroutineScope + gyroscopeSuperVisor,
        sensorManager,
        SensorManager.SENSOR_DELAY_NORMAL
    )

    // todo: adjust the delay on demand
    private val rotationSuperVisor = SupervisorJob()
    val rotation = RotationSensor(
        coroutineScope + rotationSuperVisor,
        sensorManager,
        SensorManager.SENSOR_DELAY_NORMAL
    )

    // todo: adjust the delay on demand
    private val geoRotationSuperVisor = SupervisorJob()
    val geoRotation = GeoMagnetRotationSensor(
        coroutineScope + geoRotationSuperVisor,
        sensorManager,
        SensorManager.SENSOR_DELAY_NORMAL
    )

    // todo: adjust the delay on demand
    private val proximitySuperVisor = SupervisorJob()
    val proximity = ProximitySensor(
        coroutineScope + proximitySuperVisor,
        sensorManager,
        SensorManager.SENSOR_DELAY_NORMAL
    )

    // todo: adjust the delay on demand
    private val lightSuperVisor = SupervisorJob()
    val light = LightSensor(
        coroutineScope + lightSuperVisor,
        sensorManager,
        SensorManager.SENSOR_DELAY_NORMAL
    )

    // todo: adjust the delay on demand
    private val ambienceTempSuperVisor = SupervisorJob()
    val ambienceTemp = AmbienceTemperatureSensor(
        coroutineScope + ambienceTempSuperVisor,
        sensorManager,
        SensorManager.SENSOR_DELAY_NORMAL
    )

    // todo: adjust the delay on demand
    private val pressureSuperVisor = SupervisorJob()
    val pressure = PressureSensor(
        coroutineScope + pressureSuperVisor,
        sensorManager,
        SensorManager.SENSOR_DELAY_NORMAL
    )

    // todo: adjust the delay on demand
    private val humiditySuperVisor = SupervisorJob()
    val humidity = HumiditySensor(
        coroutineScope + humiditySuperVisor,
        sensorManager,
        SensorManager.SENSOR_DELAY_NORMAL
    )

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
