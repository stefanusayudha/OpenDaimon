package com.singularityindonesia.opendaimon.lib.sensor

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

interface Sensor1D : SensorEventListener {
    val sensorType: Int
    val uncalibratedSensorType: Int

    val state: MutableStateFlow<Float>
    val accuracy: MutableStateFlow<Int>

    val uncalibrated: MutableStateFlow<Float>
    val uncalibratedAccuracy: MutableStateFlow<Int>

    @Composable
    fun Display(modifier: Modifier, alignment: Alignment.Horizontal) {
        Column(
            horizontalAlignment = alignment
        ) {
            Calibrated(modifier = Modifier)
            Uncalibrated(modifier = Modifier)
        }
    }

    @Composable
    fun Calibrated(modifier: Modifier) {
        val currentState = this.state.collectAsStateWithLifecycle()
        val display = currentState.value.toString().take(4)

        Text(modifier = modifier, text = display)
    }

    @Composable
    fun Uncalibrated(modifier: Modifier) {
        val currentState = this.uncalibrated.collectAsStateWithLifecycle()
        val display = currentState.value.toString().take(4)

        Text(modifier = modifier, text = display)
    }

    fun stop()
}

fun sensor1d(
    sensorManager: SensorManager?,
    sensorDelay: Int,
    sensorType: Int,
    uncalibratedSensorType: Int
): Sensor1D {
    return object : Sensor1D {
        override val sensorType: Int = sensorType
        override val uncalibratedSensorType: Int = uncalibratedSensorType

        private val sensor = sensorManager?.getDefaultSensor(sensorType)
        private val uncalibratedSensor = sensorManager?.getDefaultSensor(uncalibratedSensorType)


        override val state = MutableStateFlow(0f)
        override val accuracy = MutableStateFlow(0)

        override val uncalibrated = MutableStateFlow(0f)
        override val uncalibratedAccuracy = MutableStateFlow(0)

        override fun onSensorChanged(event: SensorEvent?) {
            event?.let {
                when (it.sensor.type) {
                    sensorType -> {
                        state.value = it.values[0]
                    }

                    uncalibratedSensorType -> {
                        uncalibrated.value = it.values[0]
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

        override fun stop() {
            sensorManager?.unregisterListener(this)
        }

        init {
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
    }
}