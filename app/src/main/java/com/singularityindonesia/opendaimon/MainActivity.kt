package com.singularityindonesia.opendaimon

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.singularityindonesia.cns.CNS
import com.singularityindonesia.cns.lib.ModelStorage
import com.singularityindonesia.daimon.Daimon
import com.singularityindonesia.opendaimon.lib.theme.OpenDaimonTheme
import com.singularityindonesia.opendaimon.sys.ProvideCNS
import com.singularityindonesia.opendaimon.sys.ProvideDaimon
import com.singularityindonesia.opendaimon.sys.ProvideSensor
import com.singularityindonesia.opendaimon.sys.ProvideSerialMonitor
import com.singularityindonesia.sensor.Sensors
import com.singularityindonesia.serial.SerialHost
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import java.io.File

class MainActivity : ComponentActivity() {

    private val ioScope = CoroutineScope(Dispatchers.IO)
    private lateinit var serialHost: SerialHost
    private lateinit var sensors: Sensors
    private lateinit var cns: CNS
    private lateinit var daimon: Daimon
    private lateinit var modelStorage: ModelStorage

    override fun attachBaseContext(newBase: Context?) {
        super.attachBaseContext(newBase)
        initiateSensors()
        initiateSerialHost()
        initiateModelStorage()
        initiateCns()
        initiateDaimon()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()
        setContent {
            OpenDaimonTheme {
                ProvideSensor(sensors) {
                    ProvideSerialMonitor(serialHost.monitor) {
                        ProvideCNS(cns) {
                            ProvideDaimon(daimon) {
                                MainPlot()
                            }
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

    private fun initiateModelStorage() {
        val sourceFile = File(getExternalFilesDir(null), "model.txt")
        modelStorage = ModelStorage(coroutineScope = ioScope, sourceFile)
    }

    private fun initiateCns() {
        cns = CNS(coroutineScope = ioScope, modelStorage = modelStorage)
    }
}