package com.singularityindonesia.opendaimon

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.singularityindonesia.opendaimon.shell.home.HomeActivity
import com.singularityindonesia.opendaimon.shell.protocol.ProtocolActivity
import com.singularityindonesia.opendaimon.shell.splash.SplashActivity

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
            HomeActivity(
                goToScanProtocol = {
                    controller.navigate("protocol/scan")
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
    }
}