package com.singularityindonesia.opendaimon.ui.activity

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import com.singularityindonesia.opendaimon.ui.pane.AboutPane
import kotlinx.coroutines.delay

@Composable
fun SplashActivity(
    onFinishPreparation: () -> Unit
) {
    Scaffold { padding ->
        AboutPane(
            modifier = Modifier.padding(padding)
        )
    }

    LaunchedEffect(Unit) {
        delay(3000)
        onFinishPreparation.invoke()
    }
}

