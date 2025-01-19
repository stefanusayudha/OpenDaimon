package com.singularityindonesia.opendaimon.sys.lib.sensor

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.staticCompositionLocalOf
import com.singularityindonesia.opendaimon.sys.daimon.lib.Sensors

val LocalSensors = staticCompositionLocalOf<Sensors> { error("No sensor provided") }

@Composable
fun ProvideSensors(sensors: Sensors, content: @Composable () -> Unit) {
    CompositionLocalProvider(LocalSensors provides sensors) {
        content()
    }
}