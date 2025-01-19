package com.singularityindonesia.opendaimon.sys.daimon.lib

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.staticCompositionLocalOf

val LocalMicrophone = staticCompositionLocalOf<Microphone> { error("No sensor provided") }

@Composable
fun ProvideMicrophone(microphone: Microphone, content: @Composable () -> Unit) {
    CompositionLocalProvider(LocalMicrophone provides microphone) {
        content()
    }
}