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
import com.singularityindonesia.opendaimon.lib.sys.ProvideCNS
import com.singularityindonesia.opendaimon.lib.sys.ProvideDaimon
import com.singularityindonesia.opendaimon.lib.sys.ProvideSensor
import com.singularityindonesia.opendaimon.lib.sys.ProvideSerialMonitor
import com.singularityindonesia.sensor.Sensors
import com.singularityindonesia.serial.SerialHost
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import java.io.File

class MainActivity : ComponentActivity() {
    private lateinit var serialHost: SerialHost

    private val sensorsScope = CoroutineScope(Dispatchers.IO + SupervisorJob())
    private lateinit var sensors: Sensors

    private val cnsScope = CoroutineScope(Dispatchers.IO + SupervisorJob())
    private lateinit var cns: CNS

    private lateinit var daimon: Daimon

    private val modelStorageScope = CoroutineScope(Dispatchers.IO + SupervisorJob())
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
        sensors = Sensors(this, coroutineScope = sensorsScope)
    }

    private fun initiateModelStorage() {
        val sourceFile = File(getExternalFilesDir(null), "model.txt")
        modelStorage = ModelStorage(coroutineScope = modelStorageScope, sourceFile)
    }

    private fun initiateCns() {
        cns = CNS(coroutineScope = cnsScope, modelStorage = modelStorage)
    }
}