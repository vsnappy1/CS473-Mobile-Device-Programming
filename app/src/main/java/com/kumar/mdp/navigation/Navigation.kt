package com.kumar.mdp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.kumar.mdp.screen.GardenLogScreen
import com.kumar.mdp.screen.HomeScreen
import com.kumar.mdp.screen.PlantDetailScreen

@Composable
fun NavigationGardening(navController: NavHostController, onScanPlantClick: () -> Unit) {
    NavHost(navController = navController, startDestination = "/home") {
        composable(route = "/home") {
            HomeScreen(
                onGardenLogClick = {
                    navController.navigate(
                        route = "/garden_log_screen"
                    )
                },
                onPlantCareTipsClick = {},
                onScanPlantClick = onScanPlantClick
            )
        }
        composable(route = "/garden_log_screen") {
            GardenLogScreen() {
                navController.navigate(
                    route = "/plant_detail/${it}"
                )
            }
        }
        composable(
            route = "/plant_detail/{plant_id}",
            arguments = listOf(navArgument("plant_id") {
                type = NavType.IntType
            })
        ) {
            val plantId = it.arguments?.getInt("plant_id") ?: 0
            PlantDetailScreen(plantId = plantId, onDelete = { navController.popBackStack() })
        }
    }
}