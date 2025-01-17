package com.singularityindonesia.opendaimon

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.singularityindonesia.opendaimon.ui.activity.HomeActivity
import com.singularityindonesia.opendaimon.ui.activity.SplashActivity

@Composable
fun MainPlot() {
    val controller = rememberNavController()
    NavHost(
        navController = controller,
        startDestination = "splash"
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
            HomeActivity()
        }
    }
}