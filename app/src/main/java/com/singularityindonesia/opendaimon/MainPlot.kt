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
            route = "protocol/{protocolId}",
            arguments = listOf(
                navArgument("protocolId") { type = NavType.StringType }
            )
        ) { backStackEntry ->
            val protocolId = backStackEntry.arguments?.getString("protocolId")

            ProtocolActivity(
                protocolId = protocolId.orEmpty(),
                returnBack = {
                    controller.popBackStack()
                }
            )
        }
    }
}