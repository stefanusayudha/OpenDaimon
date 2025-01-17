package com.singularityindonesia.opendaimon.ui.protocol

import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.singularityindonesia.opendaimon.ui.component.HeaderPage

@Composable
fun ProtocolActivity(
    protocolId: String,
    returnBack: () -> Unit
) {
    val controller = rememberNavController()
    Scaffold(
        modifier = Modifier
            .statusBarsPadding()
            .navigationBarsPadding()
            .systemBarsPadding(),
        topBar = {
            HeaderPage(
                backAction = {
                    controller.popBackStack().takeIf { it } ?: returnBack.invoke()
                },
                titleText = "Protocol${
                    protocolId.takeIf { it.isNotBlank() }?.let { "/${it.replaceFirstChar { c -> c.uppercaseChar() }}" }
                }"
            )
        }
    ) { padding ->
        NavHost(
            modifier = Modifier.padding(padding),
            navController = controller,
            startDestination = protocolId.takeIf { it.isNotBlank() } ?: "home"
        ) {

            composable(
                route = "home"
            ) {
                Text("Protocol Home Page")
            }

            composable(
                route = "scan"
            ) {
                ScanProtocolPane()
            }
        }
    }
}