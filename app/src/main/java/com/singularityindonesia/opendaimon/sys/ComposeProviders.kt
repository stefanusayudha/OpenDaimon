package com.singularityindonesia.opendaimon.sys

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.staticCompositionLocalOf
import com.singularityindonesia.opendaimon.tmp.daimon.Daimon
import com.singularityindonesia.serial.SerialMonitor

val LocalDaimon = staticCompositionLocalOf<Daimon> { error("No sensor provided") }

@Composable
fun ProvideDaimon(daimon: Daimon, content: @Composable () -> Unit) {
    CompositionLocalProvider(LocalDaimon provides daimon) {
        content()
    }
}

val LocalSerialMonitor = staticCompositionLocalOf<SerialMonitor> { error("No sensor provided") }

@Composable
fun ProvideSerialMonitor(serialMonitor: SerialMonitor, content: @Composable () -> Unit) {
    CompositionLocalProvider(LocalSerialMonitor provides serialMonitor) {
        content()
    }
}