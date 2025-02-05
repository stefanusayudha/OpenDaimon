package com.singularityindonesia.opendaimon.shell.pane

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.singularityindonesia.opendaimon.lib.sys.LocalSerialMonitor

@Composable
fun SerialMonitorPane(
    modifier: Modifier = Modifier.fillMaxSize(),
) {
    val serialBus = LocalSerialMonitor.current

    serialBus.SimpleMonitor(modifier)
}