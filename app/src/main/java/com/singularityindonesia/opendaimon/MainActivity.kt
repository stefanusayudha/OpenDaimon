package com.singularityindonesia.opendaimon

import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.singularityindonesia.opendaimon.shell.lib.theme.OpenDaimonTheme
import com.singularityindonesia.opendaimon.sys.daimon.Daimon
import com.singularityindonesia.opendaimon.sys.daimon.ProvideDaimon

class MainActivity : ComponentActivity() {

    private lateinit var daimon: Daimon

    override fun attachBaseContext(newBase: Context?) {
        super.attachBaseContext(newBase)
        initiateDaimon()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()
        setContent {
            OpenDaimonTheme {
                // fixme: all this should be provided by the daimon
                ProvideDaimon(daimon) {
                    MainPlot()
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
            daimon.start()
        }.onFailure {
            Toast.makeText(this, "Daemon Start Failed", Toast.LENGTH_SHORT).show()
        }.onSuccess {
            Toast.makeText(this, "Daemon Started Successfully", Toast.LENGTH_SHORT).show()
        }
    }

    private fun stopDaemon() {
        daimon.stop()
    }

    private fun initiateDaimon() {
        daimon = Daimon(this)
    }
}