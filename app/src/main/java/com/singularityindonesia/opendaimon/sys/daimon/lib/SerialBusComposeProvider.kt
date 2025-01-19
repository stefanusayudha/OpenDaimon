package com.singularityindonesia.opendaimon.sys.daimon.lib

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.staticCompositionLocalOf

val LocalSerialBus = staticCompositionLocalOf<SerialBus> { error("No sensor provided") }

@Composable
fun ProvideSerialBus(serialBus: SerialBus, content: @Composable () -> Unit) {
    CompositionLocalProvider(LocalSerialBus provides serialBus) {
        content()
    }
}