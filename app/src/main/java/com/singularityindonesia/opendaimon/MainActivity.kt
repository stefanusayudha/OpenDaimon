package com.singularityindonesia.opendaimon

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.singularityindonesia.opendaimon.sys.daimon.lib.ProvideSensors
import com.singularityindonesia.opendaimon.sys.daimon.lib.Sensors
import com.singularityindonesia.opendaimon.shell.lib.theme.OpenDaimonTheme
import com.singularityindonesia.opendaimon.sys.daimon.lib.ProvideSerialBus
import com.singularityindonesia.opendaimon.sys.daimon.lib.SerialBus

class MainActivity : ComponentActivity() {

    private lateinit var sensors: Sensors
    private lateinit var serialBus: SerialBus

    override fun attachBaseContext(newBase: Context?) {
        super.attachBaseContext(newBase)

        initiateSensors()
        initiateSerialBus()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()
        setContent {
            OpenDaimonTheme {
                ProvideSerialBus(serialBus) {
                    ProvideSensors(sensors) {
                        MainPlot()
                    }
                }
            }
        }
    }

    private fun initiateSensors() {
        this.sensors = Sensors(this)
    }

    private fun initiateSerialBus() {
        this.serialBus = SerialBus(this)
    }
}