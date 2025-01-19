package com.singularityindonesia.opendaimon

import android.content.Context
import android.hardware.SensorManager
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.singularityindonesia.opendaimon.sys.lib.sensor.ProvideSensors
import com.singularityindonesia.opendaimon.sys.daimon.lib.Sensors
import com.singularityindonesia.opendaimon.shell.lib.theme.OpenDaimonTheme

class MainActivity : ComponentActivity() {

    private lateinit var sensors: Sensors

    override fun attachBaseContext(newBase: Context?) {
        super.attachBaseContext(newBase)

        initiateSensors()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()
        setContent {
            OpenDaimonTheme {
                ProvideSensors(sensors) {
                    MainPlot()
                }
            }
        }
    }

    private fun initiateSensors() {
        val sensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager
        this.sensors = Sensors(sensorManager)
    }
}