package com.singularityindonesia.opendaimon.shell.splash

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.singularityindonesia.opendaimon.shell.splash.scene.PreloadScene
import kotlinx.coroutines.delay

@Composable
fun SplashActivity(
    onFinishPreparation: () -> Unit
) {
    Scaffold { padding ->
        PreloadScene(
            modifier = Modifier.padding(padding)
        )
    }

    LaunchedEffect(Unit) {
        delay(1000)
        onFinishPreparation.invoke()
    }
}

@Preview
@Composable
private fun Preview() {
    SplashActivity(
        onFinishPreparation = {}
    )
}