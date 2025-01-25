package com.singularityindonesia.opendaimon

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.singularityindonesia.opendaimon.lib.theme.OpenDaimonTheme
import com.singularityindonesia.opendaimon.sys.ProvideDaimon
import com.singularityindonesia.opendaimon.sys.ProvideSensor
import com.singularityindonesia.opendaimon.sys.ProvideSerialMonitor
import com.singularityindonesia.opendaimon.tmp.daimon.Daimon
import com.singularityindonesia.sensor.Sensors
import com.singularityindonesia.serial.SerialHost

class MainActivity : ComponentActivity() {

    private lateinit var serialHost: SerialHost
    private lateinit var sensors: Sensors
    private lateinit var daimon: Daimon

    override fun attachBaseContext(newBase: Context?) {
        super.attachBaseContext(newBase)
        initiateSensors()
        initiateSerialHost()
        initiateDaimon()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()
        setContent {
            OpenDaimonTheme {
                ProvideSensor(sensors) {
                    ProvideSerialMonitor(serialHost.monitor) {
                        ProvideDaimon(daimon) {
                            MainPlot()
                        }
                    }
                }
            }
        }
    }

    override fun onResume() {
        super.onResume()
        sensors.start()
    }

    override fun onPause() {
        super.onPause()
        sensors.stop()
    }

    private fun initiateDaimon() {
        daimon = Daimon()
    }

    private fun initiateSerialHost() {
        serialHost = SerialHost(this)
    }

    private fun initiateSensors() {
        sensors = Sensors(this)
    }
}