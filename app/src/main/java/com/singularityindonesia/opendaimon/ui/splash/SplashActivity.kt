package com.singularityindonesia.opendaimon.ui.splash

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import kotlinx.coroutines.delay

@Composable
fun SplashActivity(
    onFinishPreparation: () -> Unit
) {
    Scaffold { padding ->
        PreloadPane(
            modifier = Modifier.padding(padding)
        )
    }

    LaunchedEffect(Unit) {
        delay(1000)
        onFinishPreparation.invoke()
    }
}

