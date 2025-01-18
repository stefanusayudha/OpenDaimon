package com.singularityindonesia.opendaimon.lib.sensor

import android.hardware.SensorManager
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.staticCompositionLocalOf

// todo: turn on sensor on demand
class Sensors(
    sensorManager: SensorManager?
) {

    // todo: adjust the delay on demand
    val accelerometer = Accelerometer(sensorManager, SensorManager.SENSOR_DELAY_NORMAL)

    // todo: adjust the delay on demand
    val gravity = GravitySensor(sensorManager, SensorManager.SENSOR_DELAY_NORMAL)

    // todo: adjust the delay on demand
    val linearAcceleration = LinearAccelerationSensor(sensorManager, SensorManager.SENSOR_DELAY_NORMAL)

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

}

// region Compose Provider
val LocalSensors = staticCompositionLocalOf<Sensors> { error("No sensor provided") }

@Composable
fun ProvideSensors(sensors: Sensors, content: @Composable () -> Unit) {
    CompositionLocalProvider(LocalSensors provides sensors) {
        content()
    }
}
// endregion