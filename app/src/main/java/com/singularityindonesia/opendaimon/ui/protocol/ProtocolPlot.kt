package com.singularityindonesia.opendaimon.ui.protocol

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun ProtocolPlot(
    modifier: Modifier = Modifier,
    protocolId: String,
    controller: NavHostController
) {
    NavHost(
        modifier = modifier,
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