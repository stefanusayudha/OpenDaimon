package com.singularityindonesia.sensor

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
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.WhileSubscribed
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.stateIn

interface Sensor1D : MSensor, SensorEventListener {
    override val sensorManager: SensorManager?

    override val sensor: Sensor?
    override val sensorType: Int

    override val uncalibratedSensor: Sensor?
    override val uncalibratedSensorType: Int

    val state: StateFlow<Float>
    val accuracy: StateFlow<Int>

    val uncalibrated: StateFlow<Float>
    val uncalibratedAccuracy: StateFlow<Int>

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
        val display = currentState.value.toString().take(4)

        Text(modifier = modifier, text = display)
    }

    @Composable
    override fun Uncalibrated(modifier: Modifier) {
        val currentState = this.uncalibrated.collectAsStateWithLifecycle()
        val display = currentState.value.toString().take(4)

        Text(modifier = modifier, text = display)
    }

    override fun start()
    override fun stop()
}

fun sensor1d(
    coroutineScope: CoroutineScope,
    sensorManager: SensorManager?,
    sensorDelay: Int,
    sensorType: Int,
    uncalibratedSensorType: Int
): Sensor1D {
    return object : Sensor1D {
        override val coroutineScope: CoroutineScope = coroutineScope
        override val sensorManager: SensorManager? = sensorManager

        override val sensor = sensorManager?.getDefaultSensor(sensorType)
        override val sensorType: Int = sensorType

        override val uncalibratedSensorType: Int = uncalibratedSensorType
        override val uncalibratedSensor = sensorManager?.getDefaultSensor(uncalibratedSensorType)


        private val _state = MutableStateFlow(0f)
        override val state =
            _state.stateIn(coroutineScope, SharingStarted.WhileSubscribed(500L), _state.value)
        private val _accuracy = MutableStateFlow(0)
        override val accuracy =
            _accuracy.stateIn(coroutineScope, SharingStarted.WhileSubscribed(500L), _accuracy.value)

        private val _uncalibrated = MutableStateFlow(0f)
        override val uncalibrated = _uncalibrated.asStateFlow()
            .stateIn(coroutineScope, SharingStarted.WhileSubscribed(500L), _uncalibrated.value)
        private val _uncalibratedAccuracy = MutableStateFlow(0)
        override val uncalibratedAccuracy = _uncalibratedAccuracy.asStateFlow()
            .stateIn(coroutineScope, SharingStarted.WhileSubscribed(500L), _uncalibratedAccuracy.value)

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
                        _state.value = it.values[0]
                    }

                    uncalibratedSensorType -> {
                        _uncalibrated.value = it.values[0]
                    }

                    else -> {}
                }
            }
        }

        override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
            when (sensor?.type) {
                sensorType -> {
                    _accuracy.value = accuracy
                }

                uncalibratedSensorType -> {
                    _uncalibratedAccuracy.value = accuracy
                }

                else -> {}
            }
        }
    }
}