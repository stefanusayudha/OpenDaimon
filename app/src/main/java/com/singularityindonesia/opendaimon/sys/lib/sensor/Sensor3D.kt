package com.singularityindonesia.opendaimon.sys.lib.sensor

import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import kotlinx.coroutines.flow.MutableStateFlow

interface Sensor3D : MSensor, SensorEventListener {
    override val sensorManager: SensorManager?

    override val sensor: Sensor?
    override val sensorType: Int

    override val uncalibratedSensor: Sensor?
    override val uncalibratedSensorType: Int

    val state: MutableStateFlow<Triple<Float, Float, Float>>
    val accuracy: MutableStateFlow<Int>

    val uncalibrated: MutableStateFlow<Triple<Float, Float, Float>>
    val uncalibratedAccuracy: MutableStateFlow<Int>

    override fun exist(): Boolean {
        return sensor != null || uncalibratedSensor != null
    }

    @Composable
    override fun Display(modifier: Modifier, alignment: Alignment.Horizontal) {
        Column(
            horizontalAlignment = alignment
        ) {
            Calibrated(modifier = Modifier)
            Uncalibrated(modifier = Modifier)
        }
    }

    @Composable
    override fun Calibrated(modifier: Modifier) {
        val currentState = this.state.collectAsStateWithLifecycle()

        val display = currentState.value.let {
            val x = it.first.toString().take(4)
            val y = it.second.toString().take(4)
            val z = it.third.toString().take(4)
            "$x, $y, $z"
        }

        Text(modifier = modifier, text = display)
    }

    @Composable
    override fun Uncalibrated(modifier: Modifier) {
        val currentState = this.uncalibrated.collectAsStateWithLifecycle()

        val display = currentState.value.let {
            val x = it.first.toString().take(4)
            val y = it.second.toString().take(4)
            val z = it.third.toString().take(4)
            "$x, $y, $z"
        }

        Text(modifier = modifier, text = display)
    }

    override fun start()

    override fun stop()
}

fun sensor3d(
    sensorManager: SensorManager?,
    sensorDelay: Int,
    sensorType: Int,
    uncalibratedSensorType: Int
): Sensor3D {
    return object : Sensor3D {
        override val sensorManager: SensorManager? = sensorManager
        override val sensorType: Int = sensorType
        override val uncalibratedSensorType: Int = uncalibratedSensorType

        override val sensor = sensorManager?.getDefaultSensor(sensorType)
        override val uncalibratedSensor = sensorManager?.getDefaultSensor(uncalibratedSensorType)


        override val state = MutableStateFlow(Triple(0f, 0f, 0f))
        override val accuracy = MutableStateFlow(0)

        override val uncalibrated = MutableStateFlow(Triple(0f, 0f, 0f))
        override val uncalibratedAccuracy = MutableStateFlow(0)

        override fun start() {
            sensorManager?.registerListener(
                this,
                sensor,
                sensorDelay
            )

            sensorManager?.registerListener(
                this,
                uncalibratedSensor,
                sensorDelay
            )
        }

        override fun stop() {
            sensorManager?.unregisterListener(this)
        }

        override fun onSensorChanged(event: SensorEvent?) {
            event?.let {
                when (it.sensor.type) {
                    sensorType -> {
                        val x = it.values[0]
                        val y = it.values[1]
                        val z = it.values[2]
                        state.value = Triple(x, y, z)
                    }

                    uncalibratedSensorType -> {
                        val x = it.values[0]
                        val y = it.values[1]
                        val z = it.values[2]
                        uncalibrated.value = Triple(x, y, z)
                    }

                    else -> {}
                }
            }
        }

        override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
            when (sensor?.type) {
                sensorType -> {
                    this.accuracy.value = accuracy
                }

                uncalibratedSensorType -> {
                    this.uncalibratedAccuracy.value = accuracy
                }

                else -> {}
            }
        }
    }
}