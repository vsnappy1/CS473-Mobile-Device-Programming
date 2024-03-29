package com.kumar.mdp.bottom_navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.kumar.mdp.screen.HomeScreen
import com.kumar.mdp.screen.NotificationScreen
import com.kumar.mdp.screen.SettingsScreen

@Composable
fun NavigationHost(navController: NavHostController, paddingValues: PaddingValues) {
    NavHost(navController = navController, startDestination = BottomNavItem.Home.route) {
        composable(BottomNavItem.Home.route) { HomeScreen(paddingValues) }
        composable(BottomNavItem.Settings.route) { SettingsScreen(paddingValues) }
        composable(BottomNavItem.Notification.route) { NotificationScreen(paddingValues) }
    }
}