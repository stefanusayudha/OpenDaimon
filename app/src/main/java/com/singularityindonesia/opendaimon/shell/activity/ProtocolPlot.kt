package com.singularityindonesia.opendaimon.shell.activity

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
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
        startDestination = path.takeIf { it.isNotBlank() } ?: ProtocolHomeRoute.ROUTE
    ) {
        ProtocolHomeRoute(this)
        ScanProtocolRoute(this)
    }
}

object ProtocolHomeRoute {
    val ROUTE = "protocol/home"

    operator fun invoke(navGraphBuilder: NavGraphBuilder) {
        with(navGraphBuilder) {
            composable(
                route = ROUTE
            ) {
                Text("Protocol Home Page")
            }
        }
    }
}

object ScanProtocolRoute {
    val ROUTE = "protocol/scan"

    operator fun invoke(navGraphBuilder: NavGraphBuilder) {
        with(navGraphBuilder) {
            composable(
                route = ROUTE
            ) {
                ScanProtocolScene()
            }
        }
    }
}