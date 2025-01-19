package com.singularityindonesia.opendaimon

import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.singularityindonesia.opendaimon.sys.daimon.lib.ProvideSensors
import com.singularityindonesia.opendaimon.sys.daimon.lib.Sensors
import com.singularityindonesia.opendaimon.shell.lib.theme.OpenDaimonTheme
import com.singularityindonesia.opendaimon.sys.daimon.lib.Microphone
import com.singularityindonesia.opendaimon.sys.daimon.lib.ProvideMicrophone
import com.singularityindonesia.opendaimon.sys.daimon.lib.ProvideSerialBus
import com.singularityindonesia.opendaimon.sys.daimon.lib.SerialBus

class MainActivity : ComponentActivity() {

    private lateinit var sensors: Sensors
    private lateinit var serialBus: SerialBus
    private lateinit var microphone: Microphone

    override fun attachBaseContext(newBase: Context?) {
        super.attachBaseContext(newBase)

        initiateMicrophone()
        initiateSensors()
        initiateSerialBus()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()
        setContent {
            OpenDaimonTheme {
                // fixme: all this should be provided by the daimon
                ProvideSerialBus(serialBus) {
                    ProvideSensors(sensors) {
                        ProvideMicrophone(microphone) {
                            MainPlot()
                        }
                    }
                }
            }
        }
    }

    override fun onResume() {
        super.onResume()
        // todo: for now, the daemon will only be active on resume
        startDaemon()
    }

    override fun onPause() {
        super.onPause()
        // todo: for now, the daemon will only be active on resume
        stopDaemon()
    }

    private fun startDaemon() {
        // fixme: for now we will not use daemon directly
        runCatching {
            microphone.listen()
            //serialBus.start()
            sensors.start()
        }.onFailure {
            Toast.makeText(this, "Daemon Start Failed", Toast.LENGTH_SHORT).show()
        }.onSuccess {
            Toast.makeText(this, "Daemon Started Successfully", Toast.LENGTH_SHORT).show()
        }
    }

    private fun stopDaemon() {
        microphone.stop()
        //serialBus.stop()
        sensors.stop()
    }

    private fun initiateMicrophone() {
        this.microphone = Microphone(this)
    }

    private fun initiateSensors() {
        this.sensors = Sensors(this)
    }

    private fun initiateSerialBus() {
        this.serialBus = SerialBus(this)
    }
}