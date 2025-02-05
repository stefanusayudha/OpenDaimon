package com.singularityindonesia.opendaimon

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.singularityindonesia.opendaimon.shell.pane.HomePane
import com.singularityindonesia.opendaimon.shell.activity.ProtocolActivity
import com.singularityindonesia.opendaimon.shell.activity.ScanProtocolRoute
import com.singularityindonesia.opendaimon.shell.activity.SplashActivity
import com.singularityindonesia.opendaimon.shell.activity.SystemActivity

@Composable
fun MainPlot() {
    val controller = rememberNavController()
    NavHost(
        navController = controller,
        startDestination = "home"
    ) {
        composable(
            route = "splash"
        ) {
            SplashActivity(
                onFinishPreparation = {
                    controller.navigate("home")
                }
            )
        }
        composable(
            route = "home"
        ) {
            HomePane(
                goToScanProtocol = {
                    controller.navigate(ScanProtocolRoute.ROUTE)
                },
                goToNeuronGraph = {
                    controller.navigate("system/neuron-graph")
                }
            )
        }
        composable(
            route = "protocol/{path}",
            arguments = listOf(
                navArgument("path") { type = NavType.StringType }
            )
        ) { backStackEntry ->
            val path = backStackEntry.arguments?.getString("path")

            ProtocolActivity(
                path = path.orEmpty(),
                returnBack = {
                    controller.popBackStack()
                }
            )
        }

        composable(
            route = "system/{path}",
            arguments = listOf(
                navArgument("path") {
                    type = NavType.StringType
                }
            )
        ) { backStackEntry ->
            val path = backStackEntry.arguments?.getString("path")

            SystemActivity(
                path = path.orEmpty(),
                returnBack = {
                    controller.popBackStack()
                }
            )
        }
    }
}