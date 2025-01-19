package com.singularityindonesia.opendaimon.sys.daimon

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.staticCompositionLocalOf

val LocalDaimon = staticCompositionLocalOf<Daimon> { error("No sensor provided") }

@Composable
fun ProvideDaimon(daimon: Daimon, content: @Composable () -> Unit) {
    CompositionLocalProvider(LocalDaimon provides daimon) {
        content()
    }
}