package com.singularityindonesia.opendaimon.shell.protocol

import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.singularityindonesia.opendaimon.shell.lib.component.HeaderPage

@Composable
fun ProtocolActivity(
    path: String,
    returnBack: () -> Unit
) {
    val controller = rememberNavController()
    val pageTitle = remember(path) {
        "Protocol${
            path.takeIf { it.isNotBlank() }
                ?.let { "/${it.replaceFirstChar { c -> c.uppercaseChar() }}" }.orEmpty()
        }"
    }

    Scaffold(
        modifier = Modifier
            .statusBarsPadding()
            .navigationBarsPadding()
            .systemBarsPadding(),
        topBar = {
            HeaderPage(
                backAction = {
                    if (!controller.popBackStack()) returnBack.invoke()
                },
                titleText = pageTitle
            )
        }
    ) { padding ->
        ProtocolPlot(
            modifier = Modifier.padding(padding),
            path = path,
            controller = controller,
        )
    }
}

@Preview
@Composable
private fun Preview() {
    ProtocolActivity(
        path = "",
        returnBack = {}
    )
}