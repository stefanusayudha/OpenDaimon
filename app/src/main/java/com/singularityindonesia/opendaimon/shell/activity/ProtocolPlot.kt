package com.singularityindonesia.opendaimon.shell.activity

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.singularityindonesia.opendaimon.shell.pane.ScanProtocolScene

@Composable
fun ProtocolPlot(
    modifier: Modifier = Modifier,
    path: String,
    controller: NavHostController
) {
    NavHost(
        modifier = modifier,
        navController = controller,
        startDestination = path.takeIf { it.isNotBlank() } ?: "home"
    ) {
        composable(
            route = "home"
        ) {
            Text("Protocol Home Page")
        }

        composable(
            route = "scan"
        ) {
            ScanProtocolScene()
        }
    }
}