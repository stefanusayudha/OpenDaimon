package com.singularityindonesia.opendaimon.ui.protocol

import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.singularityindonesia.opendaimon.ui.component.HeaderPage

@Composable
fun ProtocolActivity(
    protocolId: String,
    returnBack: () -> Unit
) {
    val controller = rememberNavController()
    val pageTitle = remember(protocolId) {
        "Protocol${
            protocolId.takeIf { it.isNotBlank() }
                ?.let { "/${it.replaceFirstChar { c -> c.uppercaseChar() }}" }
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
            protocolId = protocolId,
            controller = controller,
        )
    }
}