package com.singularityindonesia.opendaimon.lib.sys

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.staticCompositionLocalOf
import com.singularityindonesia.cns.CNS
import com.singularityindonesia.daimon.Daimon
import com.singularityindonesia.sensor.Sensors
import com.singularityindonesia.serial.SerialMonitor

val LocalDaimon = staticCompositionLocalOf<Daimon> { error("No Daimon provided") }

@Composable
fun ProvideDaimon(daimon: Daimon, content: @Composable () -> Unit) {
    CompositionLocalProvider(LocalDaimon provides daimon) {
        content()
    }
}

val LocalSerialMonitor = staticCompositionLocalOf<SerialMonitor> { error("No SerialMonitor provided") }

@Composable
fun ProvideSerialMonitor(serialMonitor: SerialMonitor, content: @Composable () -> Unit) {
    CompositionLocalProvider(LocalSerialMonitor provides serialMonitor) {
        content()
    }
}

val LocalSensor = staticCompositionLocalOf<Sensors> { error("No Sensor provided") }

@Composable
fun ProvideSensor(sensors: Sensors, content: @Composable () -> Unit) {
    CompositionLocalProvider(LocalSensor provides sensors) {
        content()
    }
}

val LocalCNS = staticCompositionLocalOf<CNS> { error("No CNS provided") }

@Composable
fun ProvideCNS(cns: CNS, content: @Composable () -> Unit) {
    CompositionLocalProvider(LocalCNS provides cns) {
        content()
    }
}